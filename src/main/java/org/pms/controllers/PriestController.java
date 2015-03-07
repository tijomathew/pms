package org.pms.controllers;

import org.pms.displaywrappers.PriestWrapper;
import org.pms.dtos.PriestDto;
import org.pms.helpers.GridContainer;
import org.pms.helpers.GridGenerator;
import org.pms.helpers.GridRow;
import org.pms.helpers.JsonBuilder;
import org.pms.models.Priest;
import org.pms.models.User;
import org.pms.serviceImpls.MailServiceImpl;
import org.pms.services.MailService;
import org.pms.services.PriestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the controller of the Priest module.
 * User: tijo
 */

@Controller
public class PriestController {

    @Autowired
    private PriestService priestService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/viewPriest.action", method = RequestMethod.POST)
    public String romeChurchPageDisplay(Model model) {
        Priest formDisplayPriest = new Priest();
        formDisplayPriest.setPriestID("PR" + priestService.getHighestAutoIDSM());
        model.addAttribute("priest", formDisplayPriest);
        model.addAttribute("loginUser", new User());
        model.addAttribute("error", "please errorrrrr");
        //mailService.sendUserCredentials(new User());
        return "login";
    }

    @RequestMapping(value = "/viewPriest.action", method = RequestMethod.GET)
    public String romeChurchPageDisplay1(Model model) {
        Priest formDisplayPriest = new Priest();
        formDisplayPriest.setPriestID("PR" + priestService.getHighestAutoIDSM());
        model.addAttribute("priest", formDisplayPriest);
        model.addAttribute("loginUser", new User());
        model.addAttribute("error", "please errorrrrr");
       // mailService.sendUserCredentials(new User());
        return "login";
    }

    @RequestMapping(value = "/addPriest.action", method = RequestMethod.POST)
    public String addPriest(@ModelAttribute("priest") Priest priest, Model model, @RequestParam(value = "image", required = false) File file) {
        File image = file;
        priestService.addPriestSM(priest);
        model.addAttribute("priest", new Priest());
        return "priest";
    }

    @RequestMapping(value = "/displayPriestGrid.action", method = RequestMethod.GET)
    public
    @ResponseBody
    Object generateJsonDisplayForPriest(@RequestParam(value = "rows",required = false) Integer rows,@RequestParam(value = "page",required = false) Integer page) {
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

        JsonBuilder jsonBuilder = new JsonBuilder();
        return jsonBuilder.convertToJson(resultContainer);
    }

}
