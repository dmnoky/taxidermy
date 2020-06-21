package com.dmnoky.taxidermy.dao.animal.subcategory;

import com.dmnoky.taxidermy.model.animal.subcategory.Subsidiary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubsidiaryDaoImpl implements SubsidiaryDao {
    private static final Logger logger = LoggerFactory.getLogger(SubsidiaryDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSubsidiary(Subsidiary subsidiary) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(subsidiary);
        logger.info("Subsidiary: "+subsidiary+" successfully added.");
    }

    @Override
    public void updateSubsidiary(Subsidiary subsidiary) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(subsidiary);
        logger.info("Subsidiary: "+subsidiary+" successfully updated.");
    }

    @Override
    public boolean removeSubsidiary(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Subsidiary subsidiary = session.load(Subsidiary.class, id);
        if (subsidiary!=null){
            session.delete(subsidiary);
            logger.info("Subsidiary: "+subsidiary+" successfully removed.");
            return true;
        }
        logger.info("Subsidiary: unsuccessfully removed.");
        return false;
    }

    @Override
    public Subsidiary getSubsidiary(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Subsidiary subsidiary = session.load(Subsidiary.class, id);
        logger.info("Subsidiary: "+subsidiary+" successfully loaded.");
        return subsidiary;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Subsidiary> getSubsidiaries() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Subsidiary> subsidiaryList = session.createQuery("FROM Subsidiary").list();
        logger.info("Subsidiaries: "+subsidiaryList.size()+" successfully loaded.");
        return subsidiaryList;
    }
}
