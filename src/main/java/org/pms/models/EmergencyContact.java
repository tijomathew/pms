package org.pms.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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

    @NotEmpty
    @Column(name = "ec_name_address", nullable = false)
    private String nameAddress;

    @NotEmpty
    @Column(name = "ec_phoneno", nullable = false)
    private String phoneNo;

    @Column(name = "ec_alternative_phoneno")
    private String alternativePhoneNo;

    @Email
    @Column(name = "ec_email")
    private String email;

    public EmergencyContact() {
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAlternativePhoneNo() {
        return alternativePhoneNo;
    }

    public void setAlternativePhoneNo(String alternativePhoneNo) {
        this.alternativePhoneNo = alternativePhoneNo;
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

        if (!nameAddress.equals(that.nameAddress)) return false;
        if (!phoneNo.equals(that.phoneNo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameAddress.hashCode();
        result = 31 * result + phoneNo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nameAddress", nameAddress)
                .append("phoneNo", phoneNo)
                .append("alternativePhoneNo", alternativePhoneNo)
                .append("email", email)
                .toString();
    }
}
