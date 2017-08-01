package com.reachuson.app.pdb2;

/**
 * Created by root on 24/7/17.
 */

public class user {

    //name and address string
    private String name,mName,Prod,Qty,Mrp,Price,Mon,Year,Tax,state;
    private String Instname,lcn;
    private String pin;
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

    public String getInst() {
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




    public String getnumber(){return number;}

    public void setnumber(String Number){this.number = Number;}




}
