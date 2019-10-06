package com.example.shaw.mlogin;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shaw on 08-02-2018.
 */

public class myClass extends SQLiteOpenHelper {

    myClass(Context context){
        super(context,"mlogin.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE student(uid INTEGER PRIMARY KEY AUTOINCREMENT,uname VARCHAR(30),password VARCHAR(30),uemail VARCHAR(30),uprn VARCHAR(30),uphone VARCHAR(30),ubranch VARCHAR(30),ubirthcity VARCHAR(30),uprofile BLOB)");
        db.execSQL("CREATE TABLE admin_master(aid INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),email VARCHAR(30),password VARCHAR(20))");
        db.execSQL("insert into admin_master(name,email,password)values('Admin','admin@gmail.com','admin@123')");
        db.execSQL("CREATE TABLE post(postid INTEGER PRIMARY KEY AUTOINCREMENT,uid INTEGER,pdesc TEXT,pimage BLOB,pdatetime TEXT)");
        db.execSQL("CREATE TABLE comment(cmid INTEGER PRIMARY KEY AUTOINCREMENT,uid INTEGER,postid INTEGER,message TEXT)");
        db.execSQL("CREATE TABLE faculty(fid INTEGER PRIMARY KEY AUTOINCREMENT,fname VARCHAR(30),fpassword VARCHAR(30),femail VARCHAR(30),fcontact INTEGER(10),fbranch VARCHAR(30))");
        db.execSQL("CREATE TABLE messagetab(msgid INTEGER PRIMARY KEY AUTOINCREMENT,suid INTEGER,ruid INTEGER,message TEXT )");
        db.execSQL("CREATE TABLE event(eid INTEGER PRIMARY KEY AUTOINCREMENT,ename VARCHAR(20),edate VARCHAR(25),etime VARCHAR(10),edesc TEXT)");
        db.execSQL("CREATE TABLE material(mid INTEGER PRIMARY KEY AUTOINCREMENT,mname VARCHAR(20),mfile TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert user---------

    public void adduser(String name, String password, String email, String prn, String phone,String branch,String birthcity,byte[] image){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into student values(NULL,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,password);
        statement.bindString(3,email);
        statement.bindString(4,prn);
        statement.bindString(5,phone);
        statement.bindString(6,branch);
        statement.bindString(7,birthcity);
        statement.bindBlob(8,image);
        statement.executeInsert();
    }
    //detailuser
    public Cursor detailuser(String uid){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uid="+uid,null);
    }


    //addfaculty
    public void addfaculty(String name, String password, String email,String phone,String branch){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into faculty values(NULL,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,password);
        statement.bindString(3,email);
        statement.bindString(4,phone);
        statement.bindString(5,branch);
        statement.executeInsert();
    }

    public Cursor showfaculty()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from faculty",null);
    }
    public Cursor branchwise(String q)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery(q,null);
    }
    public Cursor getfaculty(String fid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from faculty where fid="+fid,null);
    }
    public Cursor loginfaculty(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from faculty where femail='"+email+"' and fpassword='"+password+"'",null);
    }

    public void updatefaculty(String fid,String name,String phone,String branch){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="update faculty set fname=?,fpassword=?,fcontact=?,femail=?,fbranch=? where fid="+fid;
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(4,phone);
        statement.bindString(5,branch);
        statement.execute();
    }
    public boolean delfaculty(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long res=db.delete("faculty","fid="+"=?",new String[]{String.valueOf(id)});

        if(res>0)
        {
            return true;
        }
        return false;
    }
    public boolean deluser(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long res=db.delete("student","uid="+"=?",new String[]{String.valueOf(id)});

        if(res>0)
        {
            return true;
        }
        return false;
    }


    public void resetpassword(String email,String password){

        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE student SET password ='"+password+"' WHERE uemail ='"+email+"'";
        db.execSQL(strSQL);
    }
    public Cursor checkBirth(String email,String birthcity){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uemail='"+email+"' and ubirthcity='"+birthcity+"'",null);
    }

    public Cursor loginuser(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uemail='"+email+"' and password='"+password+"'",null);
    }
    //showuser----

