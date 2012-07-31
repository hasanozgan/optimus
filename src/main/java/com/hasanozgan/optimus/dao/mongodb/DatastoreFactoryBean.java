package com.hasanozgan.optimus.dao.mongodb;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: hasanozgan
 * Date: 7/31/12
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class DatastoreFactoryBean extends AbstractFactoryBean
{
    private Morphia morphia;
    private Mongo mongo;
    private String dbName;
    private String user;
    private String password;

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    protected Datastore createInstance() throws Exception {
        if (user == null || password == null) {
            return morphia.createDatastore(mongo, dbName);
        }
        else {
            return morphia.createDatastore(mongo, dbName, user, password.toCharArray());
        }
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
