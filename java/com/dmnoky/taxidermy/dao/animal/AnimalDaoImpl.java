package com.dmnoky.taxidermy.dao.animal;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.animal.sub.AnimalListCountTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class AnimalDaoImpl implements AnimalDao {
    private static final Logger logger = LoggerFactory.getLogger(AnimalDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addAnimal(Animal animal) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(animal);
        logger.info("Animal: "+animal+" successfully added.");
    }

    @Override
    public void updateAnimal(Animal animal) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(animal);
        logger.info("Animal: "+animal+" successfully updated.");
    }

    @Override
    public boolean removeAnimal(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            Animal animal = this.getAnimalById(id);
            session.delete(animal);
            logger.info("Animal: "+animal+" successfully removed.");
            return true;
        } catch (EntityNotFoundException | org.hibernate.ObjectNotFoundException e) {
            logger.info("Animal with id "+id+" unsuccessfully removed.");
            throw new org.hibernate.ObjectNotFoundException(id, Animal.class.getName());
        }
    }

    @Override
    public Animal getAnimalById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Animal animal = session.load(Animal.class, id);
        logger.info("Animal: "+animal+" successfully loaded.");
        return animal;
    }

    @Override
    public Animal getAnimalByArticle(String article) {
        Session session = this.sessionFactory.getCurrentSession();
        Animal animal = (Animal)
                    (session.createQuery("SELECT a FROM Animal a WHERE a.article=?")
                            .setParameter(0, article)).uniqueResult();
        if (animal == null) {
            logger.info("Animal: with article "+article+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(article, Animal.class.getName());
        }
        logger.info("Animal: "+animal+" successfully loaded.");
        return animal;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Animal> getAnimals() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Animal> animalList = session.createQuery("FROM Animal").list();
        /*for (Animal animal : animalList) {
            Hibernate.initialize(animal.getClient());
            Hibernate.initialize(animal.getProducts());
        }*/
        logger.info("Animals: "+animalList.size()+" successfully loaded.");
        return animalList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnimalListCountTemplate getAnimals(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Animal> animalList = session.createQuery("FROM Animal")
                .setFirstResult(begin).setMaxResults(end).list();
        Long count = (Long) session.createQuery
                ("SELECT count(*) FROM Animal").uniqueResult();
        AnimalListCountTemplate result = new AnimalListCountTemplate(animalList, count);
        logger.info("Animals: "+animalList.size()+" successfully loaded.");
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnimalListCountTemplate getAnimalsBySubsidiary(Integer begin, Integer end, String subsidiaryName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Animal> animalList = session.createQuery("FROM Animal a WHERE a.subsidiary.name = ?")
                .setParameter(0, subsidiaryName).setFirstResult(begin).setMaxResults(end).list();
        Long count = (Long) session.createQuery
                ("SELECT count(*) FROM Animal a WHERE a.subsidiary.name = ?")
                .setParameter(0, subsidiaryName).uniqueResult();
        AnimalListCountTemplate result = new AnimalListCountTemplate(animalList, count);
        logger.info("Animals: "+animalList.size()+" successfully loaded.");
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AnimalListCountTemplate getAnimalsByType(Integer begin, Integer end, String typeName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Animal> animalList = session.createQuery("FROM Animal a WHERE a.type.name = ?")
                .setParameter(0, typeName).setFirstResult(begin).setMaxResults(end).list();
        Long count = (Long) session.createQuery
                ("SELECT count(*) FROM Animal a WHERE a.type.name = ?")
                .setParameter(0, typeName).uniqueResult();
        AnimalListCountTemplate result = new AnimalListCountTemplate(animalList, count);
        logger.info("Animals: "+animalList.size()+" successfully loaded.");
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Animal> getAnimalsOrderByDESC(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Animal> animalList = session.createQuery("FROM Animal a ORDER BY a.id DESC")
                .setFirstResult(begin).setMaxResults(end).list();

        logger.info("Animals: "+animalList.size()+" successfully loaded.");
        return animalList;
    }

    @Override
    public Long getCountRows() {
        Session session = this.sessionFactory.getCurrentSession();
        return (Long) session.createQuery("SELECT count(*) FROM Animal").uniqueResult();
    }
}
