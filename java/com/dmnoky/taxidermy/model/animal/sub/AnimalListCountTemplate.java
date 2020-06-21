package com.dmnoky.taxidermy.model.animal.sub;

import com.dmnoky.taxidermy.model.animal.Animal;

import java.util.List;

public class AnimalListCountTemplate {
    private List<Animal> animalList;
    private Long totalListSize;

    public AnimalListCountTemplate() {
    }

    public AnimalListCountTemplate(List<Animal> animalList, Long totalListSize) {
        this.animalList = animalList;
        this.totalListSize = totalListSize;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public Long getTotalListSize() {
        return totalListSize;
    }

    public void setTotalListSize(Long totalListSize) {
        this.totalListSize = totalListSize;
    }
}
