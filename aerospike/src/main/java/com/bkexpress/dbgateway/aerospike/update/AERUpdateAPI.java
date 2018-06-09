package com.bkexpress.dbgateway.aerospike.update;

import com.aerospike.client.Bin;
import com.bkexpress.dbgateway.aerospike.DBFactory.AerospikeFactory;
import org.apache.log4j.Logger;


public class AERUpdateAPI {
    private static AERUpdateAPI instance = null;
    private static final Logger logger = Logger.getLogger(AERUpdateAPI.class);

    public static synchronized AERUpdateAPI getInstace() {
        if (instance == null) {
            instance = new AERUpdateAPI();
        }
        return instance;
    }


    public boolean update(String namespace, String set, String key, Bin... bins) {
        try {
            AerospikeFactory.getInstance().update (namespace, set, key, bins);
            return true;
        } catch (Exception e) {
            logger.error(e,e);
            return false;
        }
    }
}
