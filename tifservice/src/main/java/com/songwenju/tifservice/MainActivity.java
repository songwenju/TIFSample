package com.songwenju.tifservice;

import android.content.ContentValues;
import android.content.Context;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.songwenju.tifservice.channel.ChannelApi;
import com.songwenju.tifservice.channel.ChannelResult;
import com.songwenju.tifservice.channel.ChannelResult.GooglevideosBean;
import com.songwenju.tifservice.channel.ChannelResult.GooglevideosBean.VideosBean;
import com.songwenju.tifservice.channel.ChannelService;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ChannelService mChannelService;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //初始化API
        ChannelApi channelApi = ChannelApi.getInstance();
        mChannelService = channelApi.getChannelService();
        setContentView(R.layout.activity_main);
        Button btnAddData = (Button) findViewById(R.id.btn_add_data);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData() {
        LogUtil.i(this,"MainActivity.addData.");
        mChannelService.getResult()
                .subscribeOn(Schedulers.newThread()) //请求数据在子线程
                .map(new Func1<ChannelResult, ChannelResult>() {
                    @Override
                    public ChannelResult call(ChannelResult channelResult) {
                        List<GooglevideosBean> googlevideos = channelResult.getGooglevideos();
                        for (GooglevideosBean googlevideosBean : googlevideos) {
                            for (VideosBean videoBean : googlevideosBean.getVideos()) {
                                insertChannelsData(mContext, videoBean);

                            }
                        }
                        return channelResult;
                    }
                }).subscribeOn(Schedulers.newThread())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ChannelResult>() {    // 相当于onNext()
                    @Override
                    public void call(ChannelResult s) {
                        LogUtil.i(this, "MainActivity.endCall.");
                        Toast.makeText(mContext,"数据写入完毕",Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>() {                       // 相当于onError()
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }


    /**
     * 写入channel到数据库
     *
     * @param context   上下文
     * @param videoBean videoBean
     */
    public static void insertChannelsData(Context context, VideosBean videoBean) {
        ContentValues value = new ContentValues();
        value.put(TvContract.Channels.COLUMN_INPUT_ID, "com.songwenju.tifservice/.TvService");
        value.put(TvContract.Channels.COLUMN_DISPLAY_NUMBER, videoBean.getSources().get(0));    //url
        value.put(TvContract.Channels.COLUMN_DISPLAY_NAME, videoBean.getTitle());               //name
        value.put(TvContract.Channels.COLUMN_DESCRIPTION, videoBean.getDescription());           //description
        context.getContentResolver().insert(TvContract.Channels.CONTENT_URI, value);
    }

}
