package com.bkexpress.dbgateway.mysql.DBFactory;

import com.bkexpress.dbgateway.mysql.adapter.VehicleAdapter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.sql.SQLException;


public class HibernateUtil {


    private static final Logger logger = Logger.getLogger(HibernateUtil.class);


    public static void main(String[] args) throws SQLException {
        PropertyConfigurator.configure("etc/log4j.properties");
        VehicleAdapter adapter = new VehicleAdapter();
        adapter.query();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//
//
//        session.beginTransaction();
//
//        // Check database version
//        String sql = "select version()";
//
//        String result = (String) session.createNativeQuery(sql).getSingleResult();
//        System.out.println(result);
//
//        session.getTransaction().commit();
//        session.close();


        HibernateUtil.shutdown();
        logger.info("load hibernate success");
    }

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }






}