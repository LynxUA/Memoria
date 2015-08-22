package com.burlakov.memoria.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by denysburlakov on 08.03.15.
 */
@Entity
@Table(name = "PRIVATE_MESSAGES", schema = "MYTRELLO", catalog = "")
public class PrivateMessagesEntity {
    private BigDecimal idMessage;
    private String text;
    private Timestamp msgDate;
    private String sender;
    private String reciever;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "M1")
    @SequenceGenerator(name = "M1", sequenceName = "MESSAGE_SEQ")
    @Column(name = "ID_MESSAGE", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(BigDecimal idMessage) {
        this.idMessage = idMessage;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MSG_DATE")
    public Timestamp getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Timestamp msgDate) {
        this.msgDate = msgDate;
    }

    @Basic
    @Column(name = "SENDER")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "RECIEVER")
    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateMessagesEntity that = (PrivateMessagesEntity) o;

        if (idMessage != null ? !idMessage.equals(that.idMessage) : that.idMessage != null) return false;
        if (msgDate != null ? !msgDate.equals(that.msgDate) : that.msgDate != null) return false;
        if (reciever != null ? !reciever.equals(that.reciever) : that.reciever != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMessage != null ? idMessage.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (msgDate != null ? msgDate.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (reciever != null ? reciever.hashCode() : 0);
        return result;
    }

}
