package org.pms.models;

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
    @Column(name = "ec_name_address")
    private String nameAddress;

    @NotEmpty
    @Column(name = "ec_phoneno")
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
}
