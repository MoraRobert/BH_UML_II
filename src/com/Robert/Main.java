package com.Robert;

import com.Robert.dao.UmlDrawDao;
import com.Robert.dao.UmlDrawDaoFactory;
import com.Robert.model.ClassModel;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;
import com.Robert.model.RelationsModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

            //myUmlDao.createTables();
            List<ClassModel> classesOfTheProject = myUmlDao.queryTableClasses();

            List<String> classNames = new ArrayList<>();
            for (ClassModel cls : classesOfTheProject) {
                classNames.add(cls.getName());
            }



            for (ClassModel clsmod : classesOfTheProject) {
                clsmod.getName();
                //TODO: draw rectangle with the class' name
                System.out.println("===================");
                System.out.println(clsmod.getName());
                System.out.println("-------------------");
                List<FieldModel> fieldsOfOneClass = myUmlDao.queryTableFieldsByClass(clsmod);
                //TODO: draw rectangle with the fields of a particular class
                for (FieldModel fieldmod : fieldsOfOneClass) {
                    System.out.println(fieldmod.getAccessModifier() + " " + fieldmod.getType() + " " + fieldmod.getName());
                }
                System.out.println("-------------------");
                List<MethodModel> methodsOfOneClass = myUmlDao.queryTableMethodByClass(clsmod);
                //TODO: draw rectangle with the methods of a particular class
                for (MethodModel methmod : methodsOfOneClass) {
                    System.out.println(methmod.getAccessModifier() + " " + methmod.getReturnType() +
                             " " + methmod.getName() + " " +
                            "( " + Arrays.toString(methmod.getParameters().toArray()) + " )") ;
                }
                System.out.println("===================");
                for (ClassModel cls : classesOfTheProject) {
                    if (clsmod != cls) {                            //TODO: overwrite equals+hash in ClassModel
                        List<RelationsModel> relationsOfTwoClasses =
                            myUmlDao.queryTableRelationsForTwoClasses(clsmod, cls); //TODO: clerify parent/child
                        for (RelationsModel relmod : relationsOfTwoClasses) {
                            System.out.println
                                    (relmod.getChildClass().getName() + " " + relmod.getName()+ " " + relmod.getParentClass().getName());
                        }
                    }
                    //TODO: connect two classes with a line
                }
            }


//            myUmlDao.
//            for () {
//                for () {
//
//                }
//            }

//            List<List<Object>> Classes = myUmlDao.queryTableClasses();
//            for (List currentClass : Classes) {
//                int classId = (int) currentClass.get(0);
//                List<FieldModel> currentFields = myUmlDao.queryClassFields(classId);
//                List<MethodModel> currentMethods = myUmlDao.queryClassMethods(classId);
//            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }

    }
}
