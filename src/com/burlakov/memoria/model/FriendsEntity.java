package com.burlakov.memoria.model;

import javax.persistence.*;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "FRIENDS", schema = "MYTRELLO", catalog = "")
@IdClass(FriendsEntityPK.class)
public class FriendsEntity {
    private String email1;
    private String email2;

    @Id
    @Column(name = "EMAIL1")
    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    @Id
    @Column(name = "EMAIL2")
    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendsEntity that = (FriendsEntity) o;

        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email1 != null ? email1.hashCode() : 0;
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        return result;
    }
}
