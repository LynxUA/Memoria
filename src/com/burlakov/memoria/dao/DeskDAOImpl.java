package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;
import org.hibernate.*;

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
        Session session = getSession();
        DeskEntity desk = (DeskEntity) session.load(
                DeskEntity.class, id);
        if (null != desk) {
            Transaction tx = session.beginTransaction();
            session.delete(desk);
            session.flush();
            session.close();
            tx.commit();
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

    @Override
    public List<Object[]> findNumberOfUsersForEveryDesk() {
        SQLQuery query = null;
        try {
            Session session = getSession();
            query = session.createSQLQuery("select desk1.name, COUNT(user1.EMAIL) as users\n" +
                    "from Desk desk1 join Desk_Users user1 on desk1.id_Desk = user1.id_Desk\n" +
                    "group by desk1.name");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }


}
