package com.Robert;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.List;


public class HelloWorld extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;

    public HelloWorld(List<String> passedList)
    {
        super("UML diagram of project: Cities, roads and county borders");

        mxGraph graph = new mxGraph();

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        String s0 = passedList.get(0);
        String s1 = passedList.get(1);
        String s2 = passedList.get(2);
        String s3 = passedList.get(3);
        String s4 = passedList.get(4);
        String s5 = passedList.get(5);
        String s6 = passedList.get(6);
        String s7 = passedList.get(7);
        String s8 = passedList.get(8);
        String s9 = passedList.get(9);
        String s10 = passedList.get(10);
        String s11 = passedList.get(11);

        try
        {
//            for (int i = 0; i < 5; i++) {
//                Object v = graph.insertVertex(parent, null, str, 20, 20,
//                        80, 30);
//            }

            Object v1 = graph.insertVertex(parent, null, s0, 20, 20,
                    80, 30);
            Object v11 = graph.insertVertex(parent, null, s2+"\n"+s3+"\n"+s4, 20, 50,
                    80, 60);
            Object v111 = graph.insertVertex(parent, null, s5+"()\n"+s6+"()\n"+s7+"()\n"+s8+"()\n"+s9+"()\n"+s10+"()",
                    20, 110,80, 100);
            Object v2 = graph.insertVertex(parent, null, s1, 240, 20,
                    80, 30);
            Object v21 = graph.insertVertex(parent, null, s11+"()", 240, 40,
                    80, 50);
            graph.insertEdge(parent, null, "<<implements>>", v1, v2);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public void acceptString(String str) {

    }

    public static void main(String[] args)
    {

//        HelloWorld frame = new HelloWorld();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 640);
//        frame.setVisible(true);

    }
}
