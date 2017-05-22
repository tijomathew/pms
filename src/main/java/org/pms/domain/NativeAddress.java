package org.pms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * This class describes various attributes of person's native address.
 * It contains various getters and setters of the native address.
 * <p/>
 * User: tijo
 */
@Embeddable
public class NativeAddress implements Serializable {

    private static final long serialVersionUID = -3708545110602581311L;

    @NotEmpty
    @Column(name = "na_addressline_one", nullable = false)
    private String addressLineOne;

    @Column(name = "na_addressline_two")
    private String addressLineTwo;

    @Column(name = "na_addressline_three")
    private String addressLineThree;

    @NotEmpty
    @Column(name = "na_postoffice", nullable = false)
    private String postOffice;

    @NotEmpty
    @Column(name = "na_district", nullable = false)
    private String district;

    @NotEmpty
    @Pattern(regexp = "(^[0-9]{6,6}$)")
    @Column(name = "na_pin", nullable = false)
    private String pin;

    @NotEmpty
    @Column(name = "na_state", nullable = false)
    private String state;

    @NotEmpty
    @Column(name = "na_country", nullable = false)
    private String country = "India";

    public NativeAddress() {
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

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

        NativeAddress that = (NativeAddress) o;

        if (!addressLineOne.equals(that.addressLineOne)) return false;
        if (!country.equals(that.country)) return false;
        if (!district.equals(that.district)) return false;
        if (!pin.equals(that.pin)) return false;
        if (!postOffice.equals(that.postOffice)) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLineOne.hashCode();
        result = 31 * result + postOffice.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + pin.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressLineOne", addressLineOne)
                .append("addressLineTwo", addressLineTwo)
                .append("addressLineThree", addressLineThree)
                .append("postOffice", postOffice)
                .append("district", district)
                .append("pin", pin)
                .append("state", state)
                .append("country", country)
                .toString();
    }
}
