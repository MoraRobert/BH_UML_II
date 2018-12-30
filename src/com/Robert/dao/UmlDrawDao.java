package com.Robert.dao;

import com.Robert.model.*;

import java.util.List;

public interface UmlDrawDao {

    void createTables();
    void insertModel();

    List<ClassModel> queryTableClasses();
    List<FieldModel> queryTableFieldsByClass(ClassModel subjectClass);
    List<MethodModel> queryTableMethodByClass(ClassModel subjectClass);
    List<RelationsModel> queryTableRelationsForTwoClasses(ClassModel parentClass, ClassModel childClass);


}
