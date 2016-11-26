package com.songwenju.tifservice.channel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * songwenju on 16-7-26 : 14 : 02.
 * 邮箱：songwenju@outlook.com
 */
public interface ChannelService {
    @GET("/android-tv/android_tv_videos_new.json")   //注意这个后面不要写成这样android_tv_videos_new.json和这样android_tv_videos_new.json
    Observable<ChannelResult> getResult();
}
