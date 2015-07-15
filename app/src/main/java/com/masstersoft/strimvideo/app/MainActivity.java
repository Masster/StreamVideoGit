package com.masstersoft.strimvideo.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainActivity extends Activity {
    ListView listview;
    ProgressDialog mProgressDialog;
    static String TITLE = "title";
    static String LINK = "link";

    String str;
    NodeList nodelist;
    TVList tvlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadXML().execute();
        listview = (ListView)findViewById(R.id.listview);
        tvlist = new TVList();
    }

    private class DownloadXML extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Обновление списка каналов");
            mProgressDialog.setMessage("Загрузка...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            str = XMLfunctions.getXmlFromUrl("http://masstersoft.com/tv_channels.xml");
            nodelist = XMLfunctions.getDomElementAndItems(str,"item");
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            for (int temp = 0; temp < nodelist.getLength(); temp++) {
                Node nNode = nodelist.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    tvlist.putItem(new TVChannel(getNode(TITLE, eElement),getNode(LINK, eElement)));
                }
            }

            ListViewAdapter lvadapter = new ListViewAdapter(MainActivity.this,tvlist.getItems());
            listview.setAdapter(lvadapter);
            mProgressDialog.dismiss();
        }

        private String getNode(String sTag, Element eElement) {
            NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = nlList.item(0);
            return nValue.getNodeValue();
        }
    }
}