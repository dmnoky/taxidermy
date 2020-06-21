package com.dmnoky.taxidermy.validator.user;

import com.dmnoky.taxidermy.model.user.Client;
import com.dmnoky.taxidermy.model.user.User;
import com.dmnoky.taxidermy.service.user.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public abstract class UserValidator<T extends User> implements Validator {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]"
                    +"+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    protected UserService<T> userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        T user = (T) target;
        emailValidate(target, errors);
        if (!errors.hasFieldErrors("email")) {
            try {
                if (userService.getUserByEmail(user.getEmail()) != null)
                    errors.rejectValue("email", "Duplicate.userForm.email");
            } catch (org.hibernate.ObjectNotFoundException ignored) {}
        }
        passValidate(target, errors);
        this.validate(target, errors, "");
    }

    protected T passValidate(Object target, Errors errors) {
        T user = (T) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field.notEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 255) {
            errors.rejectValue("password", "Size.userForm.password");
        } else if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }
        return user;
    }

    protected T emailValidate(Object target, Errors errors) {
        T user = (T) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Field.notEmpty");
        if (!Pattern.compile(EMAIL_PATTERN).matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "Incorrect.userFrom.email");
        }
        return user;
    }

    public T validate(Object target, Errors errors, String property) {
        T user = (T) target;
        if (property.length() > 0) property = property + ".";

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "article", "Field.notEmpty");
        if (user.getArticle() != null) {
            try {
                if (userService.getUserByArticle(user.getArticle()) != null)
                    errors.rejectValue(property + "article", "Duplicate.form.article");
            } catch (org.hibernate.ObjectNotFoundException ignored) {}
        }
        return user;
    }

    public T validateUpdate(Object target, Errors errors) {
        passValidate(target, errors);
        emailValidate(target, errors);
        return (T) target;
    }

    public T validate(Object target, Errors errors, String property, int step) {
        T user = (T) target;

        if (user.getId() != null) {
            try {
                user = userService.getUserById(user.getId());
            } catch (org.hibernate.ObjectNotFoundException e) {
                errors.rejectValue(property + "[" + step + "].id", "NotExist.entity.id");
                return null;
            }
        }
        return user;
    }
}