    public Cursor showuser(){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student",null);
    }

    public Cursor showmsguser(String uid){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uid!="+uid,null);
    }

    //updateuser
    public void updateuser(String uid,String name, String password, String email, String prn, String phone,String branch,String birthcity,byte[] image){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="UPDATE student SET uname=?,password=?,uemail=?,uprn=?,uphone=?,ubranch=?,ubirthcity=?, uprofile=? where uid="+uid;
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,password);
        statement.bindString(3,email);
        statement.bindString(4,prn);
        statement.bindString(5,phone);
        statement.bindString(6,branch);
        statement.bindString(7,birthcity);
        statement.bindBlob(8,image);
        statement.execute();
    }

    //getid

    public String getMyId(String email)
    {

        String e="";
        Cursor c = loginId(email);
        if(c.getCount()>0)
        {
            int id = c.getColumnIndex("uid");
            while(c.moveToNext())
            {
                e=c.getString(id);

            }
        }
        return e;
    }

    public Cursor loginId(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uemail='"+email+"'",null);
    }
    //getid

    public String getBranchId(String email)
    {

        String e="";
        Cursor c = loginId(email);
        if(c.getCount()>0)
        {
            int id = c.getColumnIndex("ubranch");
            while(c.moveToNext())
            {
                e=c.getString(id);

            }
        }
        return e;
    }


    public Cursor profileuser(String uid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uid="+uid,null);
    }

    public Cursor showparticular(Integer id){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uid="+id,null);
    }
    //login Admin

    public  Cursor loginadmin(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return  db.rawQuery("select * from admin_master where email='"+email+"' and password='"+password+"' ",null);
    }




    //addpost
    public void addpost(String uid,String pdesc,byte[] pimage)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into post values(NULL,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,uid);
        statement.bindString(2,pdesc);
        statement.bindBlob(3,pimage);
        statement.bindString(4,getDateTime());
        statement.executeInsert();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    //showpost
    public Cursor showpost(String q)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery(q,null);

    }

    public boolean addcomment(String uid,String postid,String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("postid",postid);
        contentValues.put("message",msg);
        long res=db.insert("comment",null,contentValues);
        if(res>0)
        {
            return true;
        }
        return false;
    }
    public Cursor showcomment(String q)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery(q,null);

    }
    public void addMessage(String e1,String e2,String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into messagetab values(NULL,?,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,e1);
        statement.bindString(2,e2);
        statement.bindString(3,msg);
        statement.executeInsert();
    }
    public Cursor checkEmail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uemail='"+email+"'",null);
    }
    public Cursor checkPrn(String prn){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uprn='"+prn+"'",null);
    }
    public Cursor checkPhone(String phone){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from student where uphone='"+phone+"'",null);
    }


    public Cursor showMessage(String e1,String e2)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       // return db.rawQuery("select * from messagetab where suid='"+e1+"' and ruid='"+e2+"'",null);
       // return db.rawQuery("select * from messagetab where suid=2 and ruid=1 or suid=1 and ruid=2",null);
        return db.rawQuery("select * from messagetab where suid='"+e2+"' and ruid='"+e1+"' or suid='"+e1+"' and ruid='"+e2+"'",null);
    }

    public void addevent(String name, String edate, String etime,String edesc){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="insert into event values(NULL,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,edate);
        statement.bindString(3,etime);
        statement.bindString(4,edesc);
        statement.executeInsert();
    }

    public Cursor showevent()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from event",null);
    }
    public Cursor getevent(String eid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        return db.rawQuery("select * from event where eid="+eid,null);
    }
    public void updateevent(String eid,String name,String edate,String etime,String edesc){
        SQLiteDatabase db=this.getWritableDatabase();
        String q="update event set ename=?,edate=?,etime=?,edesc=? where eid="+eid;
        SQLiteStatement statement=db.compileStatement(q);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindString(2,edate);
        statement.bindString(3,etime);
        statement.bindString(4,edesc);
        statement.execute();
    }
    public boolean deletvent(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long res=db.delete("event","eid="+"=?",new String[]{String.valueOf(id)});

        if(res>0)
        {
            return true;
        }
        return false;
    }



}

