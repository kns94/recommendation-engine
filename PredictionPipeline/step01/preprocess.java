/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package step01;

/*
 * @author Tortuga
 */
public class preprocess
{
    public static void remove_stopwords(String fileName)
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/clean_labeled.py "+fileName);
            System.out.println("=== StopWords Removed ===");

            /*ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
            {
                engine.eval("python clean.py "+fileName);
                Runtime.getRuntime().exec("python clean.py "+fileName);
            }*/
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Preprocess Data; File: clean_labeled.py===");
            System.exit(0);
        }
    }
    
    public static void lemmatize()
    {
        //System.out.println("Inside Lemmatize");
        try
        {    
            //System.out.println("Inside Lemmatize.try");
            Runtime.getRuntime().exec("python PythonFiles/Lemmatization_labeled.py");
            System.out.println("=== Data Lemmatized ===");

           
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Preprocess Data; File: Lemmatization_labeled.py===");
            System.exit(0);
        }
    }
    
    public static void relevant_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_relevant_labeled.py");
            System.out.println("=== Converted Relevant Labels to Arff ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_relevant_labeled.py===");
            System.exit(0);
        }
    }
    
    public static void theme_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_theme_labeled.py");
            System.out.println("=== Converted Theme Labels to Arff ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_theme_labeled.py===");
            System.exit(0);
        }
    }
    
    public static void topic_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_topic_labeled.py");
            System.out.println("=== Converted Topic Labels to Arff ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_topic_labeled.py===");
            System.exit(0);
        }
    }
    
    public static void style_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_style_labeled.py");
            System.out.println("=== Converted Style Labels to Arff ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_style_labeled.py===");
            System.exit(0);
        }
    }
}
