package org.pms.validators;

import org.pms.daos.MemberDao;
import org.pms.enums.SystemRolesStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

/**
 * Created by tijo on 30/7/15.
 */
public class SystemRoleStatusConverter extends PropertyEditorSupport {

    @Autowired
    private MemberDao memberDao;

    public void setAsText(String text) {
        if (Integer.valueOf(text) == 0) {
            setValue(SystemRolesStatus.ACTIVE);
        } else if (Integer.valueOf(text) == 1) {
            setValue(SystemRolesStatus.BLOCKED);
        } else if (Integer.valueOf(text) == 2) {
            setValue(SystemRolesStatus.DEACTIVE);
        }
    }
}
