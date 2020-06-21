package com.dmnoky.taxidermy.validator.other;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
public class FileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MultipartFile[].class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MultipartFile[] files = (MultipartFile[]) target;
        for (MultipartFile file : files) {
            if (!file.getContentType().equals("application/octet-stream")) {
                if (file.getSize() < 0 && file.getSize() > 4294967295L)
                    errors.rejectValue("file", "Size.file.4gb");
            }
        }
    }

    //TODO
    public Map<String, String> validateImages(Object target) {
        MultipartFile[] images = (MultipartFile[]) target;
        Map<String, String> errors = new HashMap<>();
        for (int i=0; i<images.length; i++) {
            if (!images[i].getContentType().equals("application/octet-stream")) {
                System.out.println("type "+i+": "+images[i].getContentType()+" "+images[i].getContentType().equals("image/png"));
                if (!(images[i].getContentType().equals("image/png") ||
                        images[i].getContentType().equals("image/jpeg"))) {
                    errors.put("images[" + i + "]", "Файл должен быть изображением");
                } else if (images[i].getSize() < 0 && images[i].getSize() > 4294967295L)
                    errors.put("images[" + i + "]", "Размер файла больше 4гб");
            }
        }
        return errors;
    }


}
