package com.bkexpress.dbgateway.mysql.update;

import com.aerospike.client.Bin;
import com.bkexpress.dbgateway.mysql.DBFactory.MySQLFactory;
import org.apache.log4j.Logger;


public class MySQLUpdateAPI {
    private static MySQLUpdateAPI instance = null;
    private static final Logger logger = Logger.getLogger(MySQLUpdateAPI.class);

    public static synchronized MySQLUpdateAPI getInstace() {
        if (instance == null) {
            instance = new MySQLUpdateAPI();
        }
        return instance;
    }


    public boolean update(String namespace, String set, String key, Bin... bins) {
        try {
            MySQLFactory.getInstance().update (namespace, set, key, bins);
            return true;
        } catch (Exception e) {
            logger.error(e,e);
            return false;
        }
    }
}
