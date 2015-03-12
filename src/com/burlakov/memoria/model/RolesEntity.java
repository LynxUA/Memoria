package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "ROLES", schema = "MYTRELLO", catalog = "")
public class RolesEntity {
    private BigDecimal idRole;
    private String name;

    @Id
    @Column(name = "ID_ROLE")
    public BigDecimal getIdRole() {
        return idRole;
    }

    public void setIdRole(BigDecimal idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (idRole != null ? !idRole.equals(that.idRole) : that.idRole != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRole != null ? idRole.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
