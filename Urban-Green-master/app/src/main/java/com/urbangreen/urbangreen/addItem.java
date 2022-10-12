package com.urbangreen.urbangreen;

public class addItem {
    String description,plantname,price,sellerPhone,plant_id,PlantLink;
    public addItem(){

    }
    public addItem(String description, String plantname, String price,String sellerPhone,String plant_id,String PlantLink){
        this.description = description;
        this.plantname = plantname;
        this.price = price;
        this.plant_id = plant_id;
        this.sellerPhone = sellerPhone;

        this.PlantLink = PlantLink;



    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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



    public String getPlant_id() { return plant_id; }

    public void setPlant_id(String plant_id) { this.plant_id = plant_id; }

    public String getPlantLink() {
        return PlantLink;
    }

    public void setPlantLink(String plantLink) {
        PlantLink = plantLink;
    }
}
