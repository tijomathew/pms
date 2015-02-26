package org.pms.dtos;

/**
 * Created by tijo on 2/12/14.
 */
public class MemberDto {

    private Integer id;
    private Long memberID;
    private String name;
    private String dob;

    public MemberDto(Integer id, Long memberID, String name, String dob) {
        this.id = id;
        this.memberID = memberID;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public Long getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }
}
