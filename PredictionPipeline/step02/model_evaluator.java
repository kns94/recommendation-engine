package step02;

import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.core.stemmers.LovinsStemmer;
//import weka.core.instances;
//import weka.core.stopwords;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.filters.unsupervised.attribute.Remove;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tortuga
 */
public class model_evaluator 
{
    
    static FilteredClassifier classifier;
    
    static Instances test,labeled;
    
    //static String outfileName="Output_Label.csv";
    //static File outputFile = new File(outfileName);
    
    static LinkedList<String> out = new LinkedList<String>();
    
    static int count=0;
    
    
    //static BufferedWriter out_writer = new BufferedWriter(new FileWriter("Output_Label.csv"));

    public static void loadfile_input(String fileName) 
    {
    try
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //StringBuilder sb = new StringBuilder();
            String line;

        while ((line = br.readLine()) != null)  
        {
                //sb.append(line);
                //sb.append(System.lineSeparator());
                //line = br.readLine();
            String[] keywords = line.split("\t");
            String id = keywords[0];
            String tweet = keywords[1];
            String val = id+"\t"+tweet;
            out.add(val);
        
        }
    }

    catch(Exception e)
    {
        System.out.println(e);
        System.out.println("=== Input File could not be loaded === "+fileName);
        System.exit(0);
    }
    }

    public static void loadModel_relevant(String fileName) 
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object tmp = in.readObject();
            classifier = (FilteredClassifier) tmp;
            in.close();
            System.out.println("===== Loaded model: " + fileName + " =====");
        } 
        
        catch (Exception e) 
        {
            System.out.println(e);
            // Given the cast, a ClassNotFoundException must be caught along with the IOException
            System.out.println("Problem found when reading: " + fileName);
            System.exit(0);
    }
    }
    
    public static void test_relevant(String fileName)
    {
        try
        {    
            //FileWriter writer2 = new FileWriter(outfileName,true);
            //PrintWriter writer = new PrintWriter(writer2);
            
            //BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));
            
            test = new Instances(new BufferedReader(new FileReader(fileName)));

                // set class attribute
            test.setClassIndex(test.numAttributes() - 1);

                // create copy
            labeled = new Instances(test);

            //System.out.println("flag:"+test.numInstances());

            for (int i = 0; i < test.numInstances(); i++)
            {   
                double pred = classifier.classifyInstance(test.instance(i));
                labeled.instance(i).setClassValue(pred);
				
				//System.out.println(test.instance(i));
				//System.out.println(labeled.instance(i));
                
                //String tweetid= String.valueOf(test.instance(i).value(0));
                //System.out.print(tweetid+"\t");
                //writer.write(tweetid);
                //writer.write("\t");
                //String tweet = test.instance(i).stringValue(1);
                //writer.write(test.instance(i).stringValue(1));
                //System.out.print(test.instance(i).stringValue(1)+"\t");
                //writer.write("\t");
                String label= String.valueOf(labeled.instance(i).stringValue(2));
                //writer.write(label);
                //writer.write("\t");
                //System.out.println(label);
                //System.out.print(test.instance(i).stringValue(1)+"\t");
                
                //writer.write("\n");
                //writer.newLine();
                //writer.flush();
                

                //System.out.print("ID: " + test.instance(i).value(0));
                //System.out.print(", Tweet: " + test.instance(i).stringValue(1));
                //System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
                //System.out.println(", predicted: " + labeled.instance(i));
                
                String temp=out.get(i);
                String test=temp+"\t"+label;
				//System.out.println(test);
                out.set(i,test);
                count++;
				
				

                //System.out.println("flag: "+out.get(i));
            }
				System.out.println("===== Relevant Labels Predicted =====");
                //writer.flush();
                //writer.close();
        }
        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===== Problem found while testing ===== "+fileName);
            System.exit(0);
        }
    }
    
    public static void loadModel_theme(String fileName) 
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object tmp = in.readObject();
            classifier = (FilteredClassifier) tmp;
            in.close();
            System.out.println("===== Loaded model: " + fileName + " =====");
        } 
        
        catch (Exception e) 
        {
            System.out.println(e);
            // Given the cast, a ClassNotFoundException must be caught along with the IOException
            System.out.println("Problem found when reading: " + fileName);
            System.exit(0);
    }
    }
    
    public static void test_theme(String fileName)
    {
        try
        {    
            //FileWriter writer2 = new FileWriter(outfileName,true);
            //PrintWriter writer = new PrintWriter(writer2);
            
            test = new Instances(new BufferedReader(new FileReader(fileName)));

                // set class attribute
                test.setClassIndex(test.numAttributes() - 1);

                // create copy
                labeled = new Instances(test);

                for (int i = 0; i < test.numInstances(); i++)
                {   
                    double pred = classifier.classifyInstance(test.instance(i));
                    labeled.instance(i).setClassValue(pred);

                    //writer.write("\t");
                    //writer.write("\t");
                    //writer.write("\t");
                    //String label= String.valueOf(labeled.instance(i).stringValue(2));
                    //writer.write(label);
                    //writer.write("\t");
                    //writer.write(String.valueOf(labeled.instance(i)));
                    
                    //writer.write("\n");
                    //writer.newLine();
                    //writer.flush();
                    
                    
                    //System.out.print("ID: " + test.instance(i).value(0));
                    //System.out.print(", Tweet: " + test.instance(i).stringValue(1));
                    //System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
                    //System.out.println(", predicted: " + labeled.instance(i));
                    String temp=out.get(i);
                    String label= String.valueOf(labeled.instance(i).stringValue(2));
                    String newtemp=temp+"\t"+label;
                    out.set(i,newtemp);
                }
                System.out.println("===== Content Theme Labels Predicted =====");
                //writer.close();
            }
        
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("===== Problem found while testing ===== "+fileName);
                System.exit(0);
            }
    }
    
    public static void loadModel_topic(String fileName) 
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object tmp = in.readObject();
            classifier = (FilteredClassifier) tmp;
            in.close();
            System.out.println("===== Loaded model: " + fileName + " =====");
        } 
        
        catch (Exception e) 
        {
            System.out.println(e);
            // Given the cast, a ClassNotFoundException must be caught along with the IOException
            System.out.println("Problem found when reading: " + fileName);
            System.exit(0);
    }
    }
    
    public static void test_topic(String fileName)
    {
        try
        {    
            //BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));
            
            test = new Instances(new BufferedReader(new FileReader(fileName)));

                // set class attribute
                test.setClassIndex(test.numAttributes() - 1);

                // create copy
                labeled = new Instances(test);

                for (int i = 0; i < test.numInstances(); i++)
                {   
                    double pred = classifier.classifyInstance(test.instance(i));
                    labeled.instance(i).setClassValue(pred);

                    //writer.write("\t");
                    //writer.write("\t");
                    //writer.write("\t");
                    //writer.write("\t");
                    //writer.write(String.valueOf(labeled.instance(i)));
                    
                    //writer.newLine();
                    //writer.flush();
                    
                    //System.out.print("ID: " + test.instance(i).value(0));
                    //System.out.print(", Tweet: " + test.instance(i).stringValue(1));
                    //System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
                    //System.out.println(", predicted: " + labeled.instance(i));
                    
                    String temp=out.get(i);
                    String label= String.valueOf(labeled.instance(i).stringValue(2));
                    String newtemp=temp+"\t"+label;
                    out.set(i,newtemp);
                }
                System.out.println("===== ontent Topic Labels Predicted =====");
                //writer.close();
            }
        
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("===== Problem found while testing ====="+fileName);
                System.exit(0);
            }
    }
    
    public static void loadModel_style(String fileName) 
    {
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object tmp = in.readObject();
            classifier = (FilteredClassifier) tmp;
            in.close();
            System.out.println("===== Loaded model: " + fileName + " =====");
        } 
        
        catch (Exception e) 
        {
            System.out.println(e);
            // Given the cast, a ClassNotFoundException must be caught along with the IOException
            System.out.println("Problem found when reading: " + fileName);
            System.exit(0);
    }
    }
    
    public static void test_style(String fileName)
    {
        try
        {   
            //BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));
            
            test = new Instances(new BufferedReader(new FileReader(fileName)));

                // set class attribute
                test.setClassIndex(test.numAttributes() - 1);

                // create copy
                labeled = new Instances(test);

                for (int i = 0; i < test.numInstances(); i++)
                {   
                    double pred = classifier.classifyInstance(test.instance(i));
                    labeled.instance(i).setClassValue(pred);

            //        writer.write("\t");
            //        writer.write("\t");
            //        writer.write("\t");
            //        writer.write("\t");
            //        writer.write("\t");
            //        writer.write(String.valueOf(labeled.instance(i)));
                    
            //        writer.newLine();
            //        writer.flush();
                    
                    //System.out.print("ID: " + test.instance(i).value(0));
                    //System.out.print(", Tweet: " + test.instance(i).stringValue(1));
                    //System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
                    //System.out.println(", predicted: " + labeled.instance(i));
                    
                    String temp=out.get(i);
                    String label= String.valueOf(labeled.instance(i).stringValue(2));
                    String newtemp=temp+"\t"+label;
                    out.set(i,newtemp);
                }
				System.out.println("===== Content Style Labels Predicted =====");
                //writer.close();
            }
        
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("===== Problem found while testing ====="+fileName);
                System.exit(0);
            }
    }
    
    public static void savelabel(String fileName)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(labeled.toString());
            writer.newLine();
            writer.flush();
            writer.close();
            System.out.println("===== Saved labels: " + fileName + " =====");
        } 
        
    catch (IOException e) 
        {
            System.out.println(e);
            System.out.println("Problem found when writing: " + fileName);
            System.exit(0);
    }
    }
    
    public static void combinelabel(String outfileName)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outfileName));
            
            int i=0;
            while(i<count)
            {
                writer.write(out.get(i));
                writer.write("\n");
                
                i++;
            }       
        }
        
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Problem found when writing: " + outfileName);
            System.exit(0);
        }
    }
                
}
