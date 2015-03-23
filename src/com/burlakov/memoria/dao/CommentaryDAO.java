package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CommentaryEntity;
import com.burlakov.memoria.model.LableEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface CommentaryDAO {

    public void createCommentary(CommentaryEntity commentary);

    public List<CommentaryEntity> allCommentaries();

    public void deleteCommentary(BigDecimal id);

    public List<CommentaryEntity> findCommentariesByLabel(BigDecimal idLabel);
}
