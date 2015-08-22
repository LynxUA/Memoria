package com.burlakov.memoria.dao;

import com.burlakov.memoria.dao.exceptions.UserNotFoundException;
import com.burlakov.memoria.model.MemoriaUserEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class MemoriaUserDAOImpl extends DAOTemplate implements MemoriaUserDAO{
    @Override
    public void createUser(MemoriaUserEntity user) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(user);
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
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public MemoriaUserEntity findUser(String email) {
        List users = null;
        try {
            Session session = getSession();
            users = session.createQuery("from MemoriaUserEntity where email=?")
                    .setString(0, email).list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return (MemoriaUserEntity)(users.get(0));

    }

    @Override
    public MemoriaUserEntity findUser(String email, String password) throws UserNotFoundException {
        List users = null;
        try {
            Session session = getSession();
            users = session.createQuery("from MemoriaUserEntity where email=? and password=?")
                    .setString(0, email)
                    .setString(1, password)
                    .list();
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(users.isEmpty()){
            throw new UserNotFoundException();
        }
        return (MemoriaUserEntity)(users.get(0));

    }

    public String getNameByEmail(String email){
        Session session = getSession();
        String name = (String)(session.createQuery("select name from MemoriaUserEntity where email=?")
                .setString(0, email).list().get(0));
        session.close();
        return name;
    }

    @Override
    public List<Object[]> findNumberOfCommentsForEveryUser() {
        SQLQuery query = null;
        try {
            Session session = getSession();
            query = session.createSQLQuery("select user1.email, COUNT(commentary1.id_Commentary) as com \n" +
                    " from Memoria_User user1 \n" +
                    " join Commentary commentary1 on user1.email = commentary1.email\n" +
                    "group by user1.email");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public List<Object[]> findNumberOfFriendsForEveryUser() {
        SQLQuery query = null;
        try {
            Session session = getSession();
            query = session.createSQLQuery("select friends1.email1, COUNT(friends1.email2) as com \n" +
                    " from FRIENDS friends1\n" +
                    "group by friends1.EMAIL1");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public List<MemoriaUserEntity> findUsersNotFromBoard(BigDecimal boardId) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from MemoriaUserEntity user where user.email not in (select deskUsers.email from DeskUsersEntity  deskUsers where idDesk = ? )").setBigDecimal(0,boardId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public List<String> findTopUsersOn(BigDecimal boardId) {
        SQLQuery query = null;
        try {
            Session session = getSession();
            query = (SQLQuery) session.createSQLQuery("SELECT email\n" +
            "FROM\n" +
            "  (SELECT email, COUNT(*)\n" +
            "  FROM Commentary com\n" +
            "  JOIN Lable lab\n" +
            "  ON com.id_parent_label = lab.id_label\n" +
            "  JOIN Category cat on lab.id_category = cat.id_catagory\n" +
            "  JOIN Desk des\n" +
            "  ON des.id_desk = "+boardId+"\n" +
            "  AND des.id_desk = cat.id_desk\n" +
            "  WHERE ROWNUM<2\n" +
            " "+ "  GROUP BY email\n" +
            "  )");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public List<String> findTopCategoryOn(BigDecimal boardId) {
        SQLQuery query = null;
        try {
            Session session = getSession();
            query = session.createSQLQuery("SELECT name\n" +
                    "FROM\n" +
                    "  (SELECT cat.name, COUNT(*)\n" +
                    "  FROM Category cat\n" +
                    "  JOIN Desk des\n" +
                    "  ON des.id_desk = "+boardId+"\n" +
                    "  AND des.id_desk = cat.id_desk\n" +
                    "  WHERE ROWNUM<2\n" +
                    "  GROUP BY cat.name\n" +
                    "  )");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }







}
