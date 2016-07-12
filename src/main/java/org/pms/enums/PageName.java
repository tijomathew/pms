package org.pms.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PageName {

    LOGIN, FAMILY, PARISH, MEMBER, PRAYERUNIT, PRIEST, USER, CHANGEPASSWORD, FAMILYWELCOME, EMAILNOTIFICATION;

    @Override
    public String toString() {
        return StringUtils.lowerCase(super.toString());
    }
}
