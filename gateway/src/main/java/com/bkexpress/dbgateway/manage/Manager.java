package com.bkexpress.dbgateway.manage;

import com.bkexpress.dbgateway.aerospike.query.MySQLQueryAPI;

public class Manager {
    public static void main(String[] args) {

    }

    public void queryVehicleOwner(){
        MySQLQueryAPI.getInstace().query("vgo","vehicle_owner","test",null);

    }
}
