package org.pms.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Column(name = "na_addressline_one")
    private String addressLineOne;

    @Column(name = "na_addressline_two")
    private String addressLineTwo;

    @Column(name = "na_addressline_three")
    private String addressLineThree;

    @NotEmpty
    @Column(name = "na_postoffice")
    private String postOffice;

    @NotEmpty
    @Column(name = "na_district")
    private String district;

    @Min(value = 6)
    @Max(value = 6)
    @Column(name = "na_pin")
    private Long pin;

    @NotEmpty
    @Column(name = "na_state")
    private String state;

    @NotEmpty
    @Column(name = "na_country")
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

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
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

        if (addressLineOne != null ? !addressLineOne.equals(that.addressLineOne) : that.addressLineOne != null)
            return false;
        if (addressLineThree != null ? !addressLineThree.equals(that.addressLineThree) : that.addressLineThree != null)
            return false;
        if (addressLineTwo != null ? !addressLineTwo.equals(that.addressLineTwo) : that.addressLineTwo != null)
            return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (pin != null ? !pin.equals(that.pin) : that.pin != null) return false;
        if (postOffice != null ? !postOffice.equals(that.postOffice) : that.postOffice != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressLineOne != null ? addressLineOne.hashCode() : 0;
        result = 31 * result + (addressLineTwo != null ? addressLineTwo.hashCode() : 0);
        result = 31 * result + (addressLineThree != null ? addressLineThree.hashCode() : 0);
        result = 31 * result + (postOffice != null ? postOffice.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (pin != null ? pin.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NativeAddress{" +
                "addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", addressLineThree='" + addressLineThree + '\'' +
                ", postOffice='" + postOffice + '\'' +
                ", district='" + district + '\'' +
                ", pin=" + pin +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
