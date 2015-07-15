package com.masstersoft.strimvideo.app;

import java.util.ArrayList;

/**
 * Created by Masster on 04.06.2015.
 */
public class TVList {
    private ArrayList<TVChannel> channels;

    public TVList(){
        channels = new ArrayList<TVChannel>();
    }

    public void putItem(TVChannel tvC){channels.add(tvC);}
    public int getSize(){return channels.size();}
    public TVChannel getItem(int id){return channels.get(id);}
    public ArrayList<TVChannel> getItems(){return  channels;}

}
