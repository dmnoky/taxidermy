package com.dmnoky.taxidermy.controller.product;

import com.dmnoky.taxidermy.exception.ObjectNotFoundException;
import com.dmnoky.taxidermy.model.product.Product;
import com.dmnoky.taxidermy.service.product.ProductService;
import com.dmnoky.taxidermy.validator.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductValidator validator;
    private ProductService productService;

    @Autowired
    public void setValidator(ProductValidator validator) {
        this.validator = validator;
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /* ADD */
    @GetMapping(value = "/add")
    public String addProduct(ModelMap model) {
        model.addAttribute("product", new Product());
        model.addAttribute("title", "Новый продукт");
        return "product/product_add";
    }

    @PostMapping(value = "/add")
    public String addProduct(
            @ModelAttribute("product") Product product,
            BindingResult resultProduct, ModelMap model)
    {
        product = validator.validate(product, resultProduct, "");
        if (resultProduct.hasErrors()) return "product/product_add";
        productService.addProduct(product);
        return "redirect:/product/"+product.getId();
    }

    /* GET */
    @GetMapping(value = "/{id}")
    public String getProductById(@PathVariable Long id, ModelMap model) {
        Product product;
        try {
            product = productService.getProductById(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Product.class.getName());
        }
        model.addAttribute(product);
        model.addAttribute("title", product.getName());
        return "product/product_display";
    }

    @GetMapping(value = "/list/{page}")
    public String selectAll(@PathVariable Integer page, ModelMap model) {
        page = page*10-10;
        model.addAttribute("listProduct", productService.getProducts(page, page+9));
        model.addAttribute("countRows", productService.getCountRows()/10);
        model.addAttribute("title", "Продукты");
        return "product/product_list";
    }

    /* UPDATE */
    @GetMapping(value = "/update/{id}")
    public String updateProductById(@PathVariable Long id, ModelMap model) {
        Product product;
        try {
            product = productService.getProductById(id);
            model.addAttribute("product", product);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Product.class.getName());
        }
        model.addAttribute("title", product.getName());
        return "product/product_update";
    }

    @PostMapping(value = "/update")
    public String updateProduct(
            @ModelAttribute("product") Product product,
            BindingResult resultProduct, ModelMap model)
    {
        product = validator.validate(product, resultProduct, "");
        if (resultProduct.hasErrors()) {
            model.addAttribute("title", product.getName());
            return "product/product_update";
        }
        productService.updateProduct(product);
        return "redirect:/product/"+product.getId();
    }

    /* REMOVE */
    @PostMapping(value = "/remove")
    public String removeProduct(@RequestParam Long id, ModelMap model) {
        try {
            productService.removeProduct(id);
        } catch (org.hibernate.ObjectNotFoundException e) {
            throw new ObjectNotFoundException(id, Product.class.getName());
        }
        return "redirect:/admin/panel";
    }

    /* OTHER */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception", ex);
        model.addObject("url", request.getRequestURL());
        model.addObject("title", "Продукт");
        model.setViewName("error/object_not_found_exception");
        return model;
    }
}
