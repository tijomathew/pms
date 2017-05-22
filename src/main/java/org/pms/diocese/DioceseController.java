package org.pms.diocese;

import org.pms.common.PageName;
import org.pms.user.StatusCode;
import org.pms.user.SystemRole;
import org.pms.common.error.AbstractErrorAndGridHandler;
import org.pms.common.error.CustomResponse;
import org.pms.common.GridRow;
import org.pms.common.JsonBuilder;
import org.pms.common.QueryFormat;
import org.pms.common.RequestResponseHolder;
import org.pms.domain.Diocese;
import org.pms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tijo on 19/03/17.
 */
@Controller
public class DioceseController extends AbstractErrorAndGridHandler {

    @Autowired
    private RequestResponseHolder requestResponseHolder;

    @Autowired
    private DioceseService dioceseService;

    @RequestMapping(value = "/viewdiocese.action", method = RequestMethod.GET)
    public String viewDiocesePageDisplay(Model modelMap) {

        dioceseService.createDioceseFormBackObject(modelMap);

        return PageName.DIOCESE.toString();
    }

    @RequestMapping(value = "/adddiocese.action", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomResponse addDiocese(@ModelAttribute("diocese") @Valid Diocese diocese, BindingResult result){

    if(!result.hasErrors()){
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        synchronized (this){
            //Checking whether it is Diocese Admin or not.
            //One Diocese admin cannot create another one.
            //Only Admin can create another Diocese
            if (currentUser.getSystemRole() != SystemRole.DIOCESE_ADMIN){
                dioceseService.setDioceseNumber(diocese);
                dioceseService.addDiocese(diocese);
                customResponse = createSuccessMessage(StatusCode.SUCCESS, diocese.getDioceseName(), SUCCESS_MESSAGE_DISPLAY);
            }else{
                customResponse = createErrorMessage(StatusCode.FAILURE, currentUser.getEmail(), "cannot add Diocese as a Diocese admin in the system.");
            }
        }

    }else{
        customResponse = createValidationErrorMessage(StatusCode.FAIL, result.getFieldErrors());
    }


        return customResponse;
    }

    //To display data on jqgrid

    @RequestMapping(value = "displaydiocesegrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForDiocese(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sord", required = false) String sortOrder, @RequestParam(value = "sidx", required = false) String sortIndexColumnName) {
        User currentUser = requestResponseHolder.getAttributeFromSession(SystemRole.PMS_CURRENT_USER.toString(), User.class);
        List<Diocese> allDioceseForUserRole = dioceseService.getAllDioceseForUserRole(currentUser);
        Integer totalRows = allDioceseForUserRole.size();
        QueryFormat formatter = QueryFormat.getQueryFormatter(sortOrder);

        List<Diocese> allDioceseSubList = new ArrayList<>();

        if (totalRows > 0) {
            if (!formatter.equals(QueryFormat.NONE)) {
                Collections.sort(allDioceseForUserRole, formatter.by(sortIndexColumnName, Diocese.class));
            }
            allDioceseSubList = JsonBuilder.generateSubList(page, rows, totalRows, allDioceseForUserRole);
        }

        List<GridRow> dioceseGridRows = new ArrayList<GridRow>(allDioceseForUserRole.size());
        if (!allDioceseSubList.isEmpty()) {
            dioceseGridRows = allDioceseSubList.stream().map(diocese -> new DioceseWrapper(diocese)).collect(Collectors.toList());
        }

        return JsonBuilder.convertToJson(createGridContent(totalRows, page, rows, dioceseGridRows));
    }

}
