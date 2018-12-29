package com.Robert.dao.impl;

import com.Robert.dao.UmlDrawDao;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UmlDrawDaoImpl implements UmlDrawDao {

    Connection connection;

    public UmlDrawDaoImpl(Connection conn) {
        this.connection = conn;
    }


    @Override
    public void createTables() {

//        try {
//            connection.prepareStatement("CREATE TABLE classes (" +
//                    " `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    "`name` TEXT NOT NULL UNIQUE, " +
//                    "`type` TEXT NOT NULL )");
//
//            connection.prepareStatement("CREATE TABLE fields ( " +
//                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    "`access_modifier` TEXT NOT NULL, " +
//                    "`type` TEXT NOT NULL, `name` TEXT NOT NULL, " +
//                    "`class_id` INTEGER NOT NULL, " +
//                    "`isCollection` INTEGER NOT NULL DEFAULT 0 )");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void insertModel() {

    }

    @Override
    public List<List<Object>> queryTableClasses()  {

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM classes");
            List<List<Object>> tableContent = new ArrayList<>();
            List<Object> classId = new ArrayList<>();
            List<Object> className = new ArrayList<>();
            List<Object> classtype = new ArrayList<>();
            while (results.next()) {
                classId.add(results.getInt("id"));
                className.add(results.getString("name"));
                classtype.add(results.getString("type"));
            }
            tableContent.add(classId);
            tableContent.add(className);
            tableContent.add(classtype);

            return tableContent;
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    @Override
    public List<List<Object>> queryTableFields() throws SQLException {
        return null;
    }

    @Override
    public List<List<Object>> queryTableMethods() throws SQLException {
        return null;
    }

    @Override
    public List<List<Object>> queryTableRelations() throws SQLException {
        return null;
    }

    @Override
    public List<FieldModel> queryClassFields(int classId)  {

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from fields where class_id =" + classId + ";");
            List<FieldModel> classFields = new ArrayList<>();

            while (results.next()) {
                FieldModel currentField = new FieldModel(results.getInt("id"), results.getString("name"));
                currentField.setAccessModifier(results.getString("access_modifier"));
                currentField.setType(results.getString("type"));
                currentField.setCollection(results.getInt("isCollection"));
                classFields.add(currentField);
            }

            return classFields;

        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    @Override
    public List<MethodModel> queryClassMethods(int classId) throws SQLException {
        return null;
    }
}
