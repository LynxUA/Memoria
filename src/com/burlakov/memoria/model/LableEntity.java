package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "LABLE", schema = "MYTRELLO", catalog = "")
public class LableEntity {
    private BigDecimal idLabel;
    private String name;
    private byte[] background;
    private BigDecimal idCategory;
    private CommentaryEntity commentaryByIdLabel;
    private CategoryEntity categoryByIdLabel;

    @Id
    @Column(name = "ID_LABEL")
    public BigDecimal getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(BigDecimal idLabel) {
        this.idLabel = idLabel;
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
    @Column(name = "BACKGROUND")
    public byte[] getBackground() {
        return background;
    }

    public void setBackground(byte[] background) {
        this.background = background;
    }

    @Basic
    @Column(name = "ID_CATEGORY")
    public BigDecimal getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(BigDecimal idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LableEntity that = (LableEntity) o;

        if (!Arrays.equals(background, that.background)) return false;
        if (idCategory != null ? !idCategory.equals(that.idCategory) : that.idCategory != null) return false;
        if (idLabel != null ? !idLabel.equals(that.idLabel) : that.idLabel != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLabel != null ? idLabel.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (background != null ? Arrays.hashCode(background) : 0);
        result = 31 * result + (idCategory != null ? idCategory.hashCode() : 0);
        return result;
    }



    @OneToOne
    @JoinColumn(name = "ID_LABEL", referencedColumnName = "ID_CATAGORY", nullable = false)
    public CategoryEntity getCategoryByIdLabel() {
        return categoryByIdLabel;
    }

    public void setCategoryByIdLabel(CategoryEntity categoryByIdLabel) {
        this.categoryByIdLabel = categoryByIdLabel;
    }
}
