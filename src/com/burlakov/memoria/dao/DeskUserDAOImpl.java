package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskUsersEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 18.03.15.
 */
public class DeskUserDAOImpl extends DAOTemplate implements DeskUserDAO {
    @Override
    public void createDeskUser(DeskUsersEntity deskUser) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(deskUser);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<DeskUsersEntity> allDeskUsers() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from DeskUsersEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }



    @Override
    public List<DeskUsersEntity> findDeskUsersByDeskId(BigDecimal idDesk) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from DeskUsersEntity where idDesk = ?").setBigDecimal(0,idDesk);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

}
