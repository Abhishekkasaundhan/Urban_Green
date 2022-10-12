package com.urbangreen.urbangreen.Models;

public class PlantModel {

    private String plant_id;
    private String description;
    private String mImageUri,plantname,price,sellerPhone,plantLink;

    public String getPlant_id() {
        return plant_id;
    }

    public void setPlant_id(String plant_id) {
        this.plant_id = plant_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getPlantname() {
        return plantname;
    }

    public void setPlantname(String plantname) {
        this.plantname = plantname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getPlantLink() {
        return plantLink;
    }

    public void setPlantLink(String plantLink) {
        this.plantLink = plantLink;
    }
}
