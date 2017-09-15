package com.don.jersey.service.sql;

import com.don.jersey.common.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SqlConnection {

    protected Connection getConnection() throws SQLException
    {
        try
        {
            Class.forName(Constants.POSTGRE_DRIVER);
            return DriverManager.getConnection(
                    Constants.POSTGRE_URL,
                    Constants.POSTGRE_USERNAME,
                    Constants.POSTGRE_PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            throw new SQLException("Driver not found");
        }
    }
}
