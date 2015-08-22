package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.FriendsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class FriendsDAOImpl extends DAOTemplate implements  FriendsDAO{
    @Override
    public void createFriends(FriendsEntity friends) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(friends);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<FriendsEntity> findFriendsByEmail(String email) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from FriendsEntity where email1 = ?").setString(0, email);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public boolean isFriends(String email1, String email2) {
        System.out.println(email1);
        System.out.println(email2);
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from FriendsEntity where (email1 = ? and email2 = ?)").setString(0, email1).setString(1,email2);
        }catch(Exception e){
            e.printStackTrace();
        }
        List<FriendsEntity>list = query.list();
        if (list != null && list.size()!=0) {
            System.out.println("true");
            return list.get(0)!=null;
        }else{
            System.out.println("false");
            return false;
        }
    }

    @Override
    public FriendsEntity findFriends(String email1, String email2) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from FriendsEntity where (email1 = ? and email2 = ?)").setString(0, email1).setString(1,email2);
        }catch(Exception e){
            e.printStackTrace();
        }
        return (FriendsEntity) query.list().get(0);
    }

    @Override
    public void deleteFriends(FriendsEntity friendsEntity) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.delete(friendsEntity);
        session.flush();
        session.close();
        tx.commit();
    }


}
