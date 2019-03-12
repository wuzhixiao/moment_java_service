package com.nujnay.moment.server.dao

import com.nujnay.moment.server.bean.WechatContentBean
import org.apache.ibatis.annotations.Mapper

@Mapper
interface WechatContentDao {
    fun getWechatData(): WechatContentBean
}
