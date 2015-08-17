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

    public void setAsText(String massCentreID) {
        if (massCentreID != null && !massCentreID.isEmpty()) {
            Long massCentreId = Long.valueOf(massCentreID);
            MassCentre selectedMassCentre = massCentreService.getMassCentreForIDSM(massCentreId);
            setValue(selectedMassCentre);
        }
    }
}
