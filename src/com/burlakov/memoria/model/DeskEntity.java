package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "DESK", schema = "MYTRELLO", catalog = "")
public class DeskEntity {
    private BigDecimal idDesk;
    private String name;
    private byte[] background;

    @Id
    @Column(name = "ID_DESK")
    public BigDecimal getIdDesk() {
        return idDesk;
    }

    public void setIdDesk(BigDecimal idDesk) {
        this.idDesk = idDesk;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeskEntity that = (DeskEntity) o;

        if (!Arrays.equals(background, that.background)) return false;
        if (idDesk != null ? !idDesk.equals(that.idDesk) : that.idDesk != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDesk != null ? idDesk.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (background != null ? Arrays.hashCode(background) : 0);
        return result;
    }
}
