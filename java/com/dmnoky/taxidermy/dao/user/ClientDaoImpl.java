package com.dmnoky.taxidermy.dao.user;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.user.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class ClientDaoImpl implements UserDao<Client> {
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(Client client) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(client);
        if (client.getAnimalList() != null) {
            for (Animal animal : client.getAnimalList()) {
                this.sessionFactory.getCurrentSession().update(animal);
            }
        }
        logger.info("Client: "+client+" successfully added.");
        return true;
    }

    @Override
    public void updateUser(Client client) {
        this.sessionFactory.getCurrentSession().update(client);
        if (client.getAnimalList() != null) {
            for (Animal animal : client.getAnimalList()) {
                this.sessionFactory.getCurrentSession().update(animal);
            }
        }
        logger.info("Client: "+client+" successfully updated.");
    }

    @Override
    public boolean removeUser(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            Client client = this.getUserById(id);
            session.delete(client);
            logger.info("Client: "+client+" successfully removed.");
            return true;
        } catch (EntityNotFoundException | org.hibernate.ObjectNotFoundException e) {
            logger.info("Client with id "+id+" unsuccessfully removed.");
            throw new org.hibernate.ObjectNotFoundException(id, Client.class.getName());
        }
    }

    @Override
    public Client getUserById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Client client = session.load(Client.class, id);
        logger.info("Client: "+client+" successfully loaded.");
        return client;
    }

    @Override
    public Client getUserByArticle(String article) {
        Session session = this.sessionFactory.getCurrentSession();
        Client client = (Client)
                (session.createQuery("SELECT c FROM Client c WHERE c.article=?")
                        .setParameter(0, article)).uniqueResult();
        if (client == null) {
            logger.info("Client with article "+article+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(article, Client.class.getName());
        }
        logger.info("Client: "+client+" successfully loaded.");
        return client;
    }

    @Override
    public Client getUserByName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Client client = (Client)
                (session.createQuery("SELECT c FROM Client c WHERE c.name=?")
                        .setParameter(0, username)).uniqueResult();
        if (client == null) {
            logger.info("Client with username "+username+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(username, Client.class.getName());
        }
        logger.info("Client: "+client+" successfully loaded.");
        return client;
    }

    @Override
    public Client getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        Client client = (Client)
                (session.createQuery("SELECT c FROM Client c WHERE c.email=?")
                        .setParameter(0, email)).uniqueResult();
        if (client == null) {
            logger.info("Client with email "+email+" unsuccessfully loaded.");
            throw new org.hibernate.ObjectNotFoundException(email, Client.class.getName());
        }
        logger.info("Client: "+client+" successfully loaded.");
        return client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Client> clientList = session.createQuery("FROM Client").list();
        logger.info("Clients: "+clientList.size()+" successfully loaded.");
        return clientList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getUsers(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Client> clientList = session.createQuery("FROM Client")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (Client client : clientList) {
            Hibernate.initialize(client.getAnimals());
        }*/
        logger.info("Clients: "+clientList.size()+" successfully loaded.");
        return clientList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getUsersOrderByDESC(Integer begin, Integer end) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Client> clientList = session.createQuery("FROM Client c ORDER BY c.id DESC")
                .setFirstResult(begin).setMaxResults(end).list();
        /*for (Client client : clientList) {
            Hibernate.initialize(client.getAnimals());
        }*/
        logger.info("Clients: "+clientList.size()+" successfully loaded.");
        return clientList;
    }

    @Override
    public Long getCountRows() {
        Session session = this.sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT count(*) FROM Client").uniqueResult();
        return count;
    }
}
