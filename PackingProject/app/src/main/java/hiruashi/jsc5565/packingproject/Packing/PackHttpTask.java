package hiruashi.jsc5565.packingproject.Packing;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import hiruashi.jsc5565.packingproject.util.SessionUtil;

/**
 * Created by 정수찬 (jung suchan) on 2016-12-12.
 */

public class PackHttpTask extends AsyncTask<String, Void, String>{

    /*
        variables
     */
    private String url;


    private String requestMethod = "GET";
    private String contentType = "text/xml";
    private String format = "UTF-8";
    private HashMap<String, String> data = null;
    private ArrayList<String[]> requestProperty = new ArrayList<String[]>();

    private boolean SessionKeeping = false;

    /******************************************************
     *                  constructor
     ******************************************************/


    /**
     * constructor
     * initial url
     * @param url
     * @throws MalformedURLException
     */
    public PackHttpTask(String url){
        this.url = url;
    }



    /**
     * constructor with url and data
     * @param url
     * @param requestMethod
     * @throws MalformedURLException
     */
    PackHttpTask(String url, String requestMethod){

        this.url = url;
        this.requestMethod = requestMethod;
    }


    PackHttpTask(String url, String requestMethod, HashMap<String, String> data){
        this.url = url;
        this.requestMethod = requestMethod;
        this.data = data;
    }


    /*********************************************
                    private method
     *********************************************/


    /**
     * HttpUrlConnection setting in run function
     * @throws IOException
     * @return
     */
    private String HttpConnect() throws IOException{
        URL url = null;
        if(requestMethod.toLowerCase() == "get"){
            url = new URL(UrlGetFormat(this.url, this.data));
        }
        else {
            url = new URL(this.url);
        }
        HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
        SessionUtil sessionTask = new SessionUtil(this.getUrl());

        /*
            httpconnection default setting
         */
        httpcon.setReadTimeout(10000);
        httpcon.setConnectTimeout(15000);
        httpcon.setRequestMethod(requestMethod);
        httpcon.setDoInput(true);
        httpcon.setDoOutput(true);
        httpcon.setRequestProperty("Content-Type", contentType);

        // set requestproperty
        for(int i=0; i<requestProperty.size(); i++){
            httpcon.setRequestProperty(requestProperty.get(i)[0], requestProperty.get(i)[1]);
        }

        httpcon.setDefaultUseCaches(false);
        httpcon.setUseCaches(false);


        /*
            save session by cookie
         */
        if(SessionKeeping){
           httpcon.setRequestProperty("Cookie", sessionTask.getSession());
        }
        httpcon = HttpSend(httpcon);

        if(SessionKeeping){
            sessionTask.KeepSession(httpcon);
        }
        return HttpGet(httpcon);
    }






    /**
     * data send
     * @param httpcon
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private HttpURLConnection HttpSend(HttpURLConnection httpcon) throws IOException{
        byte[] dataStream = null;
        if(requestMethod.toLowerCase() == "post") {
            dataStream = JsonDataFormat(this.data).getBytes(format);
        }

        OutputStream outputStream = httpcon.getOutputStream();

        outputStream.write(dataStream); // send data
        outputStream.close();

        return httpcon;
    }


    /**
     * get result at connection
     * @param httpcon
     * @return
     * @throws IOException
     */
    private String HttpGet(HttpURLConnection httpcon) throws IOException{

        InputStream inputStream = httpcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, format));

        String line;
        StringBuffer resultbuffer = new StringBuffer();

        /*
            read response
         */
        while((line = buffer.readLine()) != null){
            resultbuffer.append(line);
            resultbuffer.append("\r");
        }

        String result = resultbuffer.toString();
        return result;
    }



    /*
        private method
     */

    private String UrlGetFormat(String url, HashMap<String, String> map){
        String data = "";
        ArrayList<String> keyList = new ArrayList<String>(map.keySet());
        ArrayList<String> valueList = new ArrayList<String>(map.values());

        for(int i=0; i<map.size()-1; i++){
            data += keyList.get(i) + "=" + valueList.get(i) + "&";
        }
        data += keyList.get(map.size()-1) + "=" + valueList.get(map.size()-1);

        return this.url + "?" + data;
    }



    private String JsonDataFormat(HashMap<String, String> map){

        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }




    /*
        Session method
     */


    public boolean ClearSession(Context context){
        try {
            SessionUtil sessionUtil = new SessionUtil(this.getUrl());
            sessionUtil.ClearSession(context);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * If you want to keep seesion, input true.
     * @param session
     */
    public void keepSession(boolean session){
        this.SessionKeeping = session;
    }

    /*********************************************
     *              set method
     *********************************************/

    /**
     * set url
     * @param url
     * @throws MalformedURLException
     */
    public void setUrl(String url){
        this.url = url;
    }


    /**
     * set request method
     * @param request
     */
    public void setRequestMethod(String request){
        this.requestMethod = request;
    }


    /**
     * set content type
     * @param contentType
     */
    public void setContentType(String contentType){
        this.contentType = contentType;
    }


    /**
     * set format
     * @param Format
     */
    public void setFormat(String Format){
        this.format = Format;
    }


    /**
     * set data
     * @param data
     */
    public void setData(HashMap<String, String> data){
        this.data = data;
    }


    /**
     * set requestproperty in httpurlconnection
     * @param key
     * @param value
     */
    public void setRequestProperty(String key, String value){
        requestProperty.add(new String[]{key, value});
    }


    /*********************************************
                    get method
     *********************************************/

    public String getUrl(){
        return this.url.toString();
    }

    public String getFormat(){
        return this.format;
    }

    public HashMap<String, String> getData(){
        return this.data;
    }



    /*********************************************
     *         run method in background
     *********************************************/


    /**
     * run in background
     * @param strings
     * @return
     */
    @Override
    protected String doInBackground(String... strings) {
        String result = null;
        Log.i("PackHttpTask", "Start");
        try {
            result = HttpConnect();
        }catch (IOException e){
            Log.e("PackHttpTask", "ERROR - "+e);
            e.printStackTrace();
        }

        return result;
    }
}
