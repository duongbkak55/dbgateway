package com.bkexpress.dbgateway.mysql.query;

public class MySQLQueryAPI {
    private static MySQLQueryAPI instance = null;

    public static synchronized MySQLQueryAPI getInstace() {
        if (instance == null) {
            instance = new MySQLQueryAPI();
        }
        return instance;
    }


}
