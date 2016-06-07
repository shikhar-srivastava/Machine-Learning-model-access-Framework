import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.*;
import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import java.sql.*;  
import java.io.*;


public class ExecuteServlet extends HttpServlet{

   protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {


        //------------De-Serializing the SAVED model---------------------
        response.setContentType("text/html"); 
        System.out.println("In ExecuteServlet");
        String mType = request.getParameter("mType");
        String dType = request.getParameter("dType");
        String data= request.getParameter("data");
        int count;
        if(dType.equals("3"))count=3;
        else count=23;
        int i=0;
        double inst[]= new double[count];
        for(String str:data.split(','))
            inst[i++]=Double.parseDouble(str);
        if(i==(count-1))System.out.println("Input Read Successfully: i: "+i);

        //Creating New Instance here
        Instance instance=new DenseInstance(inst);


        if(!mType.equals("ann"))
        {
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("C:/apache-tomcat-8.0.33/webapps/MachineLearningForMedicalDataSets/models/"+mType+""+dType+".model"));
            Classifier cl = (Classifier) ois.readObject();
            ois.close();
         }


        //Dataset dataForClassification = FileHandler.loadDataset(new File("D:/Deeplearning4Java/dl4j_0.4_examples/src/main/resources/classification/Feature_test_norm.csv"),0,",");

       Map<Object, PerformanceMeasure> pm = EvaluateDataset.testDataset(knn, dataForClassification);
       for (Object o : pm.keySet())
          System.out.println(o + ":\nFMeasure: " + pm.get(o).getFMeasure()+"\nPrecision: "+ pm.get(o).getPrecision()+"\nRecall: "+ pm.get(o).getRecall());

        //System.out.println(pm.getCost());


    }

}