package com.bkexpress.dbgateway.aerospike.DBFactory;

import com.aerospike.client.Host;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.async.AsyncClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.QueryPolicy;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class AerospikeFactory {

    Logger logger = Logger.getLogger(AerospikeFactory.class);
    //<editor-fold desc="Define Variables">
    private AsyncClient client = null;
    //FIXME load queryPolicy from config
    private QueryPolicy queryPolicy = new QueryPolicy();



    //</editor-fold>


    //<editor-fold desc="Singleton Aerospike Instance">
    private static AerospikeFactory instance = null;


    public static synchronized AerospikeFactory getInstance() {
        if (instance == null) {
            instance = new AerospikeFactory();
        }
        return instance;
    }
    //</editor-fold>

    private boolean config(String config) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(config)));
            Host[]  hosts = Host.parseHosts(prop.getProperty("host","127.0.0.1"),Integer.valueOf(prop.getProperty("port","3000")));
            //FIXME fix policy with config file
            client = new AsyncClient(null, hosts);
            return true;
        } catch (IOException e) {
            logger.error(e,e);
            return false;
        }
    }

    public AerospikeFactory() {
        config("../etc/aerospike.conf");
    }



    public Record query(String namespace, String set, String key, String... bins){
        return client.get(queryPolicy,new Key(namespace,set,key),bins);
    }

    public Record update(String namespace, String set, String key, String... bins){
        return client.p(queryPolicy,new Key(namespace,set,key),bins);
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
                logger.error(ie,ie);
            }
        }
    }

    protected synchronized void notifyComplete() {
        completed = true;
        super.notify();
    }
}
