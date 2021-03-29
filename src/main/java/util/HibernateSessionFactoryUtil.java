package util;

import entity.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    public static SessionFactory factory;

    private HibernateSessionFactoryUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (factory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            try {
                factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception ex) {
                StandardServiceRegistryBuilder.destroy(registry);
                System.err.println(ex);
            }
        }
        return factory;
    }
}