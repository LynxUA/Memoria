package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by denysburlakov on 14.02.15.
 */

public class DeskDAOImpl extends DAOTemplate implements DeskDAO{

    @Override
    public void createDesk(DeskEntity desk) {
        getSession().save(desk);
    }

    @Override
    public List<DeskEntity> allDesks() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from DeskEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public void deleteDesk(Integer id) {
        DeskEntity contact = (DeskEntity) getSession().load(
                DeskEntity.class, id);
        if (null != contact) {

        }
    }
}
