package com.dmnoky.taxidermy.service.animal;

import com.dmnoky.taxidermy.dao.animal.AnimalDao;
import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.animal.sub.AnimalListCountTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalDao animalDao;

    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    @Transactional
    public void addAnimal(Animal Animal) {
        this.animalDao.addAnimal(Animal);
    }

    @Override
    @Transactional
    public void updateAnimal(Animal Animal) {
        this.animalDao.updateAnimal(Animal);
    }

    @Override
    @Transactional
    public boolean removeAnimal(Long id) {
        return this.animalDao.removeAnimal(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Animal getAnimalById(Long id) {
        return this.animalDao.getAnimalById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Animal getAnimalByArticle(String article) {
        return this.animalDao.getAnimalByArticle(article);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Animal> getAnimals() {
        return this.animalDao.getAnimals();
    }

    @Override
    @Transactional(readOnly=true)
    public AnimalListCountTemplate getAnimals(Integer begin, Integer end) {
        return this.animalDao.getAnimals(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public AnimalListCountTemplate getAnimalsBySubsidiary(Integer begin, Integer end, String subsidiaryName) {
        return this.animalDao.getAnimalsBySubsidiary(begin, end, subsidiaryName);
    }

    @Override
    @Transactional(readOnly=true)
    public AnimalListCountTemplate getAnimalsByType(Integer begin, Integer end, String typeName) {
        return this.animalDao.getAnimalsByType(begin, end, typeName);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Animal> getAnimalsOrderByDESC(Integer begin, Integer end) {
        return this.animalDao.getAnimalsOrderByDESC(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public Long getCountRows(){
        return this.animalDao.getCountRows();
    }
}
