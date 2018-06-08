package com.bkexpress.dbgateway.aerospike.QueryAPI;

import com.aerospike.client.Record;
import com.bkexpress.dbgateway.aerospike.DBFactory.AerospikeFactory;

public class QueryAPI {
    private static QueryAPI instance = null;
    public synchronized QueryAPI getInstace(){
        if(instance == null){
            instance = new QueryAPI();
        }
        return instance;
    }

    public Record query(String namespace, String set, String key, String... bins){
        return AerospikeFactory.getInstance().query(namespace,set,key,bins);
    }


    public Record update(String namespace, String set, String key, String... bins){
        return AerospikeFactory.getInstance().query(namespace,set,key,bins);
    }
}
