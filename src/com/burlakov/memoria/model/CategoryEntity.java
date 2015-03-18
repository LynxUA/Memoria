package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "CATEGORY", schema = "MYTRELLO", catalog = "")
public class CategoryEntity {
    private BigDecimal idCatagory;
    private String name;
    private BigDecimal idDesk;
    private CategoryEntity categoryByIdCatagory;
    private CategoryEntity categoryByIdCatagory_0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "G2")
    @SequenceGenerator(name = "G2", sequenceName = "CATEGORY_SEQ")
    @Column(name = "ID_CATAGORY", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getIdCatagory() {
        return idCatagory;
    }

    public void setIdCatagory(BigDecimal idCatagory) {
        this.idCatagory = idCatagory;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ID_DESK")
    public BigDecimal getIdDesk() {
        return idDesk;
    }

    public void setIdDesk(BigDecimal idDesk) {
        this.idDesk = idDesk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (idCatagory != null ? !idCatagory.equals(that.idCatagory) : that.idCatagory != null) return false;
        if (idDesk != null ? !idDesk.equals(that.idDesk) : that.idDesk != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCatagory != null ? idCatagory.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idDesk != null ? idDesk.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "ID_CATAGORY", referencedColumnName = "ID_CATAGORY", nullable = false)
    public CategoryEntity getCategoryByIdCatagory() {
        return categoryByIdCatagory;
    }

    public void setCategoryByIdCatagory(CategoryEntity categoryByIdCatagory) {
        this.categoryByIdCatagory = categoryByIdCatagory;
    }

    @OneToOne(mappedBy = "categoryByIdCatagory")
    public CategoryEntity getCategoryByIdCatagory_0() {
        return categoryByIdCatagory_0;
    }

    public void setCategoryByIdCatagory_0(CategoryEntity categoryByIdCatagory_0) {
        this.categoryByIdCatagory_0 = categoryByIdCatagory_0;
    }
}
