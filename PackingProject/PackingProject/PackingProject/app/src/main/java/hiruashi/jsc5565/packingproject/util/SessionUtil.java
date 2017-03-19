package hiruashi.jsc5565.packingproject.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by 정수찬 (jung suchan) on 2017. 1. 11..
 */

public class SessionUtil<T extends HttpURLConnection>{


    String url;


    public SessionUtil(String url){
        try {
            this.url = new URL(url).getHost();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public String getSession(){
        String cookieId = CookieManager.getInstance().getCookie(url);
        return cookieId;
    }

    public void KeepSession(T httpcon){
        Map<String, List<String>> headerFields = httpcon.getHeaderFields();
        List<String> cookieHeader = headerFields.get("Set-Cookie");

        if(cookieHeader != null){
            for(String cookie : cookieHeader){
                String cookieName = HttpCookie.parse(cookie).get(0).getName();
                String cookieValue = HttpCookie.parse(cookie).get(0).getValue();
                String cookieString = cookieName+"="+cookieValue;
                CookieManager.getInstance().setCookie(url, cookieString);
            }
        }
        else {
            Log.i("SessionUtil", "Empty Cookie Header");
        }
    }

    public void ClearSession(Context context){
        CookieManager cookieManager = CookieManager.getInstance();
        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(context);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.removeSessionCookies(null);
                cookieManager.flush();
            } else {
                cookieSyncManager.startSync();
                cookieManager.removeSessionCookie();
                cookieManager.removeAllCookie();
                cookieSyncManager.stopSync();
                cookieSyncManager.sync();
            }
        }catch (Exception e){
            Log.e("SessionUtil", "Error Clear Session - " + e);
            e.printStackTrace();
        }
    }
}
