package com.Robert.dao.impl;

import com.Robert.dao.UmlDrawDao;
import com.Robert.model.ClassModel;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;
import com.Robert.model.RelationsModel;

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

        try {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS classes ( " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`name` TEXT NOT NULL UNIQUE, " +
                    "`type` TEXT NOT NULL )");

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS fields ( " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`access_modifier` TEXT NOT NULL, " +
                    "`type` TEXT NOT NULL, " +
                    "`name` TEXT NOT NULL, " +
                    "`class_id` INTEGER NOT NULL, " +
                    "`isCollection` INTEGER NOT NULL DEFAULT 0 )");

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS methods ( " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`name` TEXT NOT NULL, " +
                    "`return_type` TEXT NOT NULL, " +
                    "`access_modifier` TEXT NOT NULL, " +
                    "`argument01` BLOB, " +
                    "`argument02` BLOB, " +
                    "`argument03` BLOB, " +
                    "`class_id` INTEGER NOT NULL )");

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS relations ( " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "`name` TEXT NOT NULL, " +
                    "`parent_class_id` INTEGER NOT NULL, " +
                    "`child_class_id` INTEGER NOT NULL )");



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertModel() {

    }

    @Override
    public List<ClassModel> queryTableClasses() {
        List<ClassModel> projectClasses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM classes");
            while (results.next()) {
                ClassModel currentCls = new ClassModel(
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("type"));
                projectClasses.add(currentCls);
            }
            return projectClasses;
        } catch (SQLException e){
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<FieldModel> queryTableFieldsByClass(ClassModel subjectClass) {
        List<FieldModel> classFields = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM fields WHERE class_id = " + subjectClass.getId() );
            while (results.next()) {
                FieldModel currentField = new FieldModel(
                        results.getInt("id"),
                        results.getString("access_modifier"),
                        results.getString("type"),
                        results.getString("name"),
                        subjectClass,
                        results.getInt("isCollection"));
                classFields.add(currentField);
            }
            return classFields;
        } catch (SQLException e){
            e.getMessage();return null;
        }

        //return null;
    }

    @Override
    public List<MethodModel> queryTableMethodByClass(ClassModel subjectClass) {
        List<MethodModel> classMethods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM methods WHERE class_id = " + subjectClass.getId());
            while (results.next()) {
                List<String> argList = new ArrayList<>();
                if (!results.getString("argument01").equals("NULL")) argList.add(results.getString("argument01"));
                if (!results.getString("argument02").equals("NULL")) argList.add(results.getString("argument02"));
                if (!results.getString("argument03").equals("NULL")) argList.add(results.getString("argument03"));
                MethodModel currentMethod = new MethodModel (
                        results.getInt("id"),
                        results.getString("name"),
                        results.getString("return_type"),
                        results.getString("access_modifier"),
                        argList,
                        subjectClass);
                classMethods.add(currentMethod);
            }

            return classMethods;
        } catch (SQLException e) {
            e.getMessage();
        }
        return classMethods;
    }

    @Override
    public List<RelationsModel> queryTableRelationsForTwoClasses(ClassModel parentClass, ClassModel childClass) {
        List<RelationsModel> relationsOfTwoClasses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(
                    "SELECT * FROM relations WHERE (parent_class_id = " + parentClass.getId() +
                            " AND child_class_id = " + childClass.getId() + " )" );
            while (results.next()) {
                RelationsModel currentRelation = new RelationsModel(
                        results.getInt("id"),
                        results.getString("name"),
                        parentClass,
                        childClass);
                relationsOfTwoClasses.add(currentRelation);
            }
            return relationsOfTwoClasses;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
}
