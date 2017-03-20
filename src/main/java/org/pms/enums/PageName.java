package org.pms.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * User: Cufa User.
 */
public enum PageName {

    LOGIN, FAMILY, PARISH, MEMBER, PRAYERUNIT, USER, CHANGEPASSWORD, FAMILYWELCOME, EMAILNOTIFICATION, RECEIPT, EXPENSE, WITHDRAWAL, JOURNAL, DEPOSIT;

    @Override
    public String toString() {
        return StringUtils.lowerCase(super.toString());
    }
}
