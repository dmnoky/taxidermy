package com.dmnoky.taxidermy.validator.user;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.user.Client;
import com.dmnoky.taxidermy.model.user.sub.AnimalCount;
import com.dmnoky.taxidermy.service.user.UserService;
import com.dmnoky.taxidermy.validator.animal.AnimalValidator;
import com.dmnoky.taxidermy.validator.other.AddressValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ClientValidator extends UserValidator<Client> {
    private static final String PHONE_PATTERN =
            "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    private AddressValidator addressValidator;
    private AnimalValidator animalValidator;

    public void setClientService(UserService<Client> clientService) {
        super.userService = clientService;
    }

    public void setAddressValidator(AddressValidator addressValidator) {
        this.addressValidator = addressValidator;
    }

    public void setAnimalValidator(AnimalValidator animalValidator) {
        this.animalValidator = animalValidator;
    }

    @Override
    public Client validate(Object target, Errors errors, String property) {
        return defaultValidate(super.validate(target, errors, property), errors, property);
    }

    public Client validateUpdate(Object target, Errors errors) {
        return defaultValidate(emailValidate(target, errors), errors, "");
    }

    private Client defaultValidate(Client client, Errors errors, String property) {
        if (client.getAnimalList() != null) {
            List<Animal> newAnimalList = new ArrayList<>();
            List<AnimalCount> newAnimalCountList = new ArrayList<>();
            for (int i = 0; i < client.getAnimalList().size(); i++) {
                if (client.getAnimalList().get(i).getId() != null) {
                    Animal animal = animalValidator.validate
                            (client.getAnimalList().get(i), errors, "animalList", i);
                    if (animal != null ) {
                        Integer number = client.getAnimalCounts().get(i).getCount();
                        if (number==null || number <= 0) {
                            errors.rejectValue(property + "animalCounts[" + i + "]", "Positive.number");
                            continue;
                        }
                        if (client.getAnimalList().get(i).getNumber() != null) {
                            number = number - client.getAnimalList().get(i).getNumber();
                        } else if (animal.getNumber() < number) {
                            errors.rejectValue(property + "animalCounts[" + i + "]", "Incorrect.balance");
                            continue;
                        }
                        animal.setNumber(animal.getNumber() - number);
                        client.getAnimalCounts().get(i).setAnimalId(animal.getId());
                        newAnimalList.add(animal);
                        newAnimalCountList.add(client.getAnimalCounts().get(i));
                    } else {
                        client.getAnimalList().remove(i);
                        client.getAnimalCounts().remove(i);
                        i--;
                    }
                }
            }
            if (!errors.hasErrors()) {
                client.setAnimalList(newAnimalList);
                client.setAnimalCounts(newAnimalCountList);
            }
        }

        addressValidator.validate(client.getAddress(), errors, property + "address");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "name", "Field.notEmpty");
        if (client.getName() != null && client.getName().length() < 2) {
            errors.rejectValue(property + "name", "Size.name.twoAndMore");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, property + "surname", "Field.notEmpty");
        if (client.getSurname() != null && client.getSurname().length() < 2) {
            errors.rejectValue(property + "surname", "Size.name.twoAndMore");
        }

        if (client.getNotes() != null) {
            for (int i = 0; i < client.getNotes().size(); i++ ) {
                if (client.getNotes().get(i).isEmpty()) {
                    client.getNotes().remove(i);
                    i--;
                }
            }
        }

        if (client.getPhoneNumbers() != null) {
            List<String> newPhoneNumbers = new ArrayList<>();
            for (int i=0; i<client.getPhoneNumbers().size(); i++) {
                if (client.getPhoneNumbers().get(i) != null &&
                        client.getPhoneNumbers().get(i).length() > 0) {
                    if (!Pattern.compile(PHONE_PATTERN).matcher(client.getPhoneNumbers().get(i)).matches()) {
                        errors.rejectValue(property+"phoneNumbers["+i+"]",
                                "Incorrect.userFrom.phoneNumber");
                    } else newPhoneNumbers.add(client.getPhoneNumbers().get(i));
                }
            }
            client.setPhoneNumbers(newPhoneNumbers);
        }

        return client;
    }
}
