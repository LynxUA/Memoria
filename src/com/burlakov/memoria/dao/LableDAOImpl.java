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
    public void createLable(LableEntity lable) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(lable);
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
        LableEntity lable = (LableEntity) getSession().load(
                LableEntity.class, id);
        if (null != lable) {

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
