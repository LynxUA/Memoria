package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "DESK_USERS", schema = "MYTRELLO", catalog = "")
@IdClass(DeskUsersEntityPK.class)
public class DeskUsersEntity {
    private String email;
    private BigDecimal idDesk;

    @Id
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
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

        DeskUsersEntity that = (DeskUsersEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (idDesk != null ? !idDesk.equals(that.idDesk) : that.idDesk != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (idDesk != null ? idDesk.hashCode() : 0);
        return result;
    }


}
