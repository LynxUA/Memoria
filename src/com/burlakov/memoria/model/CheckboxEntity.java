package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "CHECKBOX", schema = "MYTRELLO", catalog = "")
public class CheckboxEntity {
    private BigDecimal idCheckbox;
    private BigDecimal idAttributeText;
    private String email;
    private BigDecimal idLabel;

    @Id
    @Column(name = "ID_CHECKBOX")
    public BigDecimal getIdCheckbox() {
        return idCheckbox;
    }

    public void setIdCheckbox(BigDecimal idCheckbox) {
        this.idCheckbox = idCheckbox;
    }

    @Basic
    @Column(name = "ID_ATTRIBUTE_TEXT")
    public BigDecimal getIdAttributeText() {
        return idAttributeText;
    }

    public void setIdAttributeText(BigDecimal idAttributeText) {
        this.idAttributeText = idAttributeText;
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
    @Column(name = "ID_LABEL")
    public BigDecimal getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(BigDecimal idLabel) {
        this.idLabel = idLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckboxEntity that = (CheckboxEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (idAttributeText != null ? !idAttributeText.equals(that.idAttributeText) : that.idAttributeText != null)
            return false;
        if (idCheckbox != null ? !idCheckbox.equals(that.idCheckbox) : that.idCheckbox != null) return false;
        if (idLabel != null ? !idLabel.equals(that.idLabel) : that.idLabel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCheckbox != null ? idCheckbox.hashCode() : 0;
        result = 31 * result + (idAttributeText != null ? idAttributeText.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (idLabel != null ? idLabel.hashCode() : 0);
        return result;
    }
}
