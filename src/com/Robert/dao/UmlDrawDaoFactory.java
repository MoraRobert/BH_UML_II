package com.Robert.dao;

import com.Robert.dao.UmlDrawDao;
import com.Robert.dao.impl.UmlDrawDaoImpl;
import java.sql.Connection;

public class UmlDrawDaoFactory {

    public static UmlDrawDao createJdbcDao(Connection connection) {

        return new UmlDrawDaoImpl(connection);
    }
}
