package com.example.roombai1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("select * from User")
    List<User> getAllUser();

    @Query("select * from User where id = :id")
    User getUserById(int id);

    @Query("select * from User order by id desc")
    User getLastUser();

    @Query("select * from User where name = :name")
    User findUserByName(String name);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User user);
}
