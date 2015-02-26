package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class ParishDto {

    private Integer id;
    private Long parishID;
    private String parishName;
    private String riteName;
    private String archDioceseName;
    private String dioceseName;
    private String foraneName;

    public ParishDto(Integer id, Long parishID, String parishName, String riteName, String archDioceseName, String dioceseName, String foraneName) {
        this.id = id;
        this.parishID = parishID;
        this.parishName = parishName;
        this.riteName = riteName;
        this.archDioceseName = archDioceseName;
        this.dioceseName = dioceseName;
        this.foraneName = foraneName;
    }

    public Integer getId() {
        return id;
    }

    public Long getParishID() {
        return parishID;
    }

    public String getParishName() {
        return parishName;
    }

    public String getRiteName() {
        return riteName;
    }

    public String getArchDioceseName() {
        return archDioceseName;
    }

    public String getDioceseName() {
        return dioceseName;
    }

    public String getForaneName() {
        return foraneName;
    }
}
