package com.nujnay.moment.server.bean;

public class UpdateVersionDO {
    /**
     * 新版本
     */
    private String newVersion;

    /**
     * 新版本名字
     */
    private String newVersionName;

    /**
     * apk链接
     */
    private String apkUrl;

    /**
     * 更新日志
     */
    private String updateLog;

    /**
     * 强制更新  0 强制   1- 不强制
     */
    private Integer forceUpdate;

    /**
     *  手机系统类型1--安卓；2--ios；
     */
    private Integer phoneSysType;

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public String getNewVersionName() {
        return newVersionName;
    }

    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public Integer getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public Integer getPhoneSysType() {
        return phoneSysType;
    }

    public void setPhoneSysType(Integer phoneSysType) {
        this.phoneSysType = phoneSysType;
    }
}
