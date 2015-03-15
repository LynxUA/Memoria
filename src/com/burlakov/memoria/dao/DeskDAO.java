package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.DeskEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by denysburlakov on 14.02.15.
 */
public interface DeskDAO {

    public void createDesk(DeskEntity desk);

    public List<DeskEntity> allDesks();

    public void deleteDesk(Integer id);

    public List<DeskEntity> findDesksByUser(String email);
}
