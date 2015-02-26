package org.pms.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class describes the various attributes of the member of the family.
 * It contains various getters and setters of the attributes of the member.
 * It contains relationship with family where member belongs to.
 * <p/>
 * User: tijo
 */

@Entity
@Table(name = "member_details")
public class Member implements Serializable {

    private static final long serialVersionUID = 7607498012522751092L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_auto_id")
    private long id;

    @Embedded
    private Person memberAsPerson;

    @Column(name = "member_id")
    private String memberID;

    @Column(name = "relationship_in_family")
    private String relationshipInFamily;

    @Column(name = "date_of_baptism")
    private String dateOfBaptism;

    @Column(name = "date_of_confirmation")
    private String dateOfConfirmation;

    @Column(name = "date_of_first_communion")
    private String dateOfFirstCommunion;

    @Column(name = "date_of_marriage")
    private String dateOfMarriage;

    @Column(name = "date_of_death")
    private String dateOfDeath;

    @Column(name = "pious_association")
    private String piousAssociation;

    @Column(name = "sunday_catechism")
    private String sundayCatechism;

    @Column(name = "sacramental_life")
    private String sacramentalLife;

    @Column(name = "church_remarks")
    private String churchRemarks;

    @Transient
    private Long familyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_member_id")
    private Family familyMember;

    public Member() {
    }

    public long getId() {
        return id;
    }

    public Person getMemberAsPerson() {
        return memberAsPerson;
    }

    public void setMemberAsPerson(Person memberAsPerson) {
        this.memberAsPerson = memberAsPerson;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getRelationshipInFamily() {
        return relationshipInFamily;
    }

    public void setRelationshipInFamily(String relationshipInFamily) {
        this.relationshipInFamily = relationshipInFamily;
    }

    public String getDateOfBaptism() {
        return dateOfBaptism;
    }

    public void setDateOfBaptism(String dateOfBaptism) {
        this.dateOfBaptism = dateOfBaptism;
    }

    public String getDateOfConfirmation() {
        return dateOfConfirmation;
    }

    public void setDateOfConfirmation(String dateOfConfirmation) {
        this.dateOfConfirmation = dateOfConfirmation;
    }

    public String getDateOfFirstCommunion() {
        return dateOfFirstCommunion;
    }

    public void setDateOfFirstCommunion(String dateOfFirstCommunion) {
        this.dateOfFirstCommunion = dateOfFirstCommunion;
    }

    public String getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(String dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPiousAssociation() {
        return piousAssociation;
    }

    public void setPiousAssociation(String piousAssociation) {
        this.piousAssociation = piousAssociation;
    }

    public String getSundayCatechism() {
        return sundayCatechism;
    }

    public void setSundayCatechism(String sundayCatechism) {
        this.sundayCatechism = sundayCatechism;
    }

    public String getSacramentalLife() {
        return sacramentalLife;
    }

    public void setSacramentalLife(String sacramentalLife) {
        this.sacramentalLife = sacramentalLife;
    }

    public String getChurchRemarks() {
        return churchRemarks;
    }

    public void setChurchRemarks(String churchRemarks) {
        this.churchRemarks = churchRemarks;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Family getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Family familyMember) {
        this.familyMember = familyMember;
    }
}
