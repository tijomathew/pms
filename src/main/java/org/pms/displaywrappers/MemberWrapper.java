package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Member;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class MemberWrapper implements GridRow {

    private Member memberBean;

    private String[] VALID_BEAN_PROPERTIES = {"familyNo", "familyName", "memberNo", "memberAsPerson.fullName", "memberAsPerson.gender", "memberAsPerson.dateOfBirth", "relationshipInFamily",
            "memberAsPerson.salutation", "memberAsPerson.firstName", "memberAsPerson.middleName", "memberAsPerson.lastName", "memberAsPerson.placeOfBirth",
            "memberAsPerson.nationality", "memberAsPerson.personalStatus", "memberAsPerson.email", "memberAsPerson.mobileNo", "memberAsPerson.landLine",
            "memberAsPerson.faxNo", "memberAsPerson.educationQualifications", "memberAsPerson.jobDetails", "memberAsPerson.bloodGroup", "memberAsPerson.carNumber", "memberAsPerson.lifeStatus",
            "memberAsPerson.personalRemarks", "dateOfBaptism", "dateOfConfirmation", "dateOfFirstCommunion", "dateOfMarriage",
            "dateOfDeath", "churchOfBaptism", "countryOfBaptism", "baptismName", "ministerOfBaptism", "baptismGodFather",
            "baptismGodMother", "patronSaint", "patronSaintFeastDay", "churchOfConfirmation", "countryOfConfirmation", "ministerOfConfirmation", "confirmationGodFather", "confirmationGodMother", "churchOfHolyCommunion", "countryOfHolyCommunion",
            "ministerOfHolyCommunion", "dateOfBetrothal", "churchOfBetrothal", "countryOfBetrothal", "priestOfBetrothal", "spouseName", "spouseBaptismName", "spouseNativeParish", "spouseNativeDiocese", "spouseFatherName",
            "spouseMotherName", "spouseNativeAddress", "spouseNationality", "betrothalWitnessOne", "betrothalWitnessTwo", "churchOfMarriage", "priestOfMarriage", "marriageWitnessOne", "marriageWitnessTwo", "placeOfDeath",
            "funeralDate", "buriedChurch", "ministerOfDeath", "placeOfCemetery", "tombNo", "confession", "communion", "anointingTheSick", "ministerOfAnointingTheSick", "familyMember.id", "familyHead", "id", "memberAsPerson.imageBytesAsString","registeredDate"};

    public MemberWrapper(Member memberBean) {
        this.memberBean = memberBean;
    }

    @Override
    public Long getId() {
        return memberBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                String assignedValue = "";
                if (BeanUtils.getProperty(this.memberBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    assignedValue = BeanUtils.getProperty(this.memberBean, VALID_BEAN_PROPERTIES[i]).toString();
                    if (assignedValue.isEmpty()) {
                        assignedValue = "";
                    }
                }
                convertedResult.add(assignedValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return convertedResult;
    }
}
