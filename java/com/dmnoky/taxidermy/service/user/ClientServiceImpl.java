package com.dmnoky.taxidermy.service.user;

import com.dmnoky.taxidermy.dao.user.UserDao;
import com.dmnoky.taxidermy.model.user.Client;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements UserService<Client> {
    private UserDao<Client> clientDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setClientDao(UserDao<Client> clientDao) {
        this.clientDao = clientDao;
    }
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean addUser(Client client) {
        client.setEnabled(0);
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        //client.getAuthorities().add(new Authority(client, "ROLE_USER"));
        return this.clientDao.addUser(client);
    }

    @Override
    @Transactional
    public void updateUser(Client client, int enabled) {
        client.setEnabled(enabled);
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        this.clientDao.updateUser(client);
    }

    @Override
    @Transactional
    public boolean removeUser(Long id) {
        return this.clientDao.removeUser(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Client getUserById(Long id) {
        return this.clientDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Client getUserByArticle(String article) {
        return this.clientDao.getUserByArticle(article);
    }

    @Override
    @Transactional(readOnly=true)
    public Client getUserByName(String username) {
        return this.clientDao.getUserByName(username);
    }

    @Override
    @Transactional(readOnly=true)
    public Client getUserByEmail(String email) {
        return this.clientDao.getUserByEmail(email);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Client> getUsers() {
        return this.clientDao.getUsers();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Client> getUsers(Integer begin, Integer end) {
        return this.clientDao.getUsers(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Client> getUsersOrderByDESC(Integer begin, Integer end) {
        return this.clientDao.getUsersOrderByDESC(begin, end);
    }
    
    @Override
    @Transactional(readOnly=true)
    public Long getCountRows(){
        return this.clientDao.getCountRows();
    }
}
