package com.don.jersey.service.sql;

import com.don.jersey.service.exception.SQLRepositoryException;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SqlSelect<T> extends SqlOperation
{
    private final RowMapper<?> mapper;

    public SqlSelect(Type type, String queryString, List<Object> parameters)
    {
        super(queryString, parameters);

        if (rowMappers.get(type) == null)
        {
            throw new SQLRepositoryException("No mapper found for class:" + type);
        }

        this.mapper = rowMappers.get(type);
    }

    @SuppressWarnings("unchecked")
    public List<T> executeSelect()
    {
        try (final Connection connection = getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement(queryString);

            setStatementValues(stmt);

            List<T> result = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                result.add((T) mapper.mapRow(rs));
            }

            return result;

        }
        catch (SQLException e)
        {
            throw new SQLRepositoryException(e.getMessage());
        }
    }
}