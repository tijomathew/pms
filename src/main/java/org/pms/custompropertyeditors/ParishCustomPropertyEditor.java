package org.pms.custompropertyeditors;

import org.pms.models.Parish;
import org.pms.services.ParishService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 31/7/15.
 */
public class ParishCustomPropertyEditor extends PropertyEditorSupport {

    private final ParishService parishService;

    public ParishCustomPropertyEditor(ParishService parishService) {
        super();
        this.parishService = parishService;
    }

    public void setAsText(String massCentreID) {
        if (massCentreID != null && !massCentreID.isEmpty()) {
            Long massCentreId = Long.valueOf(massCentreID);
            Parish selectedParish = parishService.getParishForIDSM(massCentreId);
            setValue(selectedParish);
        }
    }
}
