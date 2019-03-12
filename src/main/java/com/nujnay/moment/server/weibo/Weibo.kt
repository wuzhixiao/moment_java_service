package com.nujnay.moment.server.weibo

import okhttp3.*

import java.io.IOException
import java.util.concurrent.TimeUnit

object Weibo {
    internal var weBo = "https://www.weibo.com/adyun?is_all=1"
    internal var cookie2 = "SINAGLOBAL=5785187276629.115.1540200416789; _ga=GA1.2.17390227.1545461934; ALF=1548410415; SUB=_2A25xJyF_DeRhGeBP41IX8izEyjSIHXVS6E83rDV8PUJbkNBeLWXAkW1NRT1Rr5HcFeDl8f36tWub4FVqNUkcwRKc; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WW8GQaZ0fYKGYLDMnVomz0j5JpX5oz75NHD95QceKn7SozE1h2RWs4Dqcjni--fi-z7iKysi--fi-isiKn0i--ciKn0iKnfSoM7eKBt; UOR=oa.adyun.com,widget.weibo.com,oa.adyun.com; wvr=6; _s_tentry=-; Apache=5565707328994.493.1545979190817; ULV=1545979190853:7:5:2:5565707328994.493.1545979190817:1545818171869"

    @JvmStatic
    fun main(args: Array<String>) {

        weiboForward(weBo)
    }

    private fun weiboForward(url: String) {
        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                            .addHeader("Accept", "*/*")
                            .addHeader("Accept-Encoding", "gzip, deflate, br")
                            .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("Cookie", cookie2)
                            .addHeader("Referer", "https://www.weibo.com/adyun?topnav=1&wvr=6&topsug=1&is_all=1")
                            .addHeader("Host", "rm.api.weibo.com")
                            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
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

        val request = Request.Builder()
                //                .post(formBody)//Post请求的参数传递，此处是和Get请求相比，多出的一句代码
                .url(weBo)
                .build()


        try {
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    WeiboAuto.forwarding = false
                    System.out.print(e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    System.out.print(response.body().toString())
                }
            })
        } catch (e: IOException) {
            WeiboAuto.forwarding = false
            e.printStackTrace()
        }

    }

    private fun parseGetMid(str: String): String {
        val feed_list_item = str.indexOf("feed_list_item")
        val mid = str.substring(feed_list_item)
        print(mid)
        return mid
    }
}


