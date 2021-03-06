package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.LableEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class LableDAOImpl extends  DAOTemplate implements LableDAO {

    @Override
    public LableEntity findLable(BigDecimal id) {
        Session session  = getSession();
        LableEntity lableEntity = (LableEntity) session.load(
                LableEntity.class, id);
        session.close();
        return lableEntity;

    }

    @Override
    public void createLable(LableEntity lable) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(lable);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public void renameLable(BigDecimal id, String name) {
        Query query;
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            query = session.createQuery("update LableEntity set name = ? where idLabel = ?").setString(0, name).setBigDecimal(1, id);
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<LableEntity> allLables() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from LableEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public void deleteLable(BigDecimal id) {
        Session session = getSession();
        LableEntity lable = (LableEntity) session.load(
                LableEntity.class, id);
        if (null != lable) {
            Transaction tx = session.beginTransaction();
            session.delete(lable);
            session.flush();
            session.close();
            tx.commit();
        }
    }

    @Override
    public List<LableEntity> findLablesByCategory(BigDecimal idCategory) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from LableEntity lable " +
                    "where idCategory=?").setBigDecimal(0,idCategory);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
