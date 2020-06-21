package com.dmnoky.taxidermy.controller;

import com.dmnoky.taxidermy.model.user.Client;
import com.dmnoky.taxidermy.model.user.Worker;
import com.dmnoky.taxidermy.service.user.UserService;
import com.dmnoky.taxidermy.validator.user.WorkerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {
    private UserService<Worker> workerService;
    private UserService<Client> clientService;
    private WorkerValidator validator;

    @Autowired
    public void setWorkerService(UserService<Worker> workerService) {
        this.workerService = workerService;
    }
    @Autowired
    public void setClientService(UserService<Client> clientService) {
        this.clientService = clientService;
    }
    @Autowired
    public void setValidator(WorkerValidator validator) {
        this.validator = validator;
    }

    /* REGISTRATION */
    /*@GetMapping(value = "/registration")
    public String registration(ModelMap model) {
        model.addAttribute("worker", new Worker());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("worker") Worker worker,
                               BindingResult bindingResult, ModelMap model) {
        validator.validate(worker, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (this.workerService.addUser(worker)) {
            return "redirect:/login?goodReg";
        }
        return "registration";
    }*/

    /* LOGIN */
    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout,
                        @RequestParam(name = "goodReg", required = false) String goodReg,
                        Model model) {
        if (error != null) model.addAttribute("message", "Логин или пароль некорректны.");
        if (logout != null) model.addAttribute("message", "Вы вышли из системы.");
        if (goodReg != null) model.addAttribute("message", "Вы прошли регистрацию.");
        return "login";
    }

    /* LOGOUT */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = "/")
    public String index(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal==null) return "index";
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User
                    user = (org.springframework.security.core.userdetails.User) principal;
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (authority.toString().equals("ROLE_ADMIN")) {
                    model.addAttribute("listOfTasks", workerService.getUsers());
                    return "index";
                }
            }
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (authority.toString().equals("ROLE_WORKER")) {
                    model.addAttribute("worker", workerService.getUserByArticle(user.getUsername()));
                    return "index";
                }
            }
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (authority.toString().equals("ROLE_USER")) {
                    model.addAttribute("user", clientService.getUserByArticle(user.getUsername()));
                    return "index";
                }
            }
        }

        return "index";
    }
}
