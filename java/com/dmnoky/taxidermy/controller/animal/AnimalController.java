package com.dmnoky.taxidermy.controller.animal;

import com.dmnoky.taxidermy.exception.ObjectNotFoundException;
import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.animal.sub.AnimalListCountTemplate;
import com.dmnoky.taxidermy.service.animal.AnimalService;
import com.dmnoky.taxidermy.service.animal.subcategory.SubsidiaryService;
import com.dmnoky.taxidermy.service.animal.subcategory.TypeService;
import com.dmnoky.taxidermy.validator.animal.AnimalValidator;
import com.dmnoky.taxidermy.validator.other.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    private AnimalValidator validator;
    private AnimalService animalService;
    private TypeService typeService;
    private SubsidiaryService subsidiaryService;
    private FileValidator fileValidator;

    @Autowired
    public void setValidator(AnimalValidator validator) {
        this.validator = validator;
    }
    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    @Autowired
    public void setSubsidiaryService(SubsidiaryService subsidiaryService) {
        this.subsidiaryService = subsidiaryService;
    }
    @Autowired
    public void setFileValidator(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    /* ADD */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/add")
    public String addAnimal(ModelMap model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("typeList", typeService.getTypes());
        model.addAttribute("subsidiaryList", subsidiaryService.getSubsidiaries());
        model.addAttribute("title", "Новый товар");
        return "animal/animal_add";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add")
    public String addAnimal(
            @ModelAttribute("animal") Animal animal, BindingResult resultAnimal,
            @RequestParam("images") MultipartFile[] images, ModelMap model)
    {
        animal = validator.validate(animal, resultAnimal, "");
        Map<String, String> fileErrors = fileValidator.validateImages(images);
        if (resultAnimal.hasErrors() || fileErrors.size() > 0) {
            model.addAttribute("typeList", typeService.getTypes());
            model.addAttribute("subsidiaryList", subsidiaryService.getSubsidiaries());
            model.addAttribute("fileErrors", fileErrors);
            return "animal/animal_add";
        }
        //animal.setImages(Arrays.asList(images));
        animalService.addAnimal(animal);
        return "redirect:/animal/id/"+animal.getId();
    }

    /* GET */
    @PreAuthorize("permitAll()")
    @GetMapping(value = "/id/{id}")
    public String getAnimalById(@PathVariable Long id, ModelMap model) {
        Animal animal;
        try {
            animal = animalService.getAnimalById(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Animal.class.getName());
        }
        model.addAttribute(animal);
        model.addAttribute("title", animal.getName());
        return "animal/animal_display";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/article/{article}")
    public String getAnimalByArticle(@PathVariable String article, ModelMap model) {
        Animal animal;
        try {
            animal = animalService.getAnimalByArticle(article);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(article, Animal.class.getName());
        }
        model.addAttribute(animal);
        model.addAttribute("title", animal.getName());
        return "animal/animal_display";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/list/{page}")
    public String selectAll(@PathVariable Integer page, ModelMap model) {
        page = page*10-10;
        AnimalListCountTemplate result = animalService.getAnimals(page, page+9);
        model.addAttribute("listAnimal", result.getAnimalList());
        model.addAttribute("countRows", result.getTotalListSize()/10+1);
        model.addAttribute("title", "Товары");
        return "animal/animal_list";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/list/sub/{subsidiary}/page/{page}")
    public String selectAllBySubsidiary(@PathVariable Integer page,
                                        @PathVariable String subsidiary,
                                        ModelMap model) {
        page = page*10-10;
        AnimalListCountTemplate result = animalService.getAnimalsBySubsidiary(page, page+9, subsidiary);
        model.addAttribute("listAnimal", result.getAnimalList());
        model.addAttribute("countRows", result.getTotalListSize()/10+1);
        model.addAttribute("title", "Товары");
        return "animal/animal_list";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/list/type/{type}/page/{page}")
    public String selectAllByType(@PathVariable Integer page,
                                  @PathVariable String type,
                                  ModelMap model) {
        page = page*10-10;
        AnimalListCountTemplate result = animalService.getAnimalsByType(page, page+9, type);
        model.addAttribute("listAnimal", result.getAnimalList());
        model.addAttribute("countRows", result.getTotalListSize()/10+1);
        model.addAttribute("title", "Товары");
        return "animal/animal_list";
    }

    /* UPDATE */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public String updateAnimalById(@PathVariable Long id, ModelMap model) {
        Animal animal;
        try {
            animal = animalService.getAnimalById(id);
            model.addAttribute("animal", animal);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Animal.class.getName());
        }
        model.addAttribute("typeList", typeService.getTypes());
        model.addAttribute("subsidiaryList", subsidiaryService.getSubsidiaries());
        model.addAttribute("title", animal.getName());
        return "animal/animal_update";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update")
    public String updateAnimal(
            @ModelAttribute("animal") Animal animal, BindingResult resultAnimal,
            @RequestParam("images") MultipartFile[] images, ModelMap model) {
        animal = validator.validateUpdate(animal, resultAnimal);
        Map<String, String> fileErrors = fileValidator.validateImages(images);
        if (resultAnimal.hasErrors() || fileErrors.size() > 0) {
            model.addAttribute("typeList", typeService.getTypes());
            model.addAttribute("subsidiaryList", subsidiaryService.getSubsidiaries());
            model.addAttribute("fileErrors", fileErrors);
            return "animal/animal_update";
        }
        //animal.setImages(Arrays.asList(images));
        animalService.updateAnimal(animal);
        return "redirect:/animal/id/"+animal.getId();
    }

    /* REMOVE */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/remove")
    public String removeAnimal(@RequestParam Long id, ModelMap model) {
        try {
            animalService.removeAnimal(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Animal.class.getName());
        }
        return "redirect:/admin/panel";
    }

    /* OTHER */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        model.addObject("url", request.getRequestURL());
        model.addObject("title", "Товар");
        model.setViewName("error/object_not_found_exception");
        return model;
    }
}
