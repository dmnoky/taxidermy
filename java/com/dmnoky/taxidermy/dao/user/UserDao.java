package com.dmnoky.taxidermy.dao.user;

import com.dmnoky.taxidermy.model.user.User;

import java.util.List;

public interface UserDao<T extends User> {
    boolean addUser(T user);
    void updateUser(T user);
    boolean removeUser(Long id);
    T getUserById(Long id) throws org.hibernate.ObjectNotFoundException;
    T getUserByArticle(String article) throws org.hibernate.ObjectNotFoundException;
    T getUserByName(String username) throws org.hibernate.ObjectNotFoundException;
    T getUserByEmail(String email) throws org.hibernate.ObjectNotFoundException;
    List<T> getUsers();
    List<T> getUsers(Integer begin, Integer end);
    List<T> getUsersOrderByDESC(Integer begin, Integer end);
    Long getCountRows();
}
