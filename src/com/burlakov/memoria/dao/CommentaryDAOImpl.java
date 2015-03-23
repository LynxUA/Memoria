package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CommentaryEntity;
import com.burlakov.memoria.model.DeskEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public class CommentaryDAOImpl extends DAOTemplate implements CommentaryDAO {
    @Override
    public void createCommentary(CommentaryEntity commentary) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(commentary);
        session.flush();
        session.close();
        tx.commit();
    }

    @Override
    public List<CommentaryEntity> allCommentaries() {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from CommentaryEntity");
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }

    @Override
    public void deleteCommentary(BigDecimal id) {
        Session session = getSession();
        CommentaryEntity commentary = (CommentaryEntity) session.load(
                CommentaryEntity.class, id);
        if (null != commentary) {
            Transaction tx = session.beginTransaction();
            session.delete(commentary);
            session.flush();
            session.close();
            tx.commit();
        }
    }

    @Override
    public List<CommentaryEntity> findCommentariesByLabel(BigDecimal idLabel) {
        Query query = null;
        try {
            Session session = getSession();
            query = session.createQuery("from CommentaryEntity commentary " +
                    "where idParentLabel=?").setBigDecimal(0,idLabel);
        }catch(Exception e){
            e.printStackTrace();
        }
        return query.list();
    }
}
