package org.pms.custompropertyeditors;

import org.pms.models.MassCentre;
import org.pms.services.MassCentreService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 31/7/15.
 */
public class MassCentreCustomPropertyEditor extends PropertyEditorSupport {

    private final MassCentreService massCentreService;

    public MassCentreCustomPropertyEditor(MassCentreService massCentreService) {
        super();
        this.massCentreService = massCentreService;
    }

    public void setAsText(String massCenterID) {
        if (massCenterID != null && !massCenterID.isEmpty()) {
            Long massCenterId = Long.valueOf(massCenterID);
            MassCentre selectedMassCentre = massCentreService.getMassCenterForIDSM(massCenterId);
            setValue(selectedMassCentre);
        }
    }
}
