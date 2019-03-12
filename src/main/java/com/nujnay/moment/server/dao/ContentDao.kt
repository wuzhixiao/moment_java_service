package com.nujnay.moment.server.dao

import com.nujnay.moment.server.bean.MainBean
import com.nujnay.moment.server.bean.TodayBean
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ContentDao {
    fun getWeiboData(): TodayBean
}