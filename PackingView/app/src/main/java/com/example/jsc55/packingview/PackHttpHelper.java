package com.example.jsc55.packingview;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jsc55 on 2016-12-12.
 */

public class PackHttpHelper extends Thread {

    /**
     * TODO 추후 개발 : 만들지 안만들지에 대해서도 정하지 않음
     */
    String method;
    URL url;
    HttpURLConnection conn;

    /**
     * constructor
     * @param url
     */
    PackHttpHelper(String url){

        try {
            /*
                make HttpURLConnection
             */
            this.url = new URL(url);
            conn = (HttpURLConnection) this.url.openConnection();

        }catch (MalformedURLException e1){
            e1.printStackTrace();

        }catch (IOException e2){
            e2.printStackTrace();

        }catch (Exception e3){
            e3.printStackTrace();
        }
    }

    public void run(){

    }
}
