package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.dtos.MemberDto;
import org.pms.helpers.GridRow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class MemberWrapper implements GridRow {

    private MemberDto memberDto;

    private String[] VALID_BEAN_PROPERTIES = {"memberID", "name", "dob","placeOfBirth","gender","nationality","jobDetails","personalStatus","bloodGroup","carNumber","liveStatus","personalRemarks","piousAssociation","sundayCatechism","sacramentalLife","churchRemarks","email","mobNo","landLineNo","faxNo","dateOfBaptism","churchOfBaptism","countryOfBaptism","baptismName","ministerOfBaptism","baptismGodFather","baptismGodMother","patronSaint","patronSaintFeastDay","dateOfConfirmation","churchOfConfirmation","countryOfConfirmation","ministerOfConfirmation","confirmationGodFather","confirmationGodMother","dateOfFirstCommunion","churchOfHolyCommunion","countryOfHolyCommunion","ministerOfHolyCommunion","dateOfBetrothal","churchOfBetrothal","countryOfBetrothal","priestOfBetrothal","spouseName","spouseBaptismName","spouseNativeParish","spouseNativeDiocese","spouseFatherName","spouseMotherName","spouseNativeAddress","spouseNationality","betrothalWitnessOne","betrothalWitnessTwo","dateOfMarriage","churchOfMarriage","priestOfMarriage","marriageWitnessOne","marriageWitnessTwo","dateOfDeath","placeOfDeath","funeralDate","buriedChurch","ministerOfDeath","placeOfCemetery","tombNo","confession","communion","anointingTheSick","ministerOfAnointingTheSick"};

    public MemberWrapper(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    @Override
    public Integer getId() {
        return memberDto.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                convertedResult.add(BeanUtils.getProperty(this.memberDto, VALID_BEAN_PROPERTIES[i]).toString());
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
