package com.christian.TIAMS.dao;

import com.christian.TIAMS.dao.base.AbstractGenericDAO;
import com.christian.TIAMS.model.Asset;
import com.christian.TIAMS.model.User;

import javax.persistence.TypedQuery;

public class UserDAO extends AbstractGenericDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    @Override
    public User update(User object) {
        User currentObject = get(object.getUserID());
        currentObject.setEmail(object.getEmail());
        currentObject.setFirstName(object.getFirstName());
        currentObject.setPassword(object.getPassword());
        currentObject.setLastName(object.getLastName());
        return super.update(currentObject);
    }

    protected User getUserByEmail(String email){
        User user = null;
        entityManager.getTransaction().begin();
        String jpql = "select u from User u where u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        try {
            user = query.getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
            return user;
        }
    }

    public boolean authenticateUser(String email, String password){
        User user = getUserByEmail(email);
        if(user != null){
            if(password.contentEquals(password)){
                return true;
            }
        }
        return false;
    }

    public User getAuthenticatedUser(String email, String password){
        User user = getUserByEmail(email);
        if(user != null){
            if(user.getPassword().contentEquals(password)){
                return user;
            }
        }
        return null;
    }
}
