package com.bkexpress.dbgateway.mysql.update;

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


}
