package com.dmnoky.taxidermy.controller.user;

import com.dmnoky.taxidermy.exception.ObjectNotFoundException;
import com.dmnoky.taxidermy.model.user.Client;
import com.dmnoky.taxidermy.service.user.UserService;
import com.dmnoky.taxidermy.validator.user.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/client")
public class ClientController {
    private ClientValidator validator;
    private UserService<Client> clientService;

    @Autowired
    public void setValidator(ClientValidator validator) {
        this.validator = validator;
    }
    @Autowired
    public void setClientService(UserService<Client> clientService) {
        this.clientService = clientService;
    }

    /* ADD */
    @GetMapping(value = "/add")
    public String addClient(ModelMap model) {
        model.addAttribute("client", new Client());
        model.addAttribute("title", "Новый клиент");
        return "user/client/client_add";
    }

    @PostMapping(value = "/add")
    public String addClient(
            @ModelAttribute("client") Client client,
            BindingResult resultClient, ModelMap model)
    {
        client = validator.validate(client, resultClient, "");
        if (resultClient.hasErrors()) {
            model.addAttribute("title", "Новый клиент");
            return "user/client/client_add";
        }
        clientService.addUser(client);
        return "redirect:/client/id/"+client.getId();
    }

    /* GET */
    @GetMapping(value = "/id/{id}")
    public String getClientById(@PathVariable Long id, ModelMap model) {
        Client client;
        try {
            client = clientService.getUserById(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Client.class.getName());
        }
        model.addAttribute(client);
        model.addAttribute("title", client.getSurname()+" "+client.getName());
        return "user/client/client_display";
    }

    @GetMapping(value = "/article/{article}")
    public String getClientByArticle(@PathVariable String article, ModelMap model) {
        Client client;
        try {
            client = clientService.getUserByArticle(article);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(article, Client.class.getName());
        }
        model.addAttribute(client);
        model.addAttribute("title", client.getSurname()+" "+client.getName());
        return "user/client/client_display";
    }

    @GetMapping(value = "/list/{page}")
    public String selectAll(@PathVariable Integer page, ModelMap model) {
        page = page*10-10;
        model.addAttribute("listClient", clientService.getUsers(page, page+9));
        model.addAttribute("countRows", clientService.getCountRows()/10);
        model.addAttribute("title", "Список клиентов");
        return "user/client/client_list";
    }

    /* UPDATE */
    @GetMapping(value = "/update/{id}")
    public String updateClientById(@PathVariable Long id, ModelMap model) {
        Client client;
        try {
            client = clientService.getUserById(id);
            model.addAttribute("client", client);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Client.class.getName());
        }
        model.addAttribute("title", client.getSurname()+" "+client.getName());
        return "user/client/client_update";
    }

    @PostMapping(value = "/update")
    public String updateClient(
            @ModelAttribute("client") Client client,
            BindingResult resultClient, ModelMap model)
    {
        client = validator.validateUpdate(client, resultClient);
        if (resultClient.hasErrors()) {
            model.addAttribute("title", client.getSurname()+" "+client.getName());
            return "user/client/client_update";
        }
        clientService.updateUser(client, 1);
        return "redirect:/client/id/"+client.getId();
    }

    /* REMOVE */
    @PostMapping(value = "/remove")
    public String removeClient(@RequestParam Long id, ModelMap model) {
        try {
            clientService.removeUser(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Client.class.getName());
        }
        return "redirect:/admin/panel";
    }

    /* OTHER */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        model.addObject("url", request.getRequestURL());
        model.addObject("title", "Клиент");
        model.setViewName("error/object_not_found_exception");
        return model;
    }
}
