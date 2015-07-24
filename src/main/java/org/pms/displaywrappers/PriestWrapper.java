package org.pms.displaywrappers;

import org.apache.commons.beanutils.BeanUtils;
import org.pms.helpers.GridRow;
import org.pms.models.Priest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tijo on 1/12/14.
 */
public class PriestWrapper implements GridRow {

    private Priest priestBean;

    private String[] VALID_BEAN_PROPERTIES = {"priestID", "dateOfOrdination", "feastDay", "heavenlyPatron", "nativeDiocese", "nativeParish", "nativePlace", "priestCardValidity", "ordainedToDiocese", "fatherName", "motherName", "priestStatus", "congregation", "familyName", "priestAsPerson.salutation", "priestAsPerson.firstName", "priestAsPerson.middleName", "priestAsPerson.lastName", "priestAsPerson.dateOfBirth", "priestAsPerson.placeOfBirth", "priestAsPerson.gender", "priestAsPerson.nationality", "priestAsPerson.personalStatus", "priestAsPerson.email", "priestAsPerson.mobileNo", "priestAsPerson.landLine", "priestAsPerson.faxNo", "priestAsPerson.educationQualifications",
            "priestAsPerson.jobDetails", "priestAsPerson.bloodGroup", "priestAsPerson.carNumber", "priestAsPerson.lifeStatus", "priestAsPerson.personalRemarks", "localAddress.addressLineOne", "localAddress.addressLineTwo", "localAddress.addressLineThree", "localAddress.town", "localAddress.county", "localAddress.pin", "localAddress.country", "nativeAddress.addressLineOne", "nativeAddress.addressLineTwo", "nativeAddress.addressLineThree", "nativeAddress.postOffice", "nativeAddress.district", "nativeAddress.pin", "nativeAddress.state", "nativeAddress.country", "emergencyContact.name", "emergencyContact.addressLineOne", "emergencyContact.addressLineTwo", "emergencyContact.addressLineThree", "emergencyContact.mobileNo", "emergencyContact.landLineNo", "emergencyContact.email"};

    public PriestWrapper(Priest priestBean) {
        this.priestBean = priestBean;
    }

    @Override
    public Long getId() {
        return priestBean.getId();
    }

    @Override
    public List<String> getGridRow(Integer type) {
        List<String> convertedResult = new ArrayList<String>();
        try {
            for (int i = 0; i < VALID_BEAN_PROPERTIES.length; i++) {
                if (BeanUtils.getProperty(this.priestBean, VALID_BEAN_PROPERTIES[i]) != null) {
                    convertedResult.add(BeanUtils.getProperty(this.priestBean, VALID_BEAN_PROPERTIES[i]).toString());
                }
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
