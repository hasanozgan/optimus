package com.hasanozgan.optimus.service.mongodb;

import com.hasanozgan.optimus.dao.mongodb.UserDAO;
import com.hasanozgan.optimus.model.mongodb.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hasanozgan
 * Date: 7/31/12
 * Time: 8:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserService
{
    UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user)
    {
        userDAO.save(user);
    }

    public List<User> getUserList()
    {
        return userDAO.find().asList();
    }
}
