package com.masstersoft.strimvideo.app;

import java.util.ArrayList;

/**
 * Created by Masster on 04.06.2015.
 */
public class TVList {
    private ArrayList<TVChannel> tvChannels;

    public TVList(){
        tvChannels = new ArrayList<TVChannel>();
    }

    public void putItem(TVChannel tvC){
        tvChannels.add(tvC);}
    public int getSize(){return tvChannels.size();}
    public TVChannel getItem(int id){return tvChannels.get(id);}
    public ArrayList<TVChannel> getItems(){return tvChannels;}

}
