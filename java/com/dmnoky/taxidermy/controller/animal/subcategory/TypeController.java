package com.dmnoky.taxidermy.controller.animal.subcategory;

import com.dmnoky.taxidermy.model.animal.subcategory.Type;
import com.dmnoky.taxidermy.service.animal.subcategory.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/type")
public class TypeController {
    private TypeService typeService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(value = "/add")
    public String addType(ModelMap model) {
        model.addAttribute("type", new Type());
        return "animal/type/type_add";
    }

    @PostMapping(value = "/add")
    public String addType(
            @ModelAttribute("type") Type type,
            ModelMap model)
    {
        typeService.addType(type);
        model.addAttribute(type);
        return "redirect:/type/"+type.getId();
    }

    @GetMapping(value = "/{id}")
    public String getTypeById(@PathVariable Long id, ModelMap model) {
        model.addAttribute("type", typeService.getType(id));
        return "animal/type/type_display";
    }

    @GetMapping(value = "/list")
    public String getTypeList(ModelMap model) {
        model.addAttribute("typeList", typeService.getTypes());
        model.addAttribute("title", "Типы товара");
        return "animal/subsidiary/subsidiary_list";
    }

    @GetMapping(value = "/update/{id}")
    public String updateTypeById(@PathVariable Long id, ModelMap model) {
        model.addAttribute("type", typeService.getType(id));
        return "animal/type/type_update";
    }

    @PostMapping(value = "/update")
    public String updateType(
            @ModelAttribute("type") Type type,
            ModelMap model)
    {
        typeService.updateType(type);
        return "redirect:/type/"+type.getId();
    }

    @PostMapping(value = "/remove")
    public String removeType(@RequestParam Long id, ModelMap model) {
        typeService.removeType(id);
        return "redirect:/admin/panel";
    }
}
