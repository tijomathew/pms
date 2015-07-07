package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class FamilyDto {

    private Integer id;
    private String familyID;
    private String familyName;
    private String parishIndia;
    private String dioceseIndia;
    private String dateOfRegistration;
    private String parishLocal;
    private String massCenter;
    private String prayerUnit;
    private String localAddress;
    private String nativeAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFamilyID() {
        return familyID;
    }

    public void setFamilyID(String familyID) {
        this.familyID = familyID;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getParishIndia() {
        return parishIndia;
    }

    public void setParishIndia(String parishIndia) {
        this.parishIndia = parishIndia;
    }

    public String getDioceseIndia() {
        return dioceseIndia;
    }

    public void setDioceseIndia(String dioceseIndia) {
        this.dioceseIndia = dioceseIndia;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getParishLocal() {
        return parishLocal;
    }

    public void setParishLocal(String parishLocal) {
        this.parishLocal = parishLocal;
    }

    public String getMassCenter() {
        return massCenter;
    }

    public void setMassCenter(String massCenter) {
        this.massCenter = massCenter;
    }

    public String getPrayerUnit() {
        return prayerUnit;
    }

    public void setPrayerUnit(String prayerUnit) {
        this.prayerUnit = prayerUnit;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }
}
