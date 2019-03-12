package com.nujnay.moment.server.service

import com.google.gson.Gson
import com.nujnay.moment.server.bean.*
import com.nujnay.moment.server.dao.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DataServiceImpl {

    @Autowired
    var mainDao: MainDao? = null
    @Autowired
    var contentDao: ContentDao? = null

    @Autowired
    var wechatContentDao: WechatContentDao? = null

    @Autowired
    var userRegisterDao: UserRegisterDao? = null

    @Autowired
    var userMessDao: UserMessDao? = null

    fun setData(data: String) {
        val gson = Gson()
        val mainBean: MainBean = gson.fromJson(data, MainBean::class.java)
        mainDao?.insertMainData(mainBean)
    }

    fun getTodayContent(): TodayBean {
        return contentDao!!.getWeiboData()
    }

    fun getWeChatContent(): WechatContentBean {
        return wechatContentDao!!.getWechatData()
    }
    fun getApkVersion(): UpdateVersionDO {
        return userRegisterDao!!.getapkVersion()
    }

    fun getUserMess(phoneNum:String): UserMessBean {
        return userMessDao!!.getUserMess(phoneNum)
    }

    fun setUserMess(userMessBean: UserMessBean) {
        return userMessDao!!.insertOneUsermess(userMessBean)
    }
}
