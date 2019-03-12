package com.nujnay.moment.server.weibo

import okhttp3.*

import java.io.IOException
import java.util.concurrent.TimeUnit

class WeiboAuto {

    companion object {
        var forwarding = false
        fun sendForward(cookie2: String, mid: String, reason: String) {
            if (cookie2.isEmpty()) {
                forwarding = false
                return
            }
            if (mid.isEmpty()) {
                forwarding = false
                return
            }
            if (reason.isEmpty()) {
                forwarding = false
                return
            }
            val forwardurl = "https://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=100505&__rnd=" + System.currentTimeMillis()
            val httpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                                .addHeader("Cookie", cookie2)
                                .addHeader("Referer", "https://weibo.com/5609729720/profile?topnav=1&wvr=6&is_all=1")
                        val request = requestBuilder.build()

                        val response = chain.proceed(request)
                        var responseBody: ResponseBody? = null
                        if (response != null) {
                            responseBody = response.body()
                        }









                        if (responseBody != null) {
                            val content = responseBody.string()
                        }
                        response
                    }
                    .readTimeout(8, TimeUnit.SECONDS)
                    .writeTimeout(8, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build()
            val formBody = FormBody.Builder()
                    .add("pic_src", "")
                    .add("pic_id", "")
                    .add("appkey", "")
                    .add("mid", mid)
                    .add("style_type", "1")
                    .add("mark", "")
                    .add("reason", reason)
                    .add("from_plugin", "0")
                    .add("location", "v6_content_home")
                    .add("pdetail", "")
                    .add("module", "")
                    .add("page_module_id", "")
                    .add("refer_sort", "")
                    .add("rank", "0")
                    .add("rankid", "")
                    .add("group_source", "group_all")
                    .add("rid", "0_2097152_8_2669534912802240224_0_0")
                    .add("isReEdit", "false")
                    .add("rid", "0_2097152_8_2669534912802240224_0_0")
                    .add("isReEdit", "false")
                    .add("_t", "0")
                    .build()
            val request = Request.Builder()
                    .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码
                    .url(forwardurl)
                    .build()

            try {
                httpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        forwarding = false
                    }

                    override fun onResponse(call: Call, response: Response) {
                    }
                })
            } catch (e: IOException) {
                forwarding = false
                e.printStackTrace()
            }
        }
    }
}
