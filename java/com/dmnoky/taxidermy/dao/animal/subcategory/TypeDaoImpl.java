package com.dmnoky.taxidermy.dao.animal.subcategory;


import com.dmnoky.taxidermy.model.animal.subcategory.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDaoImpl implements TypeDao {
    private static final Logger logger = LoggerFactory.getLogger(TypeDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addType(Type type) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(type);
        logger.info("Type: "+type+" successfully added.");
    }

    @Override
    public void updateType(Type type) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(type);
        logger.info("Type: "+type+" successfully updated.");
    }

    @Override
    public boolean removeType(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Type type = session.load(Type.class, id);
        if (type!=null){
            session.delete(type);
            logger.info("Type: "+type+" successfully removed.");
            return true;
        }
        logger.info("Type: unsuccessfully removed.");
        return false;
    }

    @Override
    public Type getType(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Type type = session.load(Type.class, id);
        logger.info("Type: "+type+" successfully loaded.");
        return type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Type> getTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Type> typeList = session.createQuery("FROM Type").list();
        logger.info("Subsidiaries: "+typeList.size()+" successfully loaded.");
        return typeList;
    }
}

