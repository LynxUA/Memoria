package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CategoryEntity;
import com.burlakov.memoria.model.DeskEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class CategoryDAOImpl extends DAOTemplate implements CategoryDAO {
    @Override
    public void createCategory(CategoryEntity desk) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(desk);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<CategoryEntity> allCategories() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from CategoryEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public void deleteCategory(BigDecimal id) {
        CategoryEntity contact = (CategoryEntity) getSession().load(
                CategoryEntity.class, id);
        if (null != contact) {

        }
    }

    @Override
    public List<CategoryEntity> findCategotiesByDesk(BigDecimal idDesk) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from CategoryEntity desk " +
                    "where idDesk=?").setBigDecimal(0, idDesk);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
