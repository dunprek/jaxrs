package com.don.jersey.service;


import com.don.jersey.model.User;
import com.don.jersey.service.sql.Mapper;
import com.don.jersey.service.sql.RowMapper;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;



@Mapper
public final class SQLUserMapper implements RowMapper<User>
{
    @Override
    public User mapRow(ResultSet rs) throws SQLException
    {
        return new User(rs.getLong("userid"), rs.getString("username"));
    }

    @Override
    public Type getType()
    {
        return User.class;
    }
}
