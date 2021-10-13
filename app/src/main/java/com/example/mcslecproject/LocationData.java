package com.example.mcslecproject;

public class LocationData {
    String attractionId;
    int attractionImage;
    String attractionName;
    String attractionDescription;
    String attractionLocationStreet;
    String attractionSchedule;
    String attractionPhone;
    String attractionWebsite;
    String attractionLongitude;
    String attractionLatitude;

    public LocationData(String attractionId, int attractionImage, String attractionName, String attractionDescription, String attractionLocationStreet, String attractionSchedule, String attractionPhone, String attractionWebsite, String attractionLongitude, String attractionLatitude) {
        this.attractionId = attractionId;
        this.attractionImage = attractionImage;
        this.attractionName = attractionName;
        this.attractionDescription = attractionDescription;
        this.attractionLocationStreet = attractionLocationStreet;
        this.attractionSchedule = attractionSchedule;
        this.attractionPhone = attractionPhone;
        this.attractionWebsite = attractionWebsite;
        this.attractionLongitude = attractionLongitude;
        this.attractionLatitude = attractionLatitude;
    }

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public int getAttractionImage() {
        return attractionImage;
    }

    public void setAttractionImage(int attractionImage) {
        this.attractionImage = attractionImage;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionDescription() {
        return attractionDescription;
    }

    public void setAttractionDescription(String attractionDescription) {
        this.attractionDescription = attractionDescription;
    }

    public String getAttractionLocationStreet() {
        return attractionLocationStreet;
    }

    public void setAttractionLocationStreet(String attractionLocationStreet) {
        this.attractionLocationStreet = attractionLocationStreet;
    }

    public String getAttractionSchedule() {
        return attractionSchedule;
    }

    public void setAttractionSchedule(String attractionSchedule) {
        this.attractionSchedule = attractionSchedule;
    }

    public String getAttractionPhone() {
        return attractionPhone;
    }

    public void setAttractionPhone(String attractionPhone) {
        this.attractionPhone = attractionPhone;
    }

    public String getAttractionWebsite() {
        return attractionWebsite;
    }

    public void setAttractionWebsite(String attractionWebsite) {
        this.attractionWebsite = attractionWebsite;
    }

    public String getAttractionLongitude() {
        return attractionLongitude;
    }

    public void setAttractionLongitude(String attractionLongitude) {
        this.attractionLongitude = attractionLongitude;
    }

    public String getAttractionLatitude() {
        return attractionLatitude;
    }

    public void setAttractionLatitude(String attractionLatitude) {
        this.attractionLatitude = attractionLatitude;
    }
}
