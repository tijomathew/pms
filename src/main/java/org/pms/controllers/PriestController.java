package org.pms.controllers;

import org.pms.constants.PageNames;
import org.pms.displaywrappers.PriestWrapper;
import org.pms.dtos.PriestDto;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MailService mailService;

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
    public String addPriest(@ModelAttribute("priest") Priest priest, Model model, @RequestParam(value = "image", required = false) File file) {
        File image = file;
        Parish mappedParish = parishService.getParishForIDSM(priest.getParishId());
        priest.setParish(mappedParish);
        mappedParish.addPriestsForParish(priest);

        String attachedStringToID = mappedParish.getParishID()+"-"+"PR";
        Long priestAutoID = priestService.getHighestAutoIDSM();
        if(priestAutoID<10){
            attachedStringToID+="0";
        }

        priest.setPriestID(attachedStringToID+priestAutoID);

        PriestDesignation priestDesignation = new PriestDesignation();
        priestDesignation.setDesignation(priest.getDesignation());
        priestDesignation.setParishId(mappedParish.getParishID());
        priestDesignation.setPriestId(priest.getPriestID());

        priestService.addPriestDesignation(priestDesignation);
        priestService.addPriestSM(priest);

        createPriestFormBackObject(model);

        return "priest";
    }

    @RequestMapping(value = "/displayPriestGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPriest(@RequestParam(value = "rows", required = false) Integer rows, @RequestParam(value = "page", required = false) Integer page) {
        List<Priest> allPriest = priestService.getAllPriestSM();
        List<PriestDto> allPriestDtoList = priestService.createPriestDto(allPriest);
        Integer totalPriestsCount = priestService.getTotalCountOfPriestSM().intValue();

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
