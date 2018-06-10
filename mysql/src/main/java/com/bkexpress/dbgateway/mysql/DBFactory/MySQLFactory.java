package com.bkexpress.dbgateway.mysql.DBFactory;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MySQLFactory {

    Logger logger = Logger.getLogger(MySQLFactory.class);
    //<editor-fold desc="Define Variables">


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
            return true;
        } catch (IOException e) {
            logger.error(e, e);
            return false;
        }
    }

    public MySQLFactory() {
        config("../etc/aerospike.conf");
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
