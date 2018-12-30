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

                clsmod.getName();
                //TODO: draw rectangle with the class' name
                System.out.println("===================");
                System.out.println(clsmod.getName());
                System.out.println("-------------------");


                if (clsmod.getType().equals("class")) {
                    List<FieldModel> fieldsOfOneClass = myUmlDao.queryTableFieldsByClass(clsmod);
                    //TODO: draw rectangle with the fields of a particular class
                    for (FieldModel fieldmod : fieldsOfOneClass) {
                        System.out.println(fieldmod.getAccessModifier() + " " + fieldmod.getType() + " " + fieldmod.getName());
                    }
                    System.out.println("-------------------");
                }


                List<MethodModel> methodsOfOneClass = myUmlDao.queryTableMethodByClass(clsmod);
                //TODO: draw rectangle with the methods of a particular class
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


                for (ClassModel cls : classesOfTheProject) {
                    if (clsmod != cls) {                            //TODO: overwrite equals+hash in ClassModel
                        List<RelationsModel> relationsOfTwoClasses = myUmlDao.queryTableRelationsForTwoClasses(clsmod, cls);
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
