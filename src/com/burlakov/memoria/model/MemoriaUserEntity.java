package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "MEMORIA_USER", schema = "MYTRELLO", catalog = "")
public class MemoriaUserEntity {
    private String email;
    private String name;
    private byte[] avatar;
    private BigDecimal idRole;
    private String password;
    private BigDecimal poits;
    private String isOnline;

    @Id
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "AVATAR")
    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "ID_ROLE")
    public BigDecimal getIdRole() {
        return idRole;
    }

    public void setIdRole(BigDecimal idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "POITS", insertable=false)
    public BigDecimal getPoits() {
        return poits;
    }

    public void setPoits(BigDecimal poits) {
        this.poits = poits;
    }

    @Basic
    @Column(name = "IS_ONLINE", insertable=false)
    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoriaUserEntity that = (MemoriaUserEntity) o;

        if (!Arrays.equals(avatar, that.avatar)) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (idRole != null ? !idRole.equals(that.idRole) : that.idRole != null) return false;
        if (isOnline != null ? !isOnline.equals(that.isOnline) : that.isOnline != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (poits != null ? !poits.equals(that.poits) : that.poits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (avatar != null ? Arrays.hashCode(avatar) : 0);
        result = 31 * result + (idRole != null ? idRole.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (poits != null ? poits.hashCode() : 0);
        result = 31 * result + (isOnline != null ? isOnline.hashCode() : 0);
        return result;
    }
}
