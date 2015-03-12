package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.MemoriaUserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class MemoriaUserDAOImpl extends DAOTemplate implements MemoriaUserDAO{
    @Override
    public void createUser(MemoriaUserEntity user) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<MemoriaUserEntity> allUsers() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from MemoriaUserEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public MemoriaUserEntity findUser(String email, String password) {
        List users = null;
        try {
            Session session = getSession();
            users = session.createQuery("from MemoriaUserEntity where email=? and password=?")
                    .setString(0, email)
                    .setString(1, password)
                    .list();
        }catch(Exception e){
            e.printStackTrace();
        }

        return (MemoriaUserEntity)(users.get(0));

    }

}
