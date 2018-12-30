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
import java.util.List;

public class Main {

    private static final String DB_NAME = "umlDiagram.db";
    private static final String CONNECTION_STRING =
            "jdbc:sqlite:C:\\Users\\Robi\\IdeaProjects\\" +
                    "0001_Peter\\BH_UML_II\\" + DB_NAME;

    private static UmlDrawDao myUmlDao;

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {

            myUmlDao = UmlDrawDaoFactory.createJdbcDao(connection);

            myUmlDao.createTables();

            List<ClassModel> classesOfTheProject = myUmlDao.queryTableClasses();

            for (ClassModel clsmod : classesOfTheProject) {

                //we compose the UML graph of 4 elements, class name, class fields, class
                //methods and class relations
                //firs we retrieve the name
                clsmod.getName();

                //TODO: draw rectangle with the class' name
                //the following lines 43-45 are just here temporarily, to put an output on the console*
                System.out.println("===================");
                System.out.println(clsmod.getName());
                System.out.println("-------------------");


                //fetching the fields of one particular class. Only class, enums and interfaces don' have fields
                if (clsmod.getType().equals("class")) {
                    List<FieldModel> fieldsOfOneClass = myUmlDao.queryTableFieldsByClass(clsmod);

                    //TODO: draw rectangle with the fields of a particular class
                    //lines 54-57 to be cleared later
                    for (FieldModel fieldmod : fieldsOfOneClass) {
                        System.out.println(fieldmod.getAccessModifier() + " " + fieldmod.getType() + " " + fieldmod.getName());
                    }
                    System.out.println("-------------------");
                }


                //fetching the methods
                List<MethodModel> methodsOfOneClass = myUmlDao.queryTableMethodByClass(clsmod);


                //TODO: draw rectangle with the methods of a particular class
                //lines 68-78 to be cleared
                for (MethodModel methmod : methodsOfOneClass) {
                    System.out.print(methmod.getAccessModifier() + " " + methmod.getReturnType() +
                             " " + methmod.getName() + "(");
                    if (methmod.getParameters().size() == 0) System.out.println(")");
                    else {
                        for (int i = 0; i < methmod.getParameters().size() - 1; i++) {
                            System.out.print(methmod.getParameters().get(i) + ", ");
                        }
                        System.out.println(methmod.getParameters().get(methmod.getParameters().size() - 1) + ")");
                    }
                }
                System.out.println("===================");


                //composing the relations
                for (ClassModel cls : classesOfTheProject) {
                    if (clsmod != cls) {                            
                        List<RelationsModel> relationsOfTwoClasses = myUmlDao.queryTableRelationsForTwoClasses(clsmod, cls);

                        //lines 87-91 to be cleared
                        for (RelationsModel relmod : relationsOfTwoClasses) {
                            System.out.println
                                    (relmod.getChildClass().getName() + " " + relmod.getName()+ " " + relmod.getParentClass().getName());
                        }
                    }
                    //TODO: connect two classes with a line
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }
    }
}
