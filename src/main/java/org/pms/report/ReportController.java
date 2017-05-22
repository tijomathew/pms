package org.pms.report;

import org.pms.common.PageName;
import org.pms.user.SystemRole;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tijo on 25/03/17.
 */
@Controller
public class ReportController {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @RequestMapping(value = "/viewreport.action", method = RequestMethod.GET)
    public String viewReportPageDisplay(Model model) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);

        return PageName.REPORT.toString();
    }
}
