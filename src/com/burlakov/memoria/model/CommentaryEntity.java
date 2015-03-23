package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "COMMENTARY", schema = "MYTRELLO", catalog = "")
public class CommentaryEntity {
    private BigDecimal idCommentary;
    private String value;
    private byte[] attachment;
    private BigDecimal idParentLabel;
    private String email;
    private BigDecimal idAttributeRecievers;
    private BigDecimal idParentCommentary;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "G4")
    @SequenceGenerator(name = "G4", sequenceName = "COMMENTARY_SEQ")
    @Column(name = "ID_COMMENTARY", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getIdCommentary() {
        return idCommentary;
    }

    public void setIdCommentary(BigDecimal idCommentary) {
        this.idCommentary = idCommentary;
    }

    @Basic
    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "ATTACHMENT")
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Basic
    @Column(name = "ID_PARENT_LABEL")
    public BigDecimal getIdParentLabel() {
        return idParentLabel;
    }

    public void setIdParentLabel(BigDecimal idParentLabel) {
        this.idParentLabel = idParentLabel;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ID_ATTRIBUTE_RECIEVERS")
    public BigDecimal getIdAttributeRecievers() {
        return idAttributeRecievers;
    }

    public void setIdAttributeRecievers(BigDecimal idAttributeRecievers) {
        this.idAttributeRecievers = idAttributeRecievers;
    }

    @Basic
    @Column(name = "ID_PARENT_COMMENTARY")
    public BigDecimal getIdParentCommentary() {
        return idParentCommentary;
    }

    public void setIdParentCommentary(BigDecimal idParentCommentary) {
        this.idParentCommentary = idParentCommentary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentaryEntity that = (CommentaryEntity) o;

        if (!Arrays.equals(attachment, that.attachment)) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (idAttributeRecievers != null ? !idAttributeRecievers.equals(that.idAttributeRecievers) : that.idAttributeRecievers != null)
            return false;
        if (idCommentary != null ? !idCommentary.equals(that.idCommentary) : that.idCommentary != null) return false;
        if (idParentCommentary != null ? !idParentCommentary.equals(that.idParentCommentary) : that.idParentCommentary != null)
            return false;
        if (idParentLabel != null ? !idParentLabel.equals(that.idParentLabel) : that.idParentLabel != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCommentary != null ? idCommentary.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (attachment != null ? Arrays.hashCode(attachment) : 0);
        result = 31 * result + (idParentLabel != null ? idParentLabel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (idAttributeRecievers != null ? idAttributeRecievers.hashCode() : 0);
        result = 31 * result + (idParentCommentary != null ? idParentCommentary.hashCode() : 0);
        return result;
    }

}
