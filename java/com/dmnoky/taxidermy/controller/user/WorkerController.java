package com.dmnoky.taxidermy.controller.user;

import com.dmnoky.taxidermy.exception.ObjectNotFoundException;
import com.dmnoky.taxidermy.model.user.Worker;
import com.dmnoky.taxidermy.service.user.UserService;
import com.dmnoky.taxidermy.validator.user.WorkerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    private WorkerValidator validator;
    private UserService<Worker> workerService;

    @Autowired
    public void setValidator(WorkerValidator validator) {
        this.validator = validator;
    }
    @Autowired
    public void setWorkerService(UserService<Worker> workerService) {
        this.workerService = workerService;
    }

    /* ADD */
    @GetMapping(value = "/add")
    public String addWorker(ModelMap model) {
        model.addAttribute("worker", new Worker());
        model.addAttribute("title", "Новый работник");
        return "user/worker/worker_add";
    }

    @PostMapping(value = "/add")
    public String addWorker(
            @ModelAttribute("worker") Worker worker,
            BindingResult resultWorker, ModelMap model)
    {
        worker = validator.validate(worker, resultWorker, "");
        if (resultWorker.hasErrors()) {
            model.addAttribute("title", "Новый работник");
            return "user/worker/worker_add";
        }
        workerService.addUser(worker);
        return "redirect:/worker/id/"+worker.getId();
    }

    /* GET */
    @GetMapping(value = "/id/{id}")
    public String getWorkerById(@PathVariable Long id, ModelMap model) {
        Worker worker;
        try {
            worker = workerService.getUserById(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Worker.class.getName());
        }
        model.addAttribute(worker);
        model.addAttribute("title", worker.getSurname()+" "+worker.getName());
        return "user/worker/worker_display";
    }

    @GetMapping(value = "/article/{article}")
    public String getWorkerByArticle(@PathVariable String article, ModelMap model) {
        Worker worker;
        try {
            worker = workerService.getUserByArticle(article);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(article, Worker.class.getName());
        }
        model.addAttribute(worker);
        model.addAttribute("title", worker.getSurname()+" "+worker.getName());
        return "user/worker/worker_display";
    }

    @GetMapping(value = "/list/{page}")
    public String selectAll(@PathVariable Integer page, ModelMap model) {
        page = page*10-10;
        model.addAttribute("listWorker", workerService.getUsers(page, page+9));
        model.addAttribute("countRows", workerService.getCountRows()/10);
        model.addAttribute("title", "Список работников");
        return "user/worker/worker_list";
    }

    /* UPDATE */
    @GetMapping(value = "/update/{id}")
    public String updateWorkerById(@PathVariable Long id, ModelMap model) {
        Worker worker;
        try {
            worker = workerService.getUserById(id);
            model.addAttribute("worker", worker);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Worker.class.getName());
        }
        model.addAttribute("title", worker.getSurname()+" "+worker.getName());
        return "user/worker/worker_update";
    }

    @PostMapping(value = "/update")
    public String updateWorker(
            @ModelAttribute("worker") Worker worker,
            BindingResult resultWorker, ModelMap model)
    {
        worker = validator.validateUpdate(worker, resultWorker);
        if (resultWorker.hasErrors()) {
            model.addAttribute("title", worker.getSurname()+" "+worker.getName());
            return "user/worker/worker_update";
        }
        workerService.updateUser(worker, 1);
        return "redirect:/worker/id/"+worker.getId();
    }

    /* REMOVE */
    @PostMapping(value = "/remove")
    public String removeWorker(@RequestParam Long id, ModelMap model) {
        try {
            workerService.removeUser(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Worker.class.getName());
        }
        return "redirect:/admin/panel";
    }

    /* OTHER */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        model.addObject("url", request.getRequestURL());
        model.addObject("title", "Работник");
        model.setViewName("error/object_not_found_exception");
        return model;
    }
}
