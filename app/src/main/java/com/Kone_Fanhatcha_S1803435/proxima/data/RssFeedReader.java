package com.Kone_Fanhatcha_S1803435.proxima.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Kone_Fanhatcha_S1803435.proxima.Models.FeedItem;
import com.Kone_Fanhatcha_S1803435.proxima.adapters.FeedsAdapter;
import com.Kone_Fanhatcha_S1803435.proxima.adapters.VerticalSpace;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Kone Fanhatcha - Student ID: S1803435.
 */
public class RssFeedReader extends AsyncTask<Void, Void, Void> {

    Context context;
    String feedUrl = "http://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
    ProgressDialog progressDialog;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    URL url;

    public RssFeedReader(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    //Displaying progress dialog, before fetching rss feed
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    //This method will execute in background so in this method download rss feeds
    @Override
    protected Void doInBackground(Void... params) {
        //call process xml method to process document we downloaded from getData() method
        ProcessXml(Getdata());

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        FeedsAdapter adapter = new FeedsAdapter(context, feedItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new VerticalSpace(20));
        recyclerView.setAdapter(adapter);

    }


    // In this method we will process Rss feed  document we downloaded to parse useful information from it
    private void ProcessXml(Document data) {
        if (data != null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentchild = items.item(i);
                if (currentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item = new FeedItem();
                    NodeList itemchilds = currentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node current = itemchilds.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPubDate(current.getTextContent());
                        }
                        else if (current.getNodeName().equalsIgnoreCase("category")) {
                            item.setCategory(current.getTextContent());
                        }
                        else if (current.getNodeName().equalsIgnoreCase("geo:lat")) {
                            item.setGeoLat(current.getTextContent());
                        }
                        else if (current.getNodeName().equalsIgnoreCase("geo:long")) {
                            item.setGeoLong(current.getTextContent());
                        }

                    }
                    feedItems.add(item);


                }
            }
        }
    }

    //This method will download rss feed document from specified url
    public Document Getdata() {
        try {
            url = new URL(feedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
