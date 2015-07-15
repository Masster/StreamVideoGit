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
    ListView listView;
    ProgressDialog progressDialog;
    static String TITLE = "title";
    static String LINK = "link";

    String string;
    NodeList nodeList;
    TVList tvList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadXML().execute();
        listView = (ListView)findViewById(R.id.listview);
        tvList = new TVList();
    }

    private class DownloadXML extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Обновление списка каналов");
            progressDialog.setMessage("Загрузка...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            string = XMLfunctions.getXmlFromUrl("http://masstersoft.com/tv_channels.xml");
            nodeList = XMLfunctions.getDomElementAndItems(string,"item");
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) item;
                    tvList.putItem(new TVChannel(getNode(TITLE, element), getNode(LINK, element)));
                }
            }

            ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this, tvList.getItems());
            listView.setAdapter(listViewAdapter);
            progressDialog.dismiss();
        }

        private String getNode(String sTag, Element element) {
            NodeList childNodes = element.getElementsByTagName(sTag).item(0).getChildNodes();
            Node item = childNodes.item(0);
            return item.getNodeValue();
        }
    }
}