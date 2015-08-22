package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import com.burlakov.memoria.model.DeskUsersEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 18.03.15.
 */
public interface DeskUserDAO {
    public void createDeskUser(DeskUsersEntity deskUser);
    public List<DeskUsersEntity> allDeskUsers();
    public List<DeskUsersEntity> findDeskUsersByDeskId(BigDecimal idDesk);
}
