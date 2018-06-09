package com.bkexpress.dbgateway.aerospike.query;

import com.aerospike.client.Record;
import com.bkexpress.dbgateway.aerospike.DBFactory.AerospikeFactory;

public class AERQueryAPI {
    private static AERQueryAPI instance = null;

    public static synchronized AERQueryAPI getInstace() {
        if (instance == null) {
            instance = new AERQueryAPI();
        }
        return instance;
    }

    public Record query(String namespace, String set, String key, String... bins) {
        return AerospikeFactory.getInstance().query(namespace, set, key, bins);
    }


}
