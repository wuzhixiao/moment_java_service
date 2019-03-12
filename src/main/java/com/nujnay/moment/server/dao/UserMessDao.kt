package com.nujnay.moment.server.dao

import com.nujnay.moment.server.bean.UserMessBean
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMessDao {

    fun getUserMess(@Param("phoneNum") phoneNum:String): UserMessBean

    fun insertOneUsermess(userMessBean: UserMessBean)

}
