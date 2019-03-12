package com.nujnay.moment.server.dao

import com.nujnay.moment.server.bean.MainBean
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MainDao {
    fun insertMainData(mainBean: MainBean)
}