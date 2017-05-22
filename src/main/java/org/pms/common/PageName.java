package org.pms.common;

import org.apache.commons.lang3.StringUtils;

/**
 * User: Cufa User.
 */
public enum PageName {

    LOGIN, FAMILY, PARISH, MEMBER, PRAYERUNIT, USER, CHANGEPASSWORD, FAMILYWELCOME, EMAILNOTIFICATION, RECEIPT, PAYMENT, WITHDRAWAL, JOURNAL, DEPOSIT,REPORT,REALEXPAYMENT,PROCESSREALEXPAYMENT, DIOCESE;

    @Override
    public String toString() {
        return StringUtils.lowerCase(super.toString());
    }
}
