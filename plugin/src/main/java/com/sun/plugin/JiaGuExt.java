package com.sun.plugin;

public class JiaGuExt {

    //360账号
    private String userName;
    //360密码
    private String userPsd;

    //签名路径
    private String keyStorePath;
    //签名密码
    private String keyStorePass;
    //签名别名
    private String keyStoreAlias;
    //别名密码
    private String keyStoreKeyAliasPsd;

    //加固包路径
    private String jiaguToolPath;
    //输出位置
    private String outPutApk;

    public String getOutPutApk() {
        return outPutApk;
    }

    public void setOutPutApk(String outPutApk) {
        this.outPutApk = outPutApk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public String getKeyStorePass() {
        return keyStorePass;
    }

    public void setKeyStorePass(String keyStorePass) {
        this.keyStorePass = keyStorePass;
    }

    public String getKeyStoreAlias() {
        return keyStoreAlias;
    }

    public void setKeyStoreAlias(String keyStoreAlias) {
        this.keyStoreAlias = keyStoreAlias;
    }

    public String getKeyStoreKeyAliasPsd() {
        return keyStoreKeyAliasPsd;
    }

    public void setKeyStoreKeyAliasPsd(String keyStoreKeyAliasPsd) {
        this.keyStoreKeyAliasPsd = keyStoreKeyAliasPsd;
    }

    public String getJiaguToolPath() {
        return jiaguToolPath;
    }

    public void setJiaguToolPath(String jiaguToolPath) {
        this.jiaguToolPath = jiaguToolPath;
    }
}


