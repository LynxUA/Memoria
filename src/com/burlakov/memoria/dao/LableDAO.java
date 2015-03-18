package com.burlakov.memoria.dao;


import com.burlakov.memoria.model.LableEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface LableDAO {

    public void createLable(LableEntity desk);

    public List<LableEntity> allLables();

    public void deleteLable(BigDecimal id);

    public List<LableEntity> findLablesByCategory(BigDecimal idCategory);
}
