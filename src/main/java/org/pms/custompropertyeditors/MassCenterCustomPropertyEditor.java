package org.pms.custompropertyeditors;

import org.pms.models.MassCenter;
import org.pms.services.MassCenterService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 31/7/15.
 */
public class MassCenterCustomPropertyEditor extends PropertyEditorSupport {

    private final MassCenterService massCenterService;

    public MassCenterCustomPropertyEditor(MassCenterService massCenterService) {
        super();
        this.massCenterService = massCenterService;
    }

    public void setAsText(String massCenterID) {
        if (massCenterID != null && !massCenterID.isEmpty()) {
            Long massCenterId = Long.valueOf(massCenterID);
            MassCenter selectedMassCenter = massCenterService.getMassCenterForIDSM(massCenterId);
            setValue(selectedMassCenter);
        }
    }
}
