package com.songwenju.tifservice.channel;

import java.util.List;

/**
 * songwenju on 16-11-3 : 14 : 59.
 * 邮箱：songwenju@outlook.com
 */

public class ChannelResult {

    /**
     * category : Google+
     * videos : [{"description":"Jon introduces Instant Upload with a few thoughts on how we remember the things that matter. Check out some ways we've been rethinking real-life sharing for the web at plus.google.com","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload/bg.jpg","title":"Instant Upload","studio":"Google+"},{"description":"With Google+ Instant Upload, every picture you take on your phone is instantly backed up to a private Google+ album. It's a simple way to make sure you never lose another memory.","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20New%20Dad.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20New%20Dad/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20New%20Dad/bg.jpg","title":"New Dad","studio":"Google+"},{"description":"Laugh, share news, celebrate, learn something new or stay in touch with Hangouts. And with Hangouts on your phone, you can drop in from wherever you are.","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Say%20more%20with%20Hangouts.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Say%20more%20with%20Hangouts/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Say%20more%20with%20Hangouts/bg.jpg","title":"Say more with Hangouts","studio":"Google+"},{"description":"Search on Google+ helps you get advice from the people you know -- sometimes when you least expect it. Check out some ways we've been rethinking real-life sharing for the web at plus.google.com.","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Search.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Search/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Search/bg.jpg","title":"Google+ Search","studio":"Google+"},{"description":"New ways of sharing the right things with the right people. Join at http://google.com/+","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Sharing%20but%20like%20real%20life.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Sharing%20but%20like%20real%20life/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Sharing%20but%20like%20real%20life/bg.jpg","title":"Sharing but like real life","studio":"Google+"},{"description":"Jed introduces Circles with a few thoughts on the nature of friendship. Check out some ways we've been rethinking real-life sharing for the web at plus.google.com.","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Circles.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Circles/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Circles/bg.jpg","title":"Google+ Circles","studio":"Google+"},{"description":"Aimee introduces Hangouts with a few thoughts on the spontaneous get-together. Check out some ways we've been rethinking real-life sharing for the web at plus.google.com.","sources":["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Hangouts.mp4"],"card":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Hangouts/card.jpg","background":"https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Hangouts/bg.jpg","title":"Google+ Hangouts","studio":"Google+"}]
     */

    private List<GooglevideosBean> googlevideos;

    public List<GooglevideosBean> getGooglevideos() {
        return googlevideos;
    }

    public void setGooglevideos(List<GooglevideosBean> googlevideos) {
        this.googlevideos = googlevideos;
    }

    public static class GooglevideosBean {
        private String category;
        /**
         * description : Jon introduces Instant Upload with a few thoughts on how we remember the things that matter. Check out some ways we've been rethinking real-life sharing for the web at plus.google.com
         * sources : ["https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload.mp4"]
         * card : https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload/card.jpg
         * background : https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20Instant%20Upload/bg.jpg
         * title : Instant Upload
         * studio : Google+
         */

        private List<VideosBean> videos;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class VideosBean {
            private String description;
            private String card;
            private String background;
            private String title;
            private String studio;
            private List<String> sources;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCard() {
                return card;
            }

            public void setCard(String card) {
                this.card = card;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStudio() {
                return studio;
            }

            public void setStudio(String studio) {
                this.studio = studio;
            }

            public List<String> getSources() {
                return sources;
            }

            public void setSources(List<String> sources) {
                this.sources = sources;
            }

            @Override
            public String toString() {
                return "VideosBean{" +
                        "description='" + description + '\'' +
                        ", card='" + card + '\'' +
                        ", background='" + background + '\'' +
                        ", title='" + title + '\'' +
                        ", studio='" + studio + '\'' +
                        ", sources=" + sources +
                        '}';
            }
        }
    }
}
