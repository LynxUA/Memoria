package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.CategoryEntity;
import com.burlakov.memoria.model.DeskEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface CategoryDAO {

    public void createCategory(CategoryEntity category);

    public List<CategoryEntity> allCategories();

    public void deleteCategory(BigDecimal id);

    public List<CategoryEntity> findCategotiesByDesk(BigDecimal idDesk);
}
