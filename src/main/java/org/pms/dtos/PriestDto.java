package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class PriestDto {

    private Integer id;
    private Long priestID;
    private String name;
    private String designation;

    public PriestDto(Integer id, Long priestID, String name, String designation) {
        this.id = id;
        this.priestID = priestID;
        this.name = name;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public Long getPriestID() {
        return priestID;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }


}
