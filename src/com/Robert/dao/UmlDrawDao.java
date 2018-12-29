package com.Robert.dao;

import com.Robert.model.DrawUMLProjectModel;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;

import java.sql.SQLException;
import java.util.List;

public interface UmlDrawDao {

    void createTables();
    void insertModel();
    List<List<Object>> queryTableClasses() throws SQLException;
    List<List<Object>> queryTableFields() throws SQLException;
    List<List<Object>> queryTableMethods() throws SQLException;
    List<List<Object>> queryTableRelations() throws SQLException;

    List<FieldModel> queryClassFields(int classId) throws SQLException;
    List<MethodModel> queryClassMethods(int classId) throws SQLException;

}
