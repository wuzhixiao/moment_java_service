package com.nujnay.moment.server.dao;

import com.nujnay.moment.server.bean.UpdateVersionDO;

public interface UserApkVersionMapper {
    /**
     * 获取最新apk
     * @return
     */
    UpdateVersionDO getApkVersion();
}
