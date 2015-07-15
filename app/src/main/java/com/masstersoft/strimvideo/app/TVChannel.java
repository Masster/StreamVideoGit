package com.masstersoft.strimvideo.app;

/**
 * Created by Masster on 04.06.2015.
 */
public class TVChannel {

    private String tvtitle;
    private String tvlink;

    public TVChannel(String tvt, String tvl)
    {
        this.tvtitle = tvt;
        this.tvlink = tvl;
    }

    public String getTitle(){return tvtitle;}
    public String getLink(){return tvlink;}

    public String toString(){return getTitle();}

}
