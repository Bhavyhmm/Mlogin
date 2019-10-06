package com.example.shaw.mlogin;

/**
 * Created by HP on 16-03-2018.
 */

public class UserClass {

    String uid="",uname="",uemail="";
    byte[] uimage;

    UserClass(String uid,String uname,String uemail,byte[] uimage)
    {
        this.uid=uid;
        this.uname=uname;
        this.uemail=uemail;
        this.uimage=uimage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public byte[] getUimage() {
        return uimage;
    }

    public void setUimage(byte[] uimage) {
        this.uimage = uimage;
    }
}
