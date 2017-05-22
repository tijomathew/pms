package org.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
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

    @NotEmpty
    @Column(name = "la_addressline_one", nullable = false)
    private String addressLineOne;

    @NotEmpty
    @Column(name = "la_addressline_two", nullable = false)
    private String addressLineTwo;

    @Column(name = "la_addressline_three")
    private String addressLineThree;

    @NotEmpty
    @Column(name = "la_town", nullable = false)
    private String town;

    @NotEmpty
    @Column(name = "la_county", nullable = false)
    private String county;

    @Column(name = "la_postcode")
    private String postCode;

    @NotEmpty
    @Column(name = "la_pin", nullable = false)
    private String pin;

    @NotEmpty
    @Column(name = "la_country", nullable = false)
    private String country = "Ireland";

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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
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

        if (!addressLineOne.equals(that.addressLineOne)) return false;
        if (!addressLineTwo.equals(that.addressLineTwo)) return false;
        if (!country.equals(that.country)) return false;
        if (!county.equals(that.county)) return false;
        if (!pin.equals(that.pin)) return false;
        if (!town.equals(that.town)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLineOne.hashCode();
        result = 31 * result + addressLineTwo.hashCode();
        result = 31 * result + town.hashCode();
        result = 31 * result + county.hashCode();
        result = 31 * result + pin.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressLineOne", addressLineOne)
                .append("addressLineTwo", addressLineTwo)
                .append("addressLineThree", addressLineThree)
                .append("town", town)
                .append("county", county)
                .append("postCode", postCode)
                .append("pin", pin)
                .append("country", country)
                .toString();
    }
}
