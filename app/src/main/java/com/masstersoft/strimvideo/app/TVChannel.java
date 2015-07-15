package com.masstersoft.strimvideo.app;

/**
 * Created by Masster on 04.06.2015.
 */
public class TVChannel {

    private String tvTitle;
    private String tvLink;

    public TVChannel(String tvTitle, String tvLink)
    {
        this.tvTitle = tvTitle;
        this.tvLink = tvLink;
    }

    public String getTitle(){return tvTitle;}
    public String getLink(){return tvLink;}

    public String toString(){return getTitle();}

}
