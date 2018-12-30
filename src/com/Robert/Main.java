package com.Robert;

import com.Robert.dao.UmlDrawDao;
import com.Robert.dao.UmlDrawDaoFactory;
import com.Robert.model.ClassModel;
import com.Robert.model.FieldModel;
import com.Robert.model.MethodModel;
import com.Robert.model.RelationsModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
//drawing, but hardcoded
            List<String> listToPass = new ArrayList<>();
            String hWname1 = classesOfTheProject.get(0).getName();
            String hWname2 = classesOfTheProject.get(4).getName();
            listToPass.add(hWname1);
            listToPass.add(hWname2);
            List<FieldModel> drawingCityFields =
                    myUmlDao.queryTableFieldsByClass(classesOfTheProject.get(0));
            for (FieldModel fieldm : drawingCityFields) {
                listToPass.add(fieldm.getName()); //there are 3 fields
            }
            List<MethodModel> drawingCityMethods =
                    myUmlDao.queryTableMethodByClass(classesOfTheProject.get(0));
            for (MethodModel methmod : drawingCityMethods) {
                listToPass.add(methmod.getName()); // threre are 6 methods
            }
            List<MethodModel> drawingInterfMethods =
                    myUmlDao.queryTableMethodByClass(classesOfTheProject.get(4));
            for (MethodModel methmod : drawingInterfMethods) {
                listToPass.add(methmod.getName());  // there's 1 method
            }
//end of drawing


            for (ClassModel clsmod : classesOfTheProject) {

                //we compose the UML graph of 4 elements, class name, class fields, class
                //methods and class relations
                //firts we retrieve the name

                //the following lines 67-69 are just here temporarily, to put an output on the console
                //the purpose is to show that the data is correctly retrieved from db
                System.out.println("===================");
                System.out.println(clsmod.getName());
                System.out.println("-------------------");


                //fetching the fields of one particular class. Only class, enums and interfaces don' have fields
                if (clsmod.getType().equals("class")) {
                    List<FieldModel> fieldsOfOneClass = myUmlDao.queryTableFieldsByClass(clsmod);

                    //lines 77-85 to be cleared later
                    for (FieldModel fieldmod : fieldsOfOneClass) {
                        if (fieldmod.isCollection() == 0) {
                            System.out.println(fieldmod.getAccessModifier() + " " + fieldmod.getType() + " " + fieldmod.getName());
                        } else {
                            System.out.println(fieldmod.getAccessModifier() + " List<" + fieldmod.getType() + "> " + fieldmod.getName());
                        }

                    }
                    System.out.println("-------------------");
                }


                //fetching the methods
                List<MethodModel> methodsOfOneClass = myUmlDao.queryTableMethodByClass(clsmod);

                //lines 93-104 to be cleared
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

                        //lines 113-116 to be cleared
                        for (RelationsModel relmod : relationsOfTwoClasses) {
                            System.out.println
                                    (relmod.getChildClass().getName() + " " + relmod.getName()+ " " + relmod.getParentClass().getName());
                        }
                    }
                }
                System.out.println();
            }

            //foo Swing using jgraphx
            HelloWorld hv = new HelloWorld(listToPass);
            hv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            hv.setSize(800, 640);
            hv.setVisible(true);

        } catch (SQLException e) {
            System.out.println("Something went wrong with the connection. " + e.getMessage());
        }
    }
}
