package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class FamilyDto {

    private Integer id;
    private Long familyID;
    private String familyName;
    private String parishIndia;
    private String dioceseIndia;
    private String parishName;
    private String massCenterName;
    private String wardName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFamilyID() {
        return familyID;
    }

    public void setFamilyID(Long familyID) {
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

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getMassCenterName() {
        return massCenterName;
    }

    public void setMassCenterName(String massCenterName) {
        this.massCenterName = massCenterName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
