package com.dmnoky.taxidermy.validator.animal;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.product.Product;
import com.dmnoky.taxidermy.service.animal.AnimalService;
import com.dmnoky.taxidermy.validator.product.ProductValidator;
import com.dmnoky.taxidermy.validator.user.ClientValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalValidator implements Validator {
    private AnimalService animalService;
    private ProductValidator productValidator;
    private ClientValidator clientValidator;

    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void setProductValidator(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    public void setClientValidator(ClientValidator clientValidator) {
        this.clientValidator = clientValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Animal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        this.validate(target, errors, "");
    }

    public Animal validate(Object target, Errors errors, String property) {
        Animal animal = (Animal) target;

        if (property.length() > 0) property = property + ".";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "article", "Field.notEmpty");
        if (animal.getArticle() != null) {
            try {
                if (animalService.getAnimalByArticle(animal.getArticle()) != null)
                    errors.rejectValue(property + "article", "Duplicate.form.article");
            } catch (org.hibernate.ObjectNotFoundException ignored) {}
        }

        return defaultValidate(animal, errors, property);
    }

    public Animal validateUpdate(Object target, Errors errors) {
        Animal animal = (Animal) target;
        if (animal.getNotes() != null) {
            for (int i = 0; i < animal.getNotes().size(); i++) {
                if (animal.getNotes().get(i).isEmpty()) {
                    animal.getNotes().remove(i);
                    i--;
                }
            }
        }
        return defaultValidate(animal, errors, "");
    }

    public Animal validate(Object target, Errors errors, String property, int step) {
        Animal animal = (Animal) target;

        if (animal.getId() != null) {
            try {
                animal = animalService.getAnimalById(animal.getId());
            } catch (org.hibernate.ObjectNotFoundException e) {
                errors.rejectValue(property + "[" + step + "].id", "NotExist.entity.id");
                return null;
            }
        }

        return animal;
    }

    private Animal defaultValidate(Animal animal, Errors errors, String property) {

        if (animal.getProducts() != null) {
            List<Product> newProductList = new ArrayList<>();
            for (int i = 0; i < animal.getProducts().size(); i++) {
                if (animal.getProducts().get(i).getId() != null) {
                    newProductList.add(productValidator.validate
                            (animal.getProducts().get(i), errors, property + "products", i));
                }
            }
            animal.setProducts(newProductList);
        }

        /*if (animal.getClients() != null) {
            List<Client> newClientList = new ArrayList<>();
            for (int i = 0; i < animal.getClients().size(); i++) {
                if (animal.getClients().get(i).getId() != null) {
                    newClientList.add(clientValidator.validate
                            (animal.getClients().get(i), errors, property + "client", i));
                }
            }
            if (!errors.hasErrors()) animal.setClients(newClientList);
        }*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "name", "Field.notEmpty");
        if (animal.getName() != null && animal.getName().length() < 2) {
            errors.rejectValue(property + "name", "Size.name.twoAndMore");
        }


        if (animal.getNotes() != null) {
            for (int i = 0; i < animal.getNotes().size(); i++) {
                if (animal.getNotes().get(i).isEmpty()) {
                    animal.getNotes().remove(i);
                    i--;
                }
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "number", "Field.notEmpty");
        if (animal.getNumber() != null && animal.getNumber() < 0) {
            errors.rejectValue(property + "number", "Positive.number");
        }

        return animal;
    }
}
