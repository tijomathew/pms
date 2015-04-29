package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class PrayerUnitDto {

    private Integer id;
    private Long wardID;
    private String wardCode;
    private String wardName;
    private String wardPlace;
    private String parishName;
    private String massCenterName;
    private String priestNames;
    private String localAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWardID() {
        return wardID;
    }

    public void setWardID(Long wardID) {
        this.wardID = wardID;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
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

    public String getPriestNames() {
        return priestNames;
    }

    public void setPriestNames(String priestNames) {
        this.priestNames = priestNames;
    }

    public String getWardPlace() {
        return wardPlace;
    }

    public void setWardPlace(String wardPlace) {
        this.wardPlace = wardPlace;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }
}
