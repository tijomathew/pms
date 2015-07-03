package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.displaywrappers.PriestWrapper;
import org.pms.dtos.PriestDto;
import org.pms.error.CustomErrorMessage;
import org.pms.error.CustomResponse;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.Parish;
import org.pms.models.Priest;
import org.pms.models.PriestDesignation;
import org.pms.models.User;
import org.pms.serviceImpls.MailServiceImpl;
import org.pms.services.MailService;
import org.pms.services.ParishService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the controller of the Priest module.
 * User: tijo
 */

@Controller
public class PriestController {

    @Autowired
    private PriestService priestService;

    @Autowired
    private ParishService parishService;


    @RequestMapping(value = "/viewpriest.action", method = RequestMethod.POST)
    public String priestPageDisplay(Model model) {

        createPriestFormBackObject(model);

        /*model.addAttribute("loginUser", new User());
        model.addAttribute("error", "please errorrrrr");
        mailService.sendUserCredentials(new User());*/

        return PageNames.PRIEST;
    }


    @RequestMapping(value = "/viewpriest.action", method = RequestMethod.GET)
    public String priestStaticPageDisplay(Model model) {

        createPriestFormBackObject(model);

        return PageNames.PRIEST;
    }

    @RequestMapping(value = "/addpriest.action", method = RequestMethod.POST)
    public @ResponseBody
    CustomResponse addPriest(Model model, @ModelAttribute("priest") @Valid Priest priest, BindingResult result) {
        CustomResponse res = null;
        List<CustomErrorMessage> customErrorMessages = new ArrayList<CustomErrorMessage>();

        if (!result.hasErrors()) {
            Parish mappedParish = parishService.getParishForIDSM(priest.getParishId());
            priest.setParish(mappedParish);
            mappedParish.addPriestsForParish(priest);

            String attachedStringToID = mappedParish.getParishID() + "-" + "PR";
            Long priestAutoID = priestService.getHighestAutoIDSM();
            if (priestAutoID < 10) {
                attachedStringToID += "0";
            }

            priest.setPriestID(attachedStringToID + priestAutoID);

            PriestDesignation priestDesignation = new PriestDesignation();
            priestDesignation.setDesignation(priest.getDesignation());
            priestDesignation.setParishId(mappedParish.getParishID());
            priestDesignation.setPriestId(priest.getPriestID());

            priestService.addPriestDesignation(priestDesignation);
            priestService.addPriestSM(priest);

            createPriestFormBackObject(model);
            customErrorMessages.add(new CustomErrorMessage("success", "successfully added"));
            res = new CustomResponse("SUCCESS", customErrorMessages);
        }else {
            List<FieldError> allErrors = result.getFieldErrors();
            for (FieldError objectError : allErrors) {
                customErrorMessages.add(new CustomErrorMessage(objectError.getField(), objectError.getField() + "  " + objectError.getDefaultMessage()));
            }
            res = new CustomResponse("FAIL", customErrorMessages);
        }

        return res;
    }

    @RequestMapping(value = "/displaypriestgrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPriest(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Priest> allPriest = priestService.getAllPriestSM();
        Integer totalPriestsCount = priestService.getTotalCountOfPriestSM().intValue();

        List<PriestDto> allPriestDtoList = priestService.createPriestDto(allPriest);


        List<PriestDto> allUsersSublist = new ArrayList<PriestDto>();
        if (totalPriestsCount > 0) {
            allUsersSublist = JsonBuilder.generateSubList(page, rows, totalPriestsCount.intValue(), allPriestDtoList);
        }

        List<GridRow> priestGridRows = new ArrayList<GridRow>(allPriest.size());
        for (PriestDto priestDto : allUsersSublist) {
            priestGridRows.add(new PriestWrapper(priestDto));
        }

        GridGenerator gridGenerator = new GridGenerator();
        GridContainer resultContainer = gridGenerator.createGridContainer(totalPriestsCount, page, rows, priestGridRows);

        return JsonBuilder.convertToJson(resultContainer);
    }

    private void createPriestFormBackObject(Model model) {
        Priest formDisplayPriest = new Priest();
        model.addAttribute("priest", formDisplayPriest);

        Map<Long, String> parishMap = new HashMap<Long, String>();
        List<Parish> addedParishes = parishService.getAllParish();
        parishMap.put(0l, "--Please Select--");
        for (Parish parish : addedParishes)
            parishMap.put(parish.getId(), parish.getName());

        Map<String, String> priestDesignationsMap = new HashMap<>();
        priestDesignationsMap.put("Please Select", "--Please Select--");
        priestDesignationsMap.put("Supporting Priest", "Supporting Priest");
        priestDesignationsMap.put("Co-Ordinator", "Co-Ordinator");
        priestDesignationsMap.put("Chaplain", "Chaplain");

        model.addAttribute("parishList", parishMap);
        model.addAttribute("priestDesignation", priestDesignationsMap);
    }

}
