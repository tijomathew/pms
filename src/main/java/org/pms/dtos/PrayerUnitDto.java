package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class PrayerUnitDto {

    private Integer id;
    private Long prayerUnitID;
    private String prayerUnitName;
    private String prayerUnitPlace;
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

    public Long getPrayerUnitID() {
        return prayerUnitID;
    }

    public void setPrayerUnitID(Long prayerUnitID) {
        this.prayerUnitID = prayerUnitID;
    }

    public String getPrayerUnitName() {
        return prayerUnitName;
    }

    public void setPrayerUnitName(String prayerUnitName) {
        this.prayerUnitName = prayerUnitName;
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

    public String getPrayerUnitPlace() {
        return prayerUnitPlace;
    }

    public void setPrayerUnitPlace(String prayerUnitPlace) {
        this.prayerUnitPlace = prayerUnitPlace;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

}
