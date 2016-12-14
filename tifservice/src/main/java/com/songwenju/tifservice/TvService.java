package com.songwenju.tifservice;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.tv.TvContract;
import android.media.tv.TvInputManager;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Surface;

import com.songwenju.tifservice.channel.ChannelResult.GooglevideosBean.VideosBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_END;
import static android.media.MediaPlayer.MEDIA_INFO_BUFFERING_START;
import static android.media.MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START;

/**
 * songwenju on 16-10-26 : 17 : 14.
 * 邮箱：songwenju@outlook.com
 */

public class TvService extends TvInputService {
    private SimpleSessionImpl mSimpleSession;
    private Context mContext;

    @Nullable
    @Override
    public Session onCreateSession(String inputId) {
        LogUtil.i(this, "TvService.onCreateSession.inputId:" + inputId);
        mContext = this;
        mSimpleSession = new SimpleSessionImpl(this);
        return mSimpleSession;
    }


    public class SimpleSessionImpl extends Session {
        private MediaPlayer mMediaPlayer;
        private Surface mSurface;

        /**
         * Creates a new Session.
         *
         * @param context The context of the application
         */
        public SimpleSessionImpl(Context context) {
            super(context);
            LogUtil.i(this, "SimpleSessionImpl.SimpleSessionImpl.");
        }

        @Override
        public void onRelease() {
            LogUtil.i(this, "SimpleSessionImpl.onRelease.");
        }

        @Override
        public boolean onSetSurface(Surface surface) {
            LogUtil.i(this, "SimpleSessionImpl.onSetSurface." + surface);
            mSurface = surface;
            return true;
        }

        @Override
        public void onSetStreamVolume(float volume) {
            LogUtil.i(this, "SimpleSessionImpl.onSetStreamVolume.");
        }

        @Override
        public boolean onTune(Uri channelUri) {
            LogUtil.i(this, "SimpleSessionImpl.onTune.");
            Long channelId = ContentUris.parseId(channelUri);
            LogUtil.d(this, "channelId:" + channelId);
            mSimpleSession.notifyVideoUnavailable(TvInputManager.VIDEO_UNAVAILABLE_REASON_TUNING);
            return setChannelIdAndPlay(channelId);
        }

        /**
         * 设置ChannelId并播放
         *
         * @param channelId channelId
         * @return 播放状态
         */
        private boolean setChannelIdAndPlay(Long channelId) {
            VideosBean dbChannel = getDbChannel(mContext, channelId);
            LogUtil.i(this, "SimpleSessionImpl.setChannelIdAndPlay." + dbChannel.toString());
            mMediaPlayer = new MediaPlayer();
            String playUrl;
            try {
                playUrl = dbChannel.getSources().get(0); //google的json有时候不能用
                if (TextUtils.isEmpty(playUrl)) {
                    if (channelId == 1) {
                        playUrl = "http://cord.tvxio.com/v1_0/I2/frk/api/live/m3u8/9/5f754b84-ec33-4d62-bb81-3e4de21c8460/medium/";
                    } else {
                        playUrl = " http://cord.tvxio.com/v1_0/I2/frk/api/live/m3u8/9/577da15a-9007-4fdd-a9cf-6e19d7a04528/medium/";
                    }
                }
                LogUtil.i(this, "SimpleSessionImpl.setChannelIdAndPlay.playUrl=" + playUrl);
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(playUrl);
                mMediaPlayer.setSurface(mSurface);
                mMediaPlayer.setOnErrorListener(new OnErrorListener());
                mMediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener());
                mMediaPlayer.setOnInfoListener(new OnInfoListener());
                mMediaPlayer.setOnPreparedListener(new OnPreparedListener());
                mMediaPlayer.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        /**
         * 查询数据库，获得相应的值，如：播放地址，播放名称等
         *
         * @param channelId channelID
         * @return 数据集合
         */
        VideosBean getDbChannel(Context context, long channelId) {
            LogUtil.i(TAG, "DbUtil.getDbChannel channelId:" + channelId);
            VideosBean videosBean = new VideosBean();
            Uri queryUri = ContentUris.withAppendedId(TvContract.Channels.CONTENT_URI, channelId);
            Cursor channelsCursor = context.getContentResolver().query(queryUri, null, null, null, null);

            if (channelsCursor != null && channelsCursor.moveToNext()) {
                String playUrl = channelsCursor.getString(channelsCursor.getColumnIndex(TvContract.Channels.COLUMN_DISPLAY_NUMBER));
                List<String> sources = new ArrayList<>();
                sources.add(playUrl);
                videosBean.setSources(sources);
                videosBean.setTitle(channelsCursor.
                        getString(channelsCursor.getColumnIndex(TvContract.Channels.COLUMN_DISPLAY_NAME)));
                videosBean.setDescription(channelsCursor.getString(channelsCursor.getColumnIndex(TvContract.Channels.COLUMN_DESCRIPTION)));
            }
            if (channelsCursor != null) {
                channelsCursor.close();
            }
            LogUtil.i(this, "SimpleSessionImpl.getDbChannel." + videosBean);
            return videosBean;
        }


        @Override
        public void onSetCaptionEnabled(boolean enabled) {
            LogUtil.i(this, "SimpleSessionImpl.onSetCaptionEnabled.");
        }

        private class OnErrorListener implements MediaPlayer.OnErrorListener {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                LogUtil.i(this, "SimpleSessionImpl.onError.what=" + what + " extra=" + extra);
                notifyVideoUnavailable(TvInputManager.VIDEO_UNAVAILABLE_REASON_UNKNOWN);
                return false;
            }
        }

        private class OnBufferingUpdateListener implements MediaPlayer.OnBufferingUpdateListener {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                LogUtil.i(this, "SimpleSessionImpl.onBufferingUpdate.percent=" + percent);
            }
        }

        private class OnInfoListener implements MediaPlayer.OnInfoListener {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                LogUtil.i(this, "SimpleSessionImpl.onInfo.what=" + what + " extra=" + extra);
                switch (what) {
                    case MEDIA_INFO_BUFFERING_START:
                        notifyVideoUnavailable(TvInputManager.VIDEO_UNAVAILABLE_REASON_BUFFERING);
                        break;
                    case MEDIA_INFO_BUFFERING_END:
                        notifyVideoAvailable();
                        break;
                    case MEDIA_INFO_VIDEO_RENDERING_START:
                        //开始播放
                        notifyVideoAvailable();
                        break;
                }
                return false;
            }
        }

        private class OnPreparedListener implements MediaPlayer.OnPreparedListener {
            @Override
            public void onPrepared(MediaPlayer mp) {
                LogUtil.i(this, "SimpleSessionImpl.onPrepared.");
                mSimpleSession.notifyVideoAvailable();
                mp.start();
            }
        }
    }

}
