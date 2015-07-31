package org.pms.custompropertyeditors;

import org.pms.models.Family;
import org.pms.services.FamilyService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 31/7/15.
 */
public class FamilyCustomPropertyEditor extends PropertyEditorSupport {

    private final FamilyService familyService;

    public FamilyCustomPropertyEditor(FamilyService familyService) {
        this.familyService = familyService;
    }

    public void setAsText(String familyID) {
        if (familyID != null && !familyID.isEmpty()) {
            Long familyId = Long.valueOf(familyID);
            Family selectedFamily = familyService.getFamilyForID(familyId);
            setValue(selectedFamily);
        }
    }
}
