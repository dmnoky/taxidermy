package com.dmnoky.taxidermy.dao.user;

import com.dmnoky.taxidermy.model.user.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class WorkerDaoImpl implements UserDao<Worker> {
    private static final Logger logger = LoggerFactory.getLogger(WorkerDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(Worker worker) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(worker);
        logger.info("worker: "+worker+" successfully added.");
        return true;
    }

    @Override
    public void updateUser(Worker worker) {
        this.sessionFactory.getCurrentSession().update(worker);
        logger.info("worker: "+worker+" successfully updated.");
    }

    @Override
    public boolean removeUser(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            Worker worker = this.getUserById(id);
            session.delete(worker);
            logger.info("worker: "+worker+" successfully removed.");
            return true;
        } catch (EntityNotFoundException | org.hibernate.ObjectNotFoundException e) {
            logger.info("worker with id "+id+" unsuccessfully removed.");
            throw new org.hibernate.ObjectNotFoundException(id, Worker.class.getName());
        }
    }

    @Override
    public Worker getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Worker worker = session.load(Worker.class, id);
        logger.info("worker: "+worker+" successfully loaded.");
        return worker;
    }

    @Override
    public Worker getUserByArticle(String article) {
        Session session = this.sessionFactory.getCurrentSession();
        Worker worker = (Worker)
                (session.createQuery("SELECT c FROM Worker c WHERE c.article=?")
                        .setParameter(0, article)).uniqueResult();
        if (worker == null) {
            logger.info("worker with article "+article+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(article, Worker.class.getName());
        }
        logger.info("worker: "+worker+" successfully loaded.");
        return worker;
    }

    @Override
    public Worker getUserByName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Worker worker = (Worker)
                (session.createQuery("SELECT c FROM Worker c WHERE c.name=?")
                        .setParameter(0, username)).uniqueResult();
        if (worker == null) {
            logger.info("worker with username "+username+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(username, Worker.class.getName());
        }
        logger.info("worker: "+worker+" successfully loaded.");
        return worker;
    }

    @Override
    public Worker getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        Worker worker = (Worker)
                (session.createQuery("SELECT c FROM Worker c WHERE c.email=?")
                        .setParameter(0, email)).uniqueResult();
        if (worker == null) {
            logger.info("Worker with email "+email+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(email, Worker.class.getName());
        }
        logger.info("Worker: "+worker+" successfully loaded.");
        return worker;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Worker> getUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Worker> workerList = session.createQuery("FROM Worker").list();
        logger.info("workers: "+workerList.size()+" successfully loaded.");
        return workerList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Worker> getUsers(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Worker> workerList = session.createQuery("FROM Worker")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (worker worker : workerList) {
            Hibernate.initialize(worker.getAnimals());
        }*/
        logger.info("workers: "+workerList.size()+" successfully loaded.");
        return workerList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Worker> getUsersOrderByDESC(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Worker> workerList = session.createQuery("FROM Worker c ORDER BY c.id DESC")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (worker worker : workerList) {
            Hibernate.initialize(worker.getAnimals());
        }*/
        logger.info("workers: "+workerList.size()+" successfully loaded.");
        return workerList;
    }

    @Override
    public Long getCountRows() {
        Session session = this.sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT count(*) FROM Worker").uniqueResult();
        return count;
    }
}
