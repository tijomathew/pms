package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class MassCenterDto {

    private Integer id;
    private Long massCenterID;
    private String massCenterName;
    private String parishName;
    private String priestNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMassCenterID() {
        return massCenterID;
    }

    public void setMassCenterID(Long massCenterID) {
        this.massCenterID = massCenterID;
    }

    public String getMassCenterName() {
        return massCenterName;
    }

    public void setMassCenterName(String massCenterName) {
        this.massCenterName = massCenterName;
    }

    public String getParishName() {
        return parishName;
    }

    public void setParishName(String parishName) {
        this.parishName = parishName;
    }

    public String getPriestNames() {
        return priestNames;
    }

    public void setPriestNames(String priestNames) {
        this.priestNames = priestNames;
    }
}
