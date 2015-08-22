package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.PrivateMessagesEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class PrivateMessagesDAOImpl extends DAOTemplate implements PrivateMessagesDAO{
    @Override
    public void sendMessage(PrivateMessagesEntity message) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(message);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<PrivateMessagesEntity> findAllInMessages(String email) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from PrivateMessagesEntity where reciever = ?").setString(0, email);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public List<PrivateMessagesEntity> findAllOutMessages(String email) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from PrivateMessagesEntity where sender = ?").setString(0, email);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
