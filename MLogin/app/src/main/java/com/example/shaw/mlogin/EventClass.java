package com.example.shaw.mlogin;

/**
 * Created by HP on 19-03-2018.
 */

public class EventClass {
    public String eid,ename,edate,etime,edesc;
    EventClass(String eid,String ename,String edate,String etime,String edesc)
    {
        this.eid=eid;
        this.ename=ename;
        this.edate=edate;
        this.etime=etime;
        this.edesc=edesc;

    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

}
