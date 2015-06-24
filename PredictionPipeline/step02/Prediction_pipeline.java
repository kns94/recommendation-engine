/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package step02;

import step02.model_evaluator;
import step02.preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;

/**
 *
 * @author Tortuga
 */
public class Prediction_pipeline 
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        try
        {    
            System.out.println("\n");
            Scanner input = new Scanner(System.in);

            model_evaluator model = new model_evaluator();
            preprocess pre = new preprocess();

            //Expected output format is ID [Tab] Tweet
            System.out.print("\nPlease Enter The Unlabeled Test File: ");
            String test_file_name=input.next();
            
          
           

            System.out.println("\n");
            pre.remove_stopwords(test_file_name);
            pre.lemmatize();
            pre.relevant_convert();
            pre.theme_convert();
            pre.topic_convert();
            pre.style_convert();

            String outfileName = "TempFiles/test_label.csv";
            
            model.loadfile_input(test_file_name);

            Thread.sleep(7);

            System.out.println("\n");
			model.loadModel_relevant("InputFiles/relevant.model");
            model.test_relevant("InputFiles/relevant.arff");
            model.savelabel("OutputFiles/relevant_labels.arff");

            System.out.println("\n");
			model.loadModel_theme("InputFiles/theme.model");
            model.savelabel("OutputFiles/theme_labels.arff");
            model.test_theme("InputFiles/theme.arff");
        
            System.out.println("\n");
			model.loadModel_topic("InputFiles/topic.model");
            model.test_topic("InputFiles/topic.arff");
            model.savelabel("OutputFiles/topic_labels.arff");

            System.out.println("\n");
			 model.loadModel_style("InputFiles/style.model");
            model.test_style("InputFiles/style.arff");
            model.savelabel("OutputFiles/style_labels.arff");
            
            model.combinelabel(outfileName);
            
            Runtime.getRuntime().exec("python PythonFiles/output.py"); 
        }
        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Please rectify the error====");
            
        }
    }
    
}
