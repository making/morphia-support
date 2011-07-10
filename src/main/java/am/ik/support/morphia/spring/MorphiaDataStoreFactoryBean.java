/*
 * Copyright (C) 2011 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package am.ik.support.morphia.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class MorphiaDataStoreFactoryBean implements FactoryBean<Datastore>,
        InitializingBean, PersistenceExceptionTranslator, DisposableBean {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MorphiaDataStoreFactoryBean.class);

    private String host = "localhost";
    private Integer port = 27017;;
    private Datastore ds;
    private String dbName;
    private Mongo mongo;

    @SuppressWarnings("serial")
    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return new DataAccessException("Exception occured", ex) {
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mongo = new Mongo(host, port);
        ds = new Morphia().createDatastore(mongo, dbName);
        LOGGER.debug("connect mongo {}", mongo);
        LOGGER.debug("create datastore {}", ds);
    }

    @Override
    public Datastore getObject() throws Exception {
        return ds;
    }

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.debug("close mongo {}", mongo);
        mongo.close();
    }
}
