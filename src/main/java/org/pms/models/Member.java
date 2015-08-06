package org.pms.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.pms.enums.RelationShipInFamily;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    private Long id;

    @Embedded
    @Valid
    @NotNull
    private Person memberAsPerson;

    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "relationship_in_family")
    @Enumerated(EnumType.ORDINAL)
    private RelationShipInFamily relationshipInFamily;

    @Column(name = "date_of_baptism")
    @NotEmpty
    private String dateOfBaptism;

    @Column(name = "date_of_confirmation")
    private String dateOfConfirmation;

    @Column(name = "date_of_first_communion")
    private String dateOfFirstCommunion;

    @Column(name = "date_of_marriage")
    private String dateOfMarriage;

    @Column(name = "date_of_death")
    private String dateOfDeath;

    @Column(name = "church_of_baptism")
    private String churchOfBaptism;

    @Column(name = "country_of_baptism")
    private String countryOfBaptism;

    @Column(name = "baptism_name")
    private String baptismName;

    @Column(name = "minister_of_baptism")
    private String ministerOfBaptism;

    @Column(name = "baptism_god_father")
    private String baptismGodFather;

    @Column(name = "baptism_god_mother")
    private String baptismGodMother;

    @Column(name = "patron_saint")
    private String patronSaint;

    @Column(name = "patron_saint_feast_day")
    private String patronSaintFeastDay;

    @Column(name = "church_of_confirmation")
    private String churchOfConfirmation;

    @Column(name = "country_of_confirmation")
    private String countryOfConfirmation;

    @Column(name = "minister_of_confirmation")
    private String ministerOfConfirmation;

    @Column(name = "confirmation_god_father")
    private String confirmationGodFather;

    @Column(name = "confirmation_god_mother")
    private String confirmationGodMother;

    @Column(name = "church_of_holy_communion")
    private String churchOfHolyCommunion;

    @Column(name = "country_of_holy_communion")
    private String countryOfHolyCommunion;

    @Column(name = "minister_of_holy_communion")
    private String ministerOfHolyCommunion;

    @Column(name = "date_of_betrothal")
    private String dateOfBetrothal;

    @Column(name = "church_of_betrothal")
    private String churchOfBetrothal;

    @Column(name = "country_of_betrothal")
    private String countryOfBetrothal;

    @Column(name = "priest_of_betrothal")
    private String priestOfBetrothal;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "spouse_baptism_name")
    private String spouseBaptismName;

    @Column(name = "spouse_native_parish")
    private String spouseNativeParish;

    @Column(name = "spouse_native_diocese")
    private String spouseNativeDiocese;

    @Column(name = "spouse_father_name")
    private String spouseFatherName;

    @Column(name = "spouse_mother_name")
    private String spouseMotherName;

    @Column(name = "spouse_native_address")
    private String spouseNativeAddress;

    @Column(name = "spouse_nationality")
    private String spouseNationality;

    @Column(name = "betrothal_witness_one")
    private String betrothalWitnessOne;

    @Column(name = "betrothal_witness_two")
    private String betrothalWitnessTwo;

    @Column(name = "church_of_marriage")
    private String churchOfMarriage;

    @Column(name = "priest_of_marriage")
    private String priestOfMarriage;

    @Column(name = "marriage_witness_one")
    private String marriageWitnessOne;

    @Column(name = "marriage_witness_two")
    private String marriageWitnessTwo;

    @Column(name = "place_of_death")
    private String placeOfDeath;

    @Column(name = "funeral_date")
    private String funeralDate;

    @Column(name = "buried_church")
    private String buriedChurch;

    @Column(name = "minister_of_death")
    private String ministerOfDeath;

    @Column(name = "place_of_cemetery")
    private String placeOfCemetery;

    @Column(name = "tomb_no")
    private String tombNo;

    @Column(name = "confession")
    private String confession;

    @Column(name = "communion")
    private String communion;

    @Column(name = "anointing_the_sick")
    private String anointingTheSick;

    @Column(name = "minister_of_anointing_the_sick")
    private String ministerOfAnointingTheSick;

    @Column(name = "registered_date")
    private String registeredDate;

    @Column(name = "family_head")
    private boolean familyHead;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_member_id")
    private Family familyMember;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public Person getMemberAsPerson() {
        return memberAsPerson;
    }

    public void setMemberAsPerson(Person memberAsPerson) {
        this.memberAsPerson = memberAsPerson;
    }

    public Long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    public RelationShipInFamily getRelationshipInFamily() {
        return relationshipInFamily;
    }

    public void setRelationshipInFamily(RelationShipInFamily relationshipInFamily) {
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

    public String getChurchOfBaptism() {
        return churchOfBaptism;
    }

    public void setChurchOfBaptism(String churchOfBaptism) {
        this.churchOfBaptism = churchOfBaptism;
    }

    public String getCountryOfBaptism() {
        return countryOfBaptism;
    }

    public void setCountryOfBaptism(String countryOfBaptism) {
        this.countryOfBaptism = countryOfBaptism;
    }

    public String getBaptismName() {
        return baptismName;
    }

    public void setBaptismName(String baptismName) {
        this.baptismName = baptismName;
    }

    public String getMinisterOfBaptism() {
        return ministerOfBaptism;
    }

    public void setMinisterOfBaptism(String ministerOfBaptism) {
        this.ministerOfBaptism = ministerOfBaptism;
    }

    public String getBaptismGodFather() {
        return baptismGodFather;
    }

    public void setBaptismGodFather(String baptismGodFather) {
        this.baptismGodFather = baptismGodFather;
    }

    public String getBaptismGodMother() {
        return baptismGodMother;
    }

    public void setBaptismGodMother(String baptismGodMother) {
        this.baptismGodMother = baptismGodMother;
    }

    public String getPatronSaint() {
        return patronSaint;
    }

    public void setPatronSaint(String patronSaint) {
        this.patronSaint = patronSaint;
    }

    public String getPatronSaintFeastDay() {
        return patronSaintFeastDay;
    }

    public void setPatronSaintFeastDay(String patronSaintFeastDay) {
        this.patronSaintFeastDay = patronSaintFeastDay;
    }

    public String getChurchOfConfirmation() {
        return churchOfConfirmation;
    }

    public void setChurchOfConfirmation(String churchOfConfirmation) {
        this.churchOfConfirmation = churchOfConfirmation;
    }

    public String getCountryOfConfirmation() {
        return countryOfConfirmation;
    }

    public void setCountryOfConfirmation(String countryOfConfirmation) {
        this.countryOfConfirmation = countryOfConfirmation;
    }

    public String getMinisterOfConfirmation() {
        return ministerOfConfirmation;
    }

    public void setMinisterOfConfirmation(String ministerOfConfirmation) {
        this.ministerOfConfirmation = ministerOfConfirmation;
    }

    public String getConfirmationGodFather() {
        return confirmationGodFather;
    }

    public void setConfirmationGodFather(String confirmationGodFather) {
        this.confirmationGodFather = confirmationGodFather;
    }

    public String getConfirmationGodMother() {
        return confirmationGodMother;
    }

    public void setConfirmationGodMother(String confirmationGodMother) {
        this.confirmationGodMother = confirmationGodMother;
    }

    public String getChurchOfHolyCommunion() {
        return churchOfHolyCommunion;
    }

    public void setChurchOfHolyCommunion(String churchOfHolyCommunion) {
        this.churchOfHolyCommunion = churchOfHolyCommunion;
    }

    public String getCountryOfHolyCommunion() {
        return countryOfHolyCommunion;
    }

    public void setCountryOfHolyCommunion(String countryOfHolyCommunion) {
        this.countryOfHolyCommunion = countryOfHolyCommunion;
    }

    public String getMinisterOfHolyCommunion() {
        return ministerOfHolyCommunion;
    }

    public void setMinisterOfHolyCommunion(String ministerOfHolyCommunion) {
        this.ministerOfHolyCommunion = ministerOfHolyCommunion;
    }

    public String getDateOfBetrothal() {
        return dateOfBetrothal;
    }

    public void setDateOfBetrothal(String dateOfBetrothal) {
        this.dateOfBetrothal = dateOfBetrothal;
    }

    public String getChurchOfBetrothal() {
        return churchOfBetrothal;
    }

    public void setChurchOfBetrothal(String churchOfBetrothal) {
        this.churchOfBetrothal = churchOfBetrothal;
    }

    public String getCountryOfBetrothal() {
        return countryOfBetrothal;
    }

    public void setCountryOfBetrothal(String countryOfBetrothal) {
        this.countryOfBetrothal = countryOfBetrothal;
    }

    public String getPriestOfBetrothal() {
        return priestOfBetrothal;
    }

    public void setPriestOfBetrothal(String priestOfBetrothal) {
        this.priestOfBetrothal = priestOfBetrothal;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseBaptismName() {
        return spouseBaptismName;
    }

    public void setSpouseBaptismName(String spouseBaptismName) {
        this.spouseBaptismName = spouseBaptismName;
    }

    public String getSpouseNativeParish() {
        return spouseNativeParish;
    }

    public void setSpouseNativeParish(String spouseNativeParish) {
        this.spouseNativeParish = spouseNativeParish;
    }

    public String getSpouseNativeDiocese() {
        return spouseNativeDiocese;
    }

    public void setSpouseNativeDiocese(String spouseNativeDiocese) {
        this.spouseNativeDiocese = spouseNativeDiocese;
    }

    public String getSpouseFatherName() {
        return spouseFatherName;
    }

    public void setSpouseFatherName(String spouseFatherName) {
        this.spouseFatherName = spouseFatherName;
    }

    public String getSpouseMotherName() {
        return spouseMotherName;
    }

    public void setSpouseMotherName(String spouseMotherName) {
        this.spouseMotherName = spouseMotherName;
    }

    public String getSpouseNativeAddress() {
        return spouseNativeAddress;
    }

    public void setSpouseNativeAddress(String spouseNativeAddress) {
        this.spouseNativeAddress = spouseNativeAddress;
    }

    public String getSpouseNationality() {
        return spouseNationality;
    }

    public void setSpouseNationality(String spouseNationality) {
        this.spouseNationality = spouseNationality;
    }

    public String getBetrothalWitnessOne() {
        return betrothalWitnessOne;
    }

    public void setBetrothalWitnessOne(String betrothalWitnessOne) {
        this.betrothalWitnessOne = betrothalWitnessOne;
    }

    public String getBetrothalWitnessTwo() {
        return betrothalWitnessTwo;
    }

    public void setBetrothalWitnessTwo(String betrothalWitnessTwo) {
        this.betrothalWitnessTwo = betrothalWitnessTwo;
    }

    public String getChurchOfMarriage() {
        return churchOfMarriage;
    }

    public void setChurchOfMarriage(String churchOfMarriage) {
        this.churchOfMarriage = churchOfMarriage;
    }

    public String getPriestOfMarriage() {
        return priestOfMarriage;
    }

    public void setPriestOfMarriage(String priestOfMarriage) {
        this.priestOfMarriage = priestOfMarriage;
    }

    public String getMarriageWitnessOne() {
        return marriageWitnessOne;
    }

    public void setMarriageWitnessOne(String marriageWitnessOne) {
        this.marriageWitnessOne = marriageWitnessOne;
    }

    public String getMarriageWitnessTwo() {
        return marriageWitnessTwo;
    }

    public void setMarriageWitnessTwo(String marriageWitnessTwo) {
        this.marriageWitnessTwo = marriageWitnessTwo;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getFuneralDate() {
        return funeralDate;
    }

    public void setFuneralDate(String funeralDate) {
        this.funeralDate = funeralDate;
    }

    public String getBuriedChurch() {
        return buriedChurch;
    }

    public void setBuriedChurch(String buriedChurch) {
        this.buriedChurch = buriedChurch;
    }

    public String getMinisterOfDeath() {
        return ministerOfDeath;
    }

    public void setMinisterOfDeath(String ministerOfDeath) {
        this.ministerOfDeath = ministerOfDeath;
    }

    public String getPlaceOfCemetery() {
        return placeOfCemetery;
    }

    public void setPlaceOfCemetery(String placeOfCemetery) {
        this.placeOfCemetery = placeOfCemetery;
    }

    public String getTombNo() {
        return tombNo;
    }

    public void setTombNo(String tombNo) {
        this.tombNo = tombNo;
    }

    public String getConfession() {
        return confession;
    }

    public void setConfession(String confession) {
        this.confession = confession;
    }

    public String getCommunion() {
        return communion;
    }

    public void setCommunion(String communion) {
        this.communion = communion;
    }

    public String getAnointingTheSick() {
        return anointingTheSick;
    }

    public void setAnointingTheSick(String anointingTheSick) {
        this.anointingTheSick = anointingTheSick;
    }

    public String getMinisterOfAnointingTheSick() {
        return ministerOfAnointingTheSick;
    }

    public void setMinisterOfAnointingTheSick(String ministerOfAnointingTheSick) {
        this.ministerOfAnointingTheSick = ministerOfAnointingTheSick;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public boolean getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(boolean familyHead) {
        this.familyHead = familyHead;
    }

    public Family getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(Family familyMember) {
        this.familyMember = familyMember;
    }

    public Long getFamilyNo() {
        return this.familyMember.getFamilyNo();
    }

    public String getFamilyName() {
        return this.familyMember.getFamilyName();
    }

    public String getParishName() {
        return this.familyMember.getFamilyParish().getParishName();
    }

    public String getMassCenterName() {
        return this.familyMember.getFamilyMassCenter().getMassCenterName();
    }

    public String getPrayerUnitName() {
        return this.familyMember.getFamilyPrayerUnit().getPrayerUnitName();
    }
}
