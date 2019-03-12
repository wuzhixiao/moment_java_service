package com.nujnay.moment.server.wechat;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WeChatAuto {
    static String wechat_sogou = "https://weixin.sogou.com/weixin?type=1&s_from=input&query=%E7%9B%98%E7%9F%B3&ie=utf8&_sug_=n&_sug_type_=";


    static String wechat_sogou2 = "https://weixin.sogou.com/weixin?type=2&query=%E7%9B%98%E7%9F%B3&ie=utf8&s_from=input&_sug_=n&_sug_type_=1&w=01015002&oq=&ri=0&sourceid=sugg&sut=0&sst0=1545469835147&lkt=0%2C0%2C0&p=40040108";

    static String weChatPanshiUrl = "http://mp.weixin.qq.com/profile?src=3&timestamp=1545462764&ver=1&signature=yjfRh9uy-vMYnbmRChx0pC2jNVQNDNkDkQIFx0VHYTWBPriLdzMXUieHiri-vBEFctb*XSbHnVs0VhJsmRdXMg==";


    static String weChatBaseUrl = "https://mp.weixin.qq.com";


    public static void main(String[] args) {
//        1545469715
//        1545469589569

        //1545469919
        //1545469881287
//        System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis());
//        forward(wechat_sogou2);
        wechatForward();
    }

    public static void forward(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request
                .Builder()
//                .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码
                .url(url)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
//            System.out.print(response.body().string());
            String time = parseGetTime(response.body().string());
            response.body().close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String parseGetTime(String body) {
        String str1 = body.substring(body.indexOf("gzh-box"));
        str1 = str1.substring(str1.indexOf("href=\""));
        str1 = str1.substring(0, str1.indexOf("\">"));
        str1 = str1.substring(str1.indexOf("timestamp="));
        str1 = str1.substring(10, str1.indexOf("&"));
        System.out.print(str1);
        return str1;
//        System.out.print(str3);
    }

    //<a target="_blank" uigs="account_name_0" href="http://mp.weixin.qq.com/profile?src=3&amp;timestamp=1545471685&amp;ver=1&amp;signature=yjfRh9uy-vMYnbmRChx0pC2jNVQNDNkDkQIFx0VHYTWBPriLdzMXUieHiri-vBEFAMN0Se9ZsVn3l0eRY3l4VQ=="><em><!--red_beg-->盘石<!--red_end--></em></a>
    public static void wechatForward() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Request request = new Request
                .Builder()
//                .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码
                .url(weChatPanshiUrl)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
//            System.out.print(response.body().string());
            psParse(response.body().string());
            response.body().close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void psParse(String responseBody) {
//        msgList
        responseBody.indexOf("msgList");
        //msgList 之后的字符截取
        String str = responseBody.substring(responseBody.indexOf("msgList"));
//        System.out.println(str);
        int start = str.indexOf("=") + 1;
        //
        String json = str.substring(start, str.lastIndexOf("}}]};") + 4).trim();
//        System.out.println(json.trim());

        //json解析
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray list = jsonObject.getJSONArray("list");
            JSONObject listObjest = list.getJSONObject(0);
            JSONObject app_msg_ext_info = listObjest.getJSONObject("app_msg_ext_info");
            JSONObject comm_msg_info = listObjest.getJSONObject("comm_msg_info");

            String content_url = app_msg_ext_info.getString("content_url");
            String wetChatContent_url = weChatBaseUrl + content_url;
            String cover = app_msg_ext_info.getString("cover");
            String digest = app_msg_ext_info.getString("digest");
            String title = app_msg_ext_info.getString("title");

            System.out.println(wetChatContent_url);
            System.out.println(cover);
            System.out.println(digest);
            System.out.println(title);

            for (int i = 0; i < list.length(); i++) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
