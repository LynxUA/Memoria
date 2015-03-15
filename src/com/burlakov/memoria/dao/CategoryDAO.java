package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CategoryEntity;
import com.burlakov.memoria.model.DeskEntity;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface CategoryDAO {

    public void createDesk(CategoryEntity desk);

    public List<CategoryEntity> allDesks();

    public void deleteDesk(Integer id);

    public List<CategoryEntity> findCategotiesByDesk(Integer idDesk);
}
