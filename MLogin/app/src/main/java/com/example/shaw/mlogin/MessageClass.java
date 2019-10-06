package com.example.shaw.mlogin;

/**
 * Created by HP on 16-03-2018.
 */

public class MessageClass {

    String suid="",ruid="",msg="",msgid="";

    MessageClass(String msgid,String suid,String ruid,String msg)
    {
        this.msgid=msgid;
        this.suid=suid;
        this.ruid=ruid;
        this.msg=msg;
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
