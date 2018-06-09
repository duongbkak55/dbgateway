package com.bkexpress.dbgateway.mysql.query;

import com.aerospike.client.Record;
import com.bkexpress.dbgateway.mysql.DBFactory.MySQLFactory;

public class MySQLQueryAPI {
    private static MySQLQueryAPI instance = null;

    public static synchronized MySQLQueryAPI getInstace() {
        if (instance == null) {
            instance = new MySQLQueryAPI();
        }
        return instance;
    }

    public Record query(String namespace, String set, String key, String... bins) {
        return MySQLFactory.getInstance().query(namespace, set, key, bins);
    }


}
