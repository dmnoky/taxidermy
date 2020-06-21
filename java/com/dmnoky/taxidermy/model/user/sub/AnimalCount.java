package com.dmnoky.taxidermy.model.user.sub;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnimalCount {
    @Column(name = "count")
    private Integer count;

    @Column(name = "id_animal_ac")
    private Long animalId;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
}
