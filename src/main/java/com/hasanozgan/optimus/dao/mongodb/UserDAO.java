package com.hasanozgan.optimus.dao.mongodb;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.hasanozgan.optimus.model.mongodb.User;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;

import java.util.List;


public class UserDAO extends BasicDAO<User, ObjectId>
{
    protected UserDAO(Datastore ds) {
        super(ds);
    }

}
