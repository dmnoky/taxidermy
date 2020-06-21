package com.dmnoky.taxidermy.service.animal;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.animal.sub.AnimalListCountTemplate;

import java.util.List;

public interface AnimalService {
    void addAnimal(Animal animal);
    void updateAnimal(Animal animal);
    boolean removeAnimal(Long id) throws org.hibernate.ObjectNotFoundException;
    Animal getAnimalById(Long id) throws org.hibernate.ObjectNotFoundException;
    Animal getAnimalByArticle(String article) throws org.hibernate.ObjectNotFoundException;
    List<Animal> getAnimals();
    AnimalListCountTemplate getAnimals(Integer begin, Integer end);
    AnimalListCountTemplate getAnimalsBySubsidiary(Integer begin, Integer end, String subsidiaryName);
    AnimalListCountTemplate getAnimalsByType(Integer begin, Integer end, String typeName);
    List<Animal> getAnimalsOrderByDESC(Integer begin, Integer end);
    Long getCountRows();
}
