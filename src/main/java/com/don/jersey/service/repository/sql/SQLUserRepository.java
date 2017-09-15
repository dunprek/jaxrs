package com.don.jersey.service.repository.sql;


import java.util.List;

import com.don.jersey.common.Constants;
import com.don.jersey.model.User;
import com.don.jersey.service.exception.RepositoryException;
import com.don.jersey.service.exception.SQLRepositoryException;
import com.don.jersey.service.repository.UserRepository;
import com.don.jersey.service.sql.SqlQuery;


public final class SQLUserRepository implements UserRepository {


    private static final String SELECT_USER_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String INSERT_USER = "INSERT INTO"+ Constants.POSTGRE_TB_NAME + "(email, password) VALUES (?, ?)";
    private static final String UPDATE_USER = "UPDATE "+ Constants.POSTGRE_TB_NAME + " SET email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM "+ Constants.POSTGRE_TB_NAME + " WHERE id = ?";

    @Override
    public User addUser(User user) {
        final List<Object> genKeys = executeUpdate(INSERT_USER, user.getId(), user.getUsername());
        long id = (long) genKeys.get(0);

        return new User( user.getId(), user.getUsername());
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        executeUpdate(UPDATE_USER, user.getId(), user.getUsername());
        return user;
    }

    @Override
    public User deleteUser(long id) {
        final User user = getUserById(id);
        executeUpdate(DELETE_USER, id);

        return user;
    }

    private List<Object> executeUpdate(final String queryString, final Object... parameters)
    {
        try
        {
            final List<Object> result = SqlQuery.getBuilder()
                    .query(queryString)
                    .parameters(parameters)
                    .executeUpdate();

            return result;
        }
        catch (SQLRepositoryException e)
        {
            throw new RepositoryException(e.getMessage());
        }
    }

}
