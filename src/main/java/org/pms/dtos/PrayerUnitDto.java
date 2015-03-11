package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class PrayerUnitDto {

    private Integer id;
    private Long wardID;
    private String wardName;
    private String parishName;
    private String massCenterName;
    private String priestNames;

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
}
