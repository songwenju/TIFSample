package com.songwenju.tifsample;

import android.content.Context;
import android.media.tv.TvContentRating;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.media.tv.TvTrackInfo;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "swj";
    private TvView mTvView;
    private String mInputId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPlayFist = (Button) findViewById(R.id.btn_play_first);
        Button btnPlayNext = (Button) findViewById(R.id.btn_play_next);
        btnPlayFist.setOnClickListener(this);
        btnPlayNext.setOnClickListener(this);


        mInputId = "com.songwenju.tifservice.TvService/com.songwenju.tifservice.TvService";
        TvInputManager tvInputManager = (TvInputManager) getSystemService(Context.TV_INPUT_SERVICE);

        List<TvInputInfo> list = tvInputManager.getTvInputList();
        for (TvInputInfo info : list) {
            Log.i(TAG, "id:" + info.getId());
        }
        mTvView = (TvView) findViewById(R.id.tv_view);


        mTvView.setCallback(new TvView.TvInputCallback() {
            @Override
            public void onConnectionFailed(String inputId) {
                super.onConnectionFailed(inputId);
                LogUtil.i(this, "MainActivity.onConnectionFailed:" + inputId);
            }

            @Override
            public void onDisconnected(String inputId) {
                super.onDisconnected(inputId);
                LogUtil.i(this, "MainActivity.onDisconnected.");
            }

            @Override
            public void onChannelRetuned(String inputId, Uri channelUri) {
                super.onChannelRetuned(inputId, channelUri);
                LogUtil.i(this, "MainActivity.onChannelRetuned.");
            }

            @Override
            public void onTracksChanged(String inputId, List<TvTrackInfo> tracks) {
                super.onTracksChanged(inputId, tracks);
                LogUtil.i(this, "MainActivity.onTracksChanged.");
            }

            @Override
            public void onTrackSelected(String inputId, int type, String trackId) {
                super.onTrackSelected(inputId, type, trackId);
                LogUtil.i(this, "MainActivity.onTrackSelected.");
            }

            @Override
            public void onVideoSizeChanged(String inputId, int width, int height) {
                super.onVideoSizeChanged(inputId, width, height);
                LogUtil.i(this, "MainActivity.onVideoSizeChanged.");
            }

            @Override
            public void onVideoAvailable(String inputId) {
                super.onVideoAvailable(inputId);
                LogUtil.i(this, "MainActivity.onVideoAvailable.inputId:" + inputId);
            }

            @Override
            public void onVideoUnavailable(String inputId, int reason) {
                super.onVideoUnavailable(inputId, reason);
                LogUtil.i(this, "MainActivity.onVideoUnavailable.");
            }

            @Override
            public void onContentAllowed(String inputId) {
                super.onContentAllowed(inputId);
                LogUtil.i(this, "MainActivity.onContentAllowed.");
            }

            @Override
            public void onContentBlocked(String inputId, TvContentRating rating) {
                super.onContentBlocked(inputId, rating);
                LogUtil.i(this, "MainActivity.onContentBlocked.");
            }

            @Override
            public void onTimeShiftStatusChanged(String inputId, int status) {
                super.onTimeShiftStatusChanged(inputId, status);
                LogUtil.i(this, "MainActivity.onTimeShiftStatusChanged.");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_first:
                mTvView.reset();
                mTvView.tune(mInputId, Uri.parse("content://main/250")); //根据数据库的id去查找
                break;
            case R.id.btn_play_next:

                mTvView.reset();
                mTvView.tune(mInputId, Uri.parse("content://main/252"));
                break;
        }
    }
}
