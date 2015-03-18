package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 14.02.15.
 */

public class DeskDAOImpl extends DAOTemplate implements DeskDAO{

    @Override
    public void createDesk(DeskEntity desk) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(desk);
        session.flush();
        session.close();
        tx.commit();
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
    public void deleteDesk(BigDecimal id) {
        DeskEntity desk = (DeskEntity) getSession().load(
                DeskEntity.class, id);
        if (null != desk) {
            sessionFactory.getCurrentSession().delete(desk);
        }
    }

    @Override
    public DeskEntity findDesk(BigDecimal id) {
        return (DeskEntity) getSession().load(
                DeskEntity.class, id);
    }

    @Override
    public List<DeskEntity> findDesksByUser(String email) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from DeskEntity desk " +
                                        "where idDesk=(select idDesk" +
                                                        " from DeskUsersEntity desk_users " +
                    "                                     where desk.idDesk = desk_users.idDesk and desk_users.email=?)").setString(0,email);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
