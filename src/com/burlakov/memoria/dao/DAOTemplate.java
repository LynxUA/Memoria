package com.burlakov.memoria.dao;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by denysburlakov on 11.03.15.
 */
public abstract class DAOTemplate {
    protected static final SessionFactory sessionFactory;
    protected static final ServiceRegistry serviceRegistry;
    private Session session;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() throws HibernateException {
        //System.out.println("start");
        Session session = sessionFactory.openSession();
        //System.out.println("session is null");
        session.setFlushMode(FlushMode.COMMIT);
        return session;
    }

}
