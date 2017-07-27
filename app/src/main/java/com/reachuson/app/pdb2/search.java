package com.reachuson.app.pdb2;

/**
 * Created by root on 24/7/17.
 */

public class search {

    //name and address string
    private String name;
    private String Brand;
    private String pin;
    private String number;
    private String qty;
    private String Mrp;
    private String mon;
    private String year;
    private String price;
    private String id;

    public search() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name ){this.name = name;}

    public String getBrand(){return Brand;}
    public void setBrand(String Brand){this.Brand = Brand;}

    public String getqty(){return qty;}
    public void setqty(String qty){this.qty = qty;}

    public String getMrp() {
        return Mrp;
    }
    public void setMrp(String Mrp){this.Mrp = Mrp;}

    public String getmon() {
        return mon;
    }
    public void setmon(String mon){this.mon = mon;}

    public String getprice(){return price;}
    public void setprice(String price){this.price = price;}

    public void setyear(String year) {
        this.year = year;
    }
    public String getyear(){return year;}

    public void setid(String id) {
        this.id = id;
    }
    public String getid(){return id;}





    public String getnumber(){return number;}

    public void setnumber(String Number){this.number = Number;}




}
