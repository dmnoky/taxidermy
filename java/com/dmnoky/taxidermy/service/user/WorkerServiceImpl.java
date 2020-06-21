package com.dmnoky.taxidermy.service.user;

import com.dmnoky.taxidermy.dao.user.UserDao;
import com.dmnoky.taxidermy.model.user.Worker;
import com.dmnoky.taxidermy.model.user.sub.Authority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerServiceImpl implements UserService<Worker> {
    private UserDao<Worker> workerDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setWorkerDao(UserDao<Worker> workerDao) {
        this.workerDao = workerDao;
    }
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean addUser(Worker worker) {
        worker.setEnabled(1);
        worker.setPassword(bCryptPasswordEncoder.encode(worker.getPassword()));
        worker.getAuthorities().add(new Authority(worker, "ROLE_WORKER"));
        worker.getAuthorities().add(new Authority(worker, "ROLE_USER"));
        return this.workerDao.addUser(worker);
    }

    @Override
    @Transactional
    public void updateUser(Worker worker, int enabled) {
        worker.setEnabled(enabled);
        //if (!bCryptPasswordEncoder.matches(worker.getPassword(), bCryptPasswordEncoder.encode(worker.getPassword())))
            worker.setPassword(bCryptPasswordEncoder.encode(worker.getPassword()));
        this.workerDao.updateUser(worker);
    }

    @Override
    @Transactional
    public boolean removeUser(Long id) {
        return this.workerDao.removeUser(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Worker getUserById(Long id) {
        return this.workerDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Worker getUserByArticle(String article) {
        return this.workerDao.getUserByArticle(article);
    }

    @Override
    @Transactional(readOnly=true)
    public Worker getUserByName(String username) {
        return this.workerDao.getUserByName(username);
    }

    @Override
    @Transactional(readOnly=true)
    public Worker getUserByEmail(String email) {
        return this.workerDao.getUserByEmail(email);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Worker> getUsers() {
        return this.workerDao.getUsers();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Worker> getUsers(Integer begin, Integer end) {
        return this.workerDao.getUsers(begin, end);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Worker> getUsersOrderByDESC(Integer begin, Integer end) {
        return this.workerDao.getUsersOrderByDESC(begin, end);
    }
    
    @Override
    @Transactional(readOnly=true)
    public Long getCountRows(){
        return this.workerDao.getCountRows();
    }
}
