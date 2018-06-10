package com.bkexpress.dbgateway.mysql.adapter;

import com.bkexpress.dbgateway.mysql.DBFactory.HibernateUtil;
import com.bkexpress.dbgateway.mysql.object.BaseObject;
import com.bkexpress.dbgateway.mysql.object.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter {

    final String queryStatement = "select distinct * from vehicle";

    public List<BaseObject> query() throws SQLException {
        List<BaseObject> ret = new ArrayList<BaseObject>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        session.doWork(new Work() {
//
//
//            public void execute(Connection connection) throws SQLException {
//
//            }
//        });

//        session.connection();

        Query query = session.createNativeQuery(queryStatement);
        List<Object[]> result = query.list();
        if(result == null || result.isEmpty()){
            return ret;
        }
        for(Object[] row : result){
            Vehicle emp = new Vehicle();
            System.out.println(row[0].toString());
            System.out.println(row[1].toString());
            System.out.println(row[2].toString());
        }
        session.close();


        return  null;
    }
}
