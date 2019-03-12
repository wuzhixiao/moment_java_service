package com.nujnay.moment.server.dao

import com.nujnay.moment.server.bean.UpdateVersionDO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRegisterDao {
    /**
     * 获取最新的apk跟新信息
     *
     * @return
     */
//    val apkVersion: UpdateVersionDO
    fun getapkVersion(): UpdateVersionDO

}
