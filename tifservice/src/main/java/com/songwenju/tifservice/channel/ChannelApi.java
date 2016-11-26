package com.songwenju.tifservice.channel;


import com.songwenju.tifservice.AppConstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * songwenju on 16-7-26 : 10 : 44.
 * 邮箱：songwenju@outlook.com
 */
public class ChannelApi extends BaseApi{

    private static ChannelApi mChannelApi = new ChannelApi();
    private ChannelService mChannelService;

    public static ChannelApi getInstance(){
        return mChannelApi;
    }

    private ChannelApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_CHANNEL_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mChannelService = retrofit.create(ChannelService.class);
    }

    /**
     * 获取ChannelService的实例
     * @return
     */
    public ChannelService getChannelService() {
        return mChannelService;
    }
}
