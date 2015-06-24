package step01;

//import step01.model_creator;
import step01.preprocess;
import step01.smo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;

public class training
{
        private static String input_filename;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, IOException, FileNotFoundException
    {
        // TODO code application logic here
        
        //int choice=0;
        
        Scanner input = new Scanner(System.in);
    
        smo learner = new smo();
        preprocess pre = new preprocess();

        System.out.print("\nPlease Enter The Training File: ");
        String training_file_name=input.next();
        //File training_file=new File(training_file_name);
        
        System.out.println("\n");
        pre.remove_stopwords(training_file_name);
        pre.lemmatize();
        pre.relevant_convert();
        pre.theme_convert();
        pre.topic_convert();
        pre.style_convert();        

        Thread.sleep(3);
        
        System.out.println("\n");
        String relevant_file_name="InputFiles/relevant_labeled.arff";
        learner.loadARFF(relevant_file_name);
        learner.evaluate();
        learner.learn();
        String relevant_model_name="InputFiles/relevant.model";
        learner.saveModel(relevant_model_name);
        
        System.out.println("\n");
        String theme_file_name="InputFiles/theme_labeled.arff";
        learner.loadARFF(theme_file_name);
        learner.evaluate();
        learner.learn();
        String theme_model_name="InputFiles/theme.model";
        learner.saveModel(theme_model_name);
        
        System.out.println("\n");
        String topic_file_name="InputFiles/topic_labeled.arff";
        learner.loadARFF(topic_file_name);
        //learner.set_classifier();
        learner.evaluate();
        learner.learn();
        String topic_model_name="InputFiles/topic.model";
        learner.saveModel(topic_model_name);
        
        System.out.println("\n");
        String style_file_name="InputFiles/style_labeled.arff";
        learner.loadARFF(style_file_name);
        //learner.set_classifier();
        learner.evaluate();
        learner.learn();
        String style_model_name="InputFiles/style.model";
        learner.saveModel(style_model_name);
        
        //System.out.print("\nPlease Enter The Output File to save model: ");
        //String model_file_name=input.next();
        
        //learner.saveModel(model_file_name);
    /*
    while(choice!=7)
    {
            System.out.println("\n\n\n\tPlease choose your option");
            System.out.println("\n\t1.Naive Bayes");
            //System.out.println("\n\t2.Naive Bayes Predictor");
            System.out.println("\t2.SMO");
            System.out.println("\t3.IBK");
            System.out.println("\t4.PART");
            System.out.println("\t5.J48");
            System.out.println("\t6.Random Forest");
            System.out.println("\t7.Exit");
            System.out.print("\n\n\tChoice: ");
            
            choice=input.nextInt();
            
            if(choice==1)
            {
                naive_bayes_model learner = new naive_bayes_model();
                
                System.out.print("\nPlease Enter The Training File: ");
                String training_file_name=input.next();
        //File training_file=new File(training_file_name);
                
                learner.loadARFF(training_file_name);
                learner.set_classifier();
                learner.evaluate();
                learner.learn();
                
                //System.out.print("\nPlease Enter The Test File: ");
                //String test_file_name=input.next();
        //File test_file=new File(test_file_name);
                
                //learner.testing(test_file_name);
                
                System.out.print("\nPlease Enter The Output File to save models: ");
                String model_file_name=input.next();
                
                learner.saveModel(model_file_name);
              
            }
            
            else if(choice==2)
            {
                smo learner = new smo();
                
                System.out.print("\nPlease Enter The Training File: ");
                String training_file_name=input.next();
        //File training_file=new File(training_file_name);
                
                learner.loadARFF(training_file_name);
                //learner.set_classifier();
                learner.evaluate();
                learner.learn();
                //learner.evaluate();
                
                //learner.learn();
                
                //System.out.print("\nPlease Enter The Test File: ");
                //String test_file_name=input.next();
        //File test_file=new File(test_file_name);
                
                //learner.test(test_file_name);
                
                System.out.print("\nPlease Enter The Output File to save model: ");
                String model_file_name=input.next();
                
                learner.saveModel(model_file_name);
              
            }
            
            else if(choice==3)
            {
                
            }
        }*/
    }
}