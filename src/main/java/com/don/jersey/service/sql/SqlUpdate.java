package com.don.jersey.service.sql;

import com.don.jersey.service.exception.SQLRepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public final class SqlUpdate extends SqlOperation
{
    public SqlUpdate(String queryString, List<Object> parameters)
    {
        super(queryString, parameters);
    }

    public List<Object> executeUpdate()
    {
        try (final Connection connection = getConnection())
        {
            connection.setAutoCommit(false);

            try (final PreparedStatement stmt = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS))
            {
                setStatementValues(stmt);

                final int affectedRows = stmt.executeUpdate();
                List<Object> result = new ArrayList<>();

                if (affectedRows > 0)
                {
                    ResultSet rs = stmt.getGeneratedKeys();

                    if (queryString.toUpperCase().contains("DELETE") || queryString.toUpperCase().contains("UPDATE"))
                    {
                        connection.commit();

                        return result;
                    }
                    else
                    {
                        if (rs.next())
                        {
                            do
                            {
                                result.add(rs.getObject(1));
                            }
                            while (rs.next());
                        }

                        connection.commit();
                        return result;
                    }
                }

                connection.rollback();

                throw new SQLRepositoryException("Could not execute query: " + queryString);
            }
            catch (SQLException e)
            {
                connection.rollback();
                throw new SQLRepositoryException(e.getMessage());
            }
        }
        catch (SQLException e)
        {
            throw new SQLRepositoryException(e.getMessage());
        }
    }
}