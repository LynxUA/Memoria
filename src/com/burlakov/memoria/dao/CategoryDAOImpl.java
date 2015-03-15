package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CategoryEntity;
import com.burlakov.memoria.model.DeskEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class CategoryDAOImpl extends DAOTemplate implements CategoryDAO {
    @Override
    public void createDesk(CategoryEntity desk) {
        getSession().save(desk);
    }

    @Override
    public List<CategoryEntity> allDesks() {
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
    public void deleteDesk(Integer id) {
        CategoryEntity contact = (CategoryEntity) getSession().load(
                CategoryEntity.class, id);
        if (null != contact) {

        }
    }

    @Override
    public List<CategoryEntity> findCategotiesByDesk(Integer idDesk) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from CategoryEntity desk " +
                    "where idDesk=?").setInteger(0,idDesk);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
