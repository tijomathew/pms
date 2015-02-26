package org.pms.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This class describes various attributes of person's emergency contact.
 * It contains various getters and setters of the emergency contact.
 * User: tijo.
 */
@Embeddable
public class EmergencyContact implements Serializable {

    private static final long serialVersionUID = -8670323338285172206L;

    @Column(name = "ec_name")
    private String name;

    @Column(name = "ec_addressline_one")
    private String addressLineOne;

    @Column(name = "ec_addressline_two")
    private String addressLineTwo;

    @Column(name = "ec_addressline_three")
    private String addressLineThree;

    @Column(name = "ec_mobileno")
    private Long mobileNo;

    @Column(name = "ec_landlineno")
    private Long landLineNo;

    @Column(name = "ec_email")
    private String email;

    public EmergencyContact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getLandLineNo() {
        return landLineNo;
    }

    public void setLandLineNo(Long landLineNo) {
        this.landLineNo = landLineNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmergencyContact that = (EmergencyContact) o;

        if (addressLineOne != null ? !addressLineOne.equals(that.addressLineOne) : that.addressLineOne != null)
            return false;
        if (addressLineThree != null ? !addressLineThree.equals(that.addressLineThree) : that.addressLineThree != null)
            return false;
        if (addressLineTwo != null ? !addressLineTwo.equals(that.addressLineTwo) : that.addressLineTwo != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (landLineNo != null ? !landLineNo.equals(that.landLineNo) : that.landLineNo != null) return false;
        if (mobileNo != null ? !mobileNo.equals(that.mobileNo) : that.mobileNo != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (addressLineOne != null ? addressLineOne.hashCode() : 0);
        result = 31 * result + (addressLineTwo != null ? addressLineTwo.hashCode() : 0);
        result = 31 * result + (addressLineThree != null ? addressLineThree.hashCode() : 0);
        result = 31 * result + (mobileNo != null ? mobileNo.hashCode() : 0);
        result = 31 * result + (landLineNo != null ? landLineNo.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmergencyContact{" +
                "name='" + name + '\'' +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", addressLineThree='" + addressLineThree + '\'' +
                ", mobileNo=" + mobileNo +
                ", landLineNo=" + landLineNo +
                ", email='" + email + '\'' +
                '}';
    }
}
