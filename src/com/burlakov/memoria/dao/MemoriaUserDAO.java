package com.burlakov.memoria.dao;

import com.burlakov.memoria.dao.exceptions.UserNotFoundException;
import com.burlakov.memoria.model.DeskEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface MemoriaUserDAO {
    public void createUser(MemoriaUserEntity user);

    public List<MemoriaUserEntity> allUsers();

    MemoriaUserEntity findUser(String email);

    public MemoriaUserEntity findUser(String email, String password) throws UserNotFoundException, UserNotFoundException;

    public String getNameByEmail(String email);

    public List<Object[]> findNumberOfCommentsForEveryUser();

    public List<Object[]> findNumberOfFriendsForEveryUser();

    public List<MemoriaUserEntity> findUsersNotFromBoard(BigDecimal boardId);

    List<String> findTopUsersOn(BigDecimal boardId);

    List<String> findTopCategoryOn(BigDecimal boardId);
}
