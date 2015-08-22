package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.PrivateMessagesEntity;

import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface PrivateMessagesDAO{
    public void sendMessage(PrivateMessagesEntity user);

    public List<PrivateMessagesEntity> findAllInMessages(String email);

    public List<PrivateMessagesEntity> findAllOutMessages(String email);
}
