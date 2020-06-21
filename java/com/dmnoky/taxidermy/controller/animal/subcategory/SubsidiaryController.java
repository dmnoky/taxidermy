package com.dmnoky.taxidermy.controller.animal.subcategory;

import com.dmnoky.taxidermy.model.animal.subcategory.Subsidiary;
import com.dmnoky.taxidermy.service.animal.subcategory.SubsidiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subsidiary")
public class SubsidiaryController {
    private SubsidiaryService subsidiaryService;

    @Autowired
    public void setSubsidiaryService(SubsidiaryService subsidiaryService) { this.subsidiaryService = subsidiaryService; }

    @GetMapping(value = "/add")
    public String addSubsidiary(ModelMap model) {
        model.addAttribute("subsidiary", new Subsidiary());
        model.addAttribute("title", "Новое семейство");
        return "animal/subsidiary/subsidiary_add";
    }

    @PostMapping(value = "/add")
    public String addSubsidiary(
            @ModelAttribute("subsidiary") Subsidiary subsidiary,
            ModelMap model)
    {
        subsidiaryService.addSubsidiary(subsidiary);
        return "redirect:/subsidiary/"+subsidiary.getId();
    }

    @GetMapping(value = "/{id}")
    public String getSubsidiaryById(@PathVariable Long id, ModelMap model) {
        Subsidiary subsidiary = subsidiaryService.getSubsidiary(id);
        model.addAttribute("subsidiary", subsidiary);
        model.addAttribute("title", subsidiary.getName());
        return "animal/subsidiary/subsidiary_display";
    }

    @GetMapping(value = "/list")
    public String getSubsidiaryList(ModelMap model) {
        model.addAttribute("subsidiaryList", subsidiaryService.getSubsidiaries());
        model.addAttribute("title", "Список семейств");
        return "animal/subsidiary/subsidiary_list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateSubsidiaryById(@PathVariable Long id, ModelMap model) {
        Subsidiary subsidiary = subsidiaryService.getSubsidiary(id);
        model.addAttribute("subsidiary", subsidiary);
        model.addAttribute("title", subsidiary.getName());
        return "animal/subsidiary/subsidiary_update";
    }

    @PostMapping(value = "/update")
    public String updateSubsidiary(
            @ModelAttribute("subsidiary") Subsidiary subsidiary,
            ModelMap model)
    {
        subsidiaryService.updateSubsidiary(subsidiary);
        return "redirect:/subsidiary/"+subsidiary.getId();
    }

    @PostMapping(value = "/remove")
    public String removeSubsidiary(@RequestParam Long id, ModelMap model) {
        subsidiaryService.removeSubsidiary(id);
        return "redirect:/admin/panel";
    }
}
