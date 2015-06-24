/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package step01;

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
import weka.classifiers.functions.SMO;
import weka.classifiers.Evaluation;
//import weka.filters.unsupervised.attribute.Remove;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.*;

/**
 *
 * @author Tortuga
 */
public class smo 
{
    /**
     * Instances to be indexed.
     */
    static Instances inputInstances;
	/**
	 * Instances after indexing.
	 */
    static Instances outputInstances;
    
    static Instances labeled,test;
    
    static StringToWordVector filter;
    
    static FilteredClassifier classifier;
    
    public static void loadARFF(String fileName) 
    {
	try 
        {
            
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            ArffReader arff = new ArffReader(reader);
            inputInstances = arff.getData();
            inputInstances.setClassIndex(inputInstances.numAttributes() - 1);
            
            //System.out.println(inputInstances);
            //System.out.println(inputInstances.classIndex());
            System.out.println("===== Loaded dataset: " + fileName + " =====");
            reader.close();
	}
	
        catch (IOException e) 
        {
            System.out.println(e);
            System.out.println("Problem found when reading: Relevant.arff");
            System.exit(0);
	}
    }
        
    public static void evaluate() 
    {
	// outputInstances = inputInstances;
	try 
        {
            // Set the tokenizer
            NGramTokenizer tokenizer = new NGramTokenizer();
            tokenizer.setNGramMinSize(1);
            tokenizer.setNGramMaxSize(2);
            //tokenizer.setDelimiters("\" \"");
			
            // Set the stemmer
            LovinsStemmer stemmer = new LovinsStemmer();
			
            // Set stopword file
            File stopwords = new File("stopWords.txt");
			
            // Set the filter
            StringToWordVector filter = new StringToWordVector();
            filter.setTokenizer(tokenizer);
            filter.setInputFormat(inputInstances);
            filter.setWordsToKeep(1000000);
            filter.setDoNotOperateOnPerClassBasis(false);
            filter.setLowerCaseTokens(true);
            filter.setAttributeIndices("first-last");
            filter.setMinTermFreq(1);
            filter.setStemmer(stemmer);
            filter.setOutputWordCounts(true);
            filter.setStopwords(stopwords);
            filter.setUseStoplist(true);
            
            classifier = new FilteredClassifier();
            classifier.setFilter(filter);
            classifier.setClassifier(new SMO());
            
            //System.out.println(filter);
            
            Evaluation eval = new Evaluation(inputInstances);
            eval.crossValidateModel(classifier, inputInstances, 10, new Random(1));
            //System.out.println(eval.toSummaryString());
            //System.out.println(eval.toClassDetailsString());
            System.out.println("===== Evaluating on filtered (training) dataset done =====");
            
            //System.out.println(classifier);
            
            //System.out.println("===== Classifiers set =====");
            
	}
        
	catch (Exception e)
	{
            System.out.println(e);
            System.out.println("Problem found when evaluating");
            System.exit(0);
	}
    }
    
    /*public static void evaluate()
    {
        try
        {        
            Evaluation eval = new Evaluation(inputInstances);
            eval.crossValidateModel(classifier, inputInstances, 10, new Random(1));
            
            System.out.println(classifier);            
            System.out.println("===== Evaluating on filtered (training) dataset done =====");
        }
        
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Problem found while evaluating");
            System.exit(0);
        }        
    }*/
    
    public static void learn()
    {
        try 
        {            
            classifier.buildClassifier(inputInstances);
            
            //System.out.println(classifier);
            System.out.println("===== Classifier built =====");
            
        }
	catch (Exception e)
        {
            System.out.println(e);
            System.out.println("===== Problem found while building classifier =====");
            System.exit(0);
	}
    }
    
    /*public static void test(String fileName)
    {
        try
        {    
            test = new Instances(new BufferedReader(new FileReader(fileName)));

                // set class attribute
                test.setClassIndex(test.numAttributes() - 1);

                // create copy
                labeled = new Instances(test);

                for (int i = 0; i < test.numInstances(); i++)
                {   
                    double pred = classifier.classifyInstance(test.instance(i));
                    labeled.instance(i).setClassValue(pred);

                    //System.out.print("ID: " + test.instance(i).value(0));
                    //System.out.print(", Tweet: " + test.instance(i).stringValue(1));
                    //System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
                    //System.out.println(", predicted: " + labeled.instance(i));
                }
            }
        
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("===== Problem found while testing =====");
                System.exit(0);
            }
    }*/
    
    /*public static void savelabel(String fileName)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(labeled.toString());
            writer.newLine();
            writer.flush();
            writer.close();
            System.out.println("===== Saved model: " + fileName + " =====");
        } 
        
	catch (IOException e) 
        {
            System.out.println("Problem found when writing: " + fileName);
            System.exit(0);
	}
    }*/
    
    public static void saveModel(String fileName)
    {
	try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(classifier);
            out.close();
 			System.out.println("===== Saved model: " + fileName + " =====");
        } 
	catch (IOException e)
        {
            System.out.println("Problem found when writing: " + fileName);
		}
    }
}
