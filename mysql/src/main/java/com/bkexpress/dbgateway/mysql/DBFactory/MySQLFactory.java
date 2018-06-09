package com.bkexpress.dbgateway.mysql.DBFactory;

import com.aerospike.client.Bin;
import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.async.AsyncClient;
import com.aerospike.client.policy.QueryPolicy;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MySQLFactory {

    Logger logger = Logger.getLogger(MySQLFactory.class);
    //<editor-fold desc="Define Variables">
    private AsyncClient client = null;
    //FIXME load queryPolicy from config
    private QueryPolicy queryPolicy = new QueryPolicy();


    //</editor-fold>


    //<editor-fold desc="Singleton Aerospike Instance">
    private static MySQLFactory instance = null;


    public static synchronized MySQLFactory getInstance() {
        if (instance == null) {
            instance = new MySQLFactory();
        }
        return instance;
    }
    //</editor-fold>

    private boolean config(String config) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(config)));
            Host[] hosts = Host.parseHosts(prop.getProperty("host", "127.0.0.1"), Integer.valueOf(prop.getProperty("port", "3000")));
            //FIXME fix policy with config file
            client = new AsyncClient(null, hosts);
            return true;
        } catch (IOException e) {
            logger.error(e, e);
            return false;
        }
    }

    public MySQLFactory() {
        config("../etc/aerospike.conf");
    }


    public Record query(String namespace, String set, String key, String... bins) {
        return client.get(queryPolicy, new Key(namespace, set, key), bins);
    }

    public void update(String namespace, String set, String key, Bin... bins) {
        client.put(null, new Key(namespace, set, key), bins);
    }


    boolean completed;

    protected void resetComplete() {
        completed = false;
    }

    protected synchronized void waitTillComplete() {
        while (!completed) {
            try {
                super.wait(10000l);
            } catch (InterruptedException ie) {
                logger.error(ie, ie);
            }
        }
    }

    protected synchronized void notifyComplete() {
        completed = true;
        super.notify();
    }
}
