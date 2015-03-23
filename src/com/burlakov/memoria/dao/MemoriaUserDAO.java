package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface MemoriaUserDAO {
    public void createUser(MemoriaUserEntity user);

    public List<MemoriaUserEntity> allUsers();

    public MemoriaUserEntity findUser(String email, String password);

    public String getNameByEmail(String email);
}
