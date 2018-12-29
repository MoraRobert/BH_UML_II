package com.Robert.dao;

import com.Robert.model.*;

import java.sql.SQLException;
import java.util.List;

public interface UmlDrawDao {

    void createTables();
    void insertModel();

    List<ClassModel> queryTableClasses();
    List<FieldModel> queryTableFieldsByClass(ClassModel subjectClass);
    List<MethodModel> queryTableMethodByClass(ClassModel subjectClass);
    //TODO: query relations in associoation with two classes

    List<RelationsModel> queryTableRelationsForTwoClasses(ClassModel parentClass, ClassModel childClass);

//    List<List<Object>> queryTableClasses();
//    List<List<Object>> queryTableFields() throws SQLException;
//    List<List<Object>> queryTableMethods() throws SQLException;
//    List<List<Object>> queryTableRelations() throws SQLException;
//
//    List<FieldModel> queryClassFields(int classId);
//    List<MethodModel> queryClassMethods(int classId);

}
