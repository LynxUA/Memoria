package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "ATTRIBUTES", schema = "MYTRELLO", catalog = "")
public class AttributesEntity {
    private BigDecimal idAttribute;
    private BigDecimal idAttributeType;
    private String value;

    @Id
    @Column(name = "ID_ATTRIBUTE")
    public BigDecimal getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(BigDecimal idAttribute) {
        this.idAttribute = idAttribute;
    }

    @Basic
    @Column(name = "ID_ATTRIBUTE_TYPE")
    public BigDecimal getIdAttributeType() {
        return idAttributeType;
    }

    public void setIdAttributeType(BigDecimal idAttributeType) {
        this.idAttributeType = idAttributeType;
    }

    @Basic
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributesEntity that = (AttributesEntity) o;

        if (idAttribute != null ? !idAttribute.equals(that.idAttribute) : that.idAttribute != null) return false;
        if (idAttributeType != null ? !idAttributeType.equals(that.idAttributeType) : that.idAttributeType != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAttribute != null ? idAttribute.hashCode() : 0;
        result = 31 * result + (idAttributeType != null ? idAttributeType.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
