package Northwind;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
public class Address{
    private String line1 ;
    private String getLine1(){
        return  line1;
    }
    private void setLine1(String line1){
        this.line1 = line1;
    }
    private String line2;
    public String getLine2(){
        return  line2;
    }
    public void setLine2(String line2){
        this.line2 = line2;
    }
    private String city;
    public String getCity(){
        return  city;
    }
    public void setCity(String city){
        this.city = city;
    }
    private String region;
    public String getRegion(){
        return this.region;
    }
    public void setRegion(String region){
        this.region = region;
    }
    private String postalCode;
    public String getPostalCode(){
        return  this.postalCode;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    private String country;
    public String getCountry(){
        return this.country;
    }
    public void setCountry(String country){
        this.country = country;
    }
}
