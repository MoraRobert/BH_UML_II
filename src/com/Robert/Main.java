package com.Robert;

import com.Robert.dao.UmlDrawDao;
import com.Robert.dao.UmlDrawDaoFactory;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    private static final String DB_NAME = "umlDiagram.db";
    private static final String CONNECTION_STRING =
            "jdbc:sqlite:C:\\Users\\Robi\\IdeaProjects\\" +
                    "0001_Peter\\BH_UML_II\\" + DB_NAME;

    private static UmlDrawDao myUmlDao;

    public static void main(String[] args) {

        try (Connection connection = DriverManager
                .getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement()) {

            myUmlDao = UmlDrawDaoFactory.createJdbcDao(connection);

            List<List<Object>> Classes = myUmlDao.queryTableClasses();
            for (List currentClass : Classes) {
                int classId = (int) currentClass.get(0);
                List<FieldModel> currentFields = myUmlDao.queryClassFields(classId);
                List<MethodModel> currentMethods = myUmlDao.queryClassMethods(classId);
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }

    }
}
