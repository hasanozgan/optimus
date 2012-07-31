package com.hasanozgan.optimus.dao.hibernate;

/**
 * Created with IntelliJ IDEA.
 * User: hasanozgan
 * Date: 7/25/12
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

import com.hasanozgan.optimus.model.hibernate.User;

/**
 *
 * User DAO Interface
 *
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface UserDAO {

    /**
     * Add User
     *
     * @param user
     */
    public void addUser(User user);

    /**
     * Update User
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * Delete User
     *
     * @param  user
     */
    public void deleteUser(User user);

    /**
     * Get User
     *
     * @param  id
     */
    public User getUserById(int id);

    /**
     * Get User List
     *
     */
    public List<User> getUsers();
}