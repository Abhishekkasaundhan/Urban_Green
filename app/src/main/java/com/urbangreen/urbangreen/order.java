package com.urbangreen.urbangreen;

import java.util.jar.Attributes;

public class order
{ String Address,PHONE,pin,plantID,Name;

    public order() {
    }



    public order(String Address, String PHONE, String pin, String plantID, String Name){
        this.Address = Address;
        this.PHONE = PHONE;
        this.pin = pin;
        this.plantID=plantID;
        this.Name=Name;


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getPin() {
        return pin;
    }

    public String getPlantID() {
        return plantID;
    }

    public void setPlantID(String plantID) {
        this.plantID = plantID;
    }
}
