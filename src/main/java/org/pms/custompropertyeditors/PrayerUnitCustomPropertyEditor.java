package org.pms.custompropertyeditors;

import org.pms.models.PrayerUnit;
import org.pms.services.PrayerUnitService;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 31/7/15.
 */
public class PrayerUnitCustomPropertyEditor extends PropertyEditorSupport {

    private final PrayerUnitService prayerUnitService;

    public PrayerUnitCustomPropertyEditor(PrayerUnitService prayerUnitService) {
        super();
        this.prayerUnitService = prayerUnitService;
    }

    public void setAsText(String prayerUnitID) {
        if (prayerUnitID != null && !prayerUnitID.isEmpty()) {
            Long prayerUnitId = Long.valueOf(prayerUnitID);
            PrayerUnit selectedPrayerUnit = prayerUnitService.getPrayerUnitForIDSM(prayerUnitId);
            setValue(selectedPrayerUnit);
        }
    }
}
