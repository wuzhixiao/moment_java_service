package com.nujnay.moment.server.controller


import com.nujnay.moment.server.base.BasicResult
import com.nujnay.moment.server.bean.TodayBean
import com.nujnay.moment.server.bean.WeiboForwardResponse
import com.nujnay.moment.server.bean.UpdateVersionDO
import com.nujnay.moment.server.bean.UserMessBean
import com.nujnay.moment.server.bean.WechatContentBean
import com.nujnay.moment.server.service.DataServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.GetMapping


@RestController
class MomentController @Autowired
constructor(private val dataService: DataServiceImpl) {

    @PostMapping("/firstData")
    fun firstData(@RequestParam(value = "weibo_Cookie", required = true) weibo_Cookie: String) {
        dataService.setData(weibo_Cookie)
    }

    @GetMapping("/getTodayContent")
    fun getTodayContent(): TodayBean {
        return dataService.getTodayContent()
    }

    @GetMapping("/getWeChatContent")
    fun getWeChatContent(): WechatContentBean {
        return dataService.getWeChatContent()
    }

    @PostMapping("/weiboForwardOk")
    fun weiboForwardOk(@RequestBody weiboForwardResponse: WeiboForwardResponse): String {
        print(weiboForwardResponse.phone_number)
        return ""
    }

    //    @ApiOperation(value = "获取apk最新版本")
    @GetMapping("/apkVersion")
    fun getApkVersion(): BasicResult<UpdateVersionDO> {
        try {
            val updateVersionDO = dataService.getApkVersion()
            val basicResult = BasicResult<UpdateVersionDO>()
            basicResult.data = updateVersionDO
            //return BasicResult.ok(updateVersionDO)
            return basicResult
        } catch (e: Exception) {
            return BasicResult.error(0, e.toString() + "")
        }
    }

    //手机注册
    @PostMapping("/login")
    fun login(@RequestBody userMessBean: UserMessBean): BasicResult<UserMessBean> {
        var userMessBeanDO: UserMessBean? = null
        try {
            userMessBeanDO = dataService.getUserMess(userMessBean.phonenumber.toString())
            if (userMessBeanDO == null) {
                dataService.setUserMess(userMessBean)
                userMessBeanDO = dataService.getUserMess(userMessBean.phonenumber.toString())
            }

            if (userMessBean.phonenumber == userMessBeanDO.phonenumber &&
                    userMessBean.imei == userMessBeanDO.imei) {
                if (userMessBeanDO.isVip?.toInt() == 1) {
                    return BasicResult.ok()
                } else {
                    return BasicResult.error(2, "请联系客服获取vip资格") //不是vip
                }
            } else {
                return BasicResult.error(2, "登录异常，请联系客服") //手机号和imei值不匹配
            }

            return BasicResult.error(0, "")
        } catch (e: Exception) {
            return BasicResult.error(0, e.toString() + "")
        }

    }
}
