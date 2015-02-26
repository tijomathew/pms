package org.pms.models;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.io.Serializable;

/**
 * This class describes various attributes of person's local address.
 * It contains various getters and setters of the local address.
 * <p/>
 * User: tijo
 */
@Embeddable
public class LocalAddress implements Serializable {

    private static final long serialVersionUID = -3368576220015835720L;

    @Column(name = "la_addressline_one")
    private String addressLineOne;

    @Column(name = "la_addressline_two")
    private String addressLineTwo;

    @Column(name = "la_addressline_three")
    private String addressLineThree;

    @Column(name = "la_town")
    private String town;

    @Column(name = "la_county")
    private String county;

    @Column(name = "la_pin")
    private Long pin;

    @Column(name = "la_country")
    private String country;

    public LocalAddress() {
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalAddress that = (LocalAddress) o;

        if (addressLineOne != null ? !addressLineOne.equals(that.addressLineOne) : that.addressLineOne != null)
            return false;
        if (addressLineThree != null ? !addressLineThree.equals(that.addressLineThree) : that.addressLineThree != null)
            return false;
        if (addressLineTwo != null ? !addressLineTwo.equals(that.addressLineTwo) : that.addressLineTwo != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (pin != null ? !pin.equals(that.pin) : that.pin != null) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLineOne != null ? addressLineOne.hashCode() : 0;
        result = 31 * result + (addressLineTwo != null ? addressLineTwo.hashCode() : 0);
        result = 31 * result + (addressLineThree != null ? addressLineThree.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocalAddress{" +
                "addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", addressLineThree='" + addressLineThree + '\'' +
                ", town='" + town + '\'' +
                ", county='" + county + '\'' +
                ", pin=" + pin +
                ", country='" + country + '\'' +
                '}';
    }
}
