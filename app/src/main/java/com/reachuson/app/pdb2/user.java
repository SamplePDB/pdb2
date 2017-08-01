package com.reachuson.app.pdb2;

/**
 * Created by root on 24/7/17.
 */

public class user {

    private String name,mName,Prod,Qty,Mrp,Price,Mon,Year,Tax,state;
    private String Instname,lcn;
    private String pin,refcode;
    private String number;

    public user() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getinst() {
        return Instname;
    }

    public void setInstname(String Instname) {
        this.Instname = Instname;
    }

    public String getpin() {
        return pin;
    }

    public void setpin(String pin) {
        this.pin = pin;
    }

    public String getstate() {
        return state;
    }

    public void setstate(String state) {
        this.state = state;
    }

    public String getlcn() {
        return lcn;
    }

    public void setlcn(String lcn) {
        this.lcn = lcn;
    }

    public String getcode() {
        return refcode;
    }

    public void setcode(String refcode) {
        this.refcode = refcode;
    }




    public String getnumber(){return number;}

    public void setnumber(String Number){this.number = Number;}




}
