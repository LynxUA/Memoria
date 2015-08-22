package com.burlakov.memoria.dao;

import com.burlakov.memoria.model.FriendsEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by denysburlakov on 09.03.15.
 */
public interface FriendsDAO {
    public void createFriends(FriendsEntity friends);
    public List<FriendsEntity> findFriendsByEmail(String email);
    public boolean isFriends(String email1, String email2);
    public FriendsEntity findFriends(String email1, String email2);
    public void deleteFriends(FriendsEntity friendsEntity);
}
