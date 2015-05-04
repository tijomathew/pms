package org.pms.serviceImpls;

import org.pms.daos.MemberDao;
import org.pms.dtos.MemberDto;
import org.pms.models.Member;
import org.pms.models.Person;
import org.pms.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation for the Member Service contract.
 * User: tijo
 */

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean addMemberSM(Member member) {
        memberDao.addMemberDM(member);
        return true;
    }

    @Override
    public List<Member> getAllMember() {
        return memberDao.getAllMembers();
    }

    @Override
    public List<MemberDto> createMemberDto(List<Member> memberList) throws IllegalArgumentException {
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>(memberList.size());
        if (!memberList.isEmpty()) {
            Integer uniqueId = 0;
            for (Member member : memberList) {
                Person personIdentity = member.getMemberAsPerson();
                StringBuilder name = new StringBuilder(personIdentity.getSalutation() + " " + personIdentity.getFirstName() + " " + personIdentity.getLastName());
                MemberDto memberDto = new MemberDto(uniqueId, member.getId(), name.toString(), personIdentity.getDateOfBirth(),personIdentity.getPlaceOfBirth(),personIdentity.getGender(),personIdentity.getNationality(),personIdentity.getJobDetails(),personIdentity.getPersonalStatus(),personIdentity.getBloodGroup(),personIdentity.getCarNumber(),personIdentity.getLifeStatus(),personIdentity.getPersonalRemarks(),member.getPiousAssociation(),member.getSundayCatechism(),member.getSacramentalLife(),member.getChurchRemarks(),personIdentity.getEmail(),personIdentity.getMobileNo(),personIdentity.getLandLine(),personIdentity.getFaxNo(),member.getDateOfBaptism(),member.getChurchOfBaptism(),member.getCountryOfBaptism(),member.getBaptismName(),member.getMinisterOfBaptism(),member.getBaptismGodFather(),member.getBaptismGodMother(),member.getPatronSaint(),member.getPatronSaintFeastDay(),member.getDateOfConfirmation(),member.getChurchOfConfirmation(),member.getCountryOfConfirmation(),member.getMinisterOfConfirmation(),member.getConfirmationGodFather(),member.getConfirmationGodMother(),member.getDateOfFirstCommunion(),member.getChurchOfHolyCommunion(),member.getCountryOfHolyCommunion(),member.getMinisterOfHolyCommunion(),member.getDateOfBetrothal(),member.getChurchOfBetrothal(),member.getCountryOfBetrothal(),member.getPriestOfBetrothal(),member.getSpouseName(),member.getSpouseBaptismName(),member.getSpouseNativeParish(),member.getSpouseNativeDiocese(),member.getSpouseFatherName(),member.getSpouseMotherName(),member.getSpouseNativeAddress(),member.getSpouseNationality(),member.getBetrothalWitnessOne(),member.getBetrothalWitnessTwo(),member.getDateOfMarriage(),member.getChurchOfMarriage(),member.getPriestOfMarriage(),member.getMarriageWitnessOne(),member.getMarriageWitnessTwo(),member.getDateOfDeath(),member.getPlaceOfDeath(),member.getFuneralDate(),member.getBuriedChurch(),member.getMinisterOfDeath(),member.getPlaceOfCemetery(),member.getFuneralDate(),member.getBuriedChurch(),member.getMinisterOfDeath(),member.getPlaceOfCemetery(),member.getTombNo(),member.getConfession(),member.getCommunion(),member.getAnointingTheSick(),member.getMinisterOfAnointingTheSick());
                memberDtoList.add(memberDto);
                uniqueId += 1;
            }
        } else {
            throw new IllegalArgumentException("Member List cannot be an empty List!!!...");
        }
        return memberDtoList;
    }

    @Override
    public Long getMemberCountForFamily(Long familyId) {
        return memberDao.getMemberCountForFamily(familyId);
    }
}
