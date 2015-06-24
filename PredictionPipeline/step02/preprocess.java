/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package step02;

/*
 * @author Tortuga
 */
public class preprocess
{
    public static void remove_stopwords(String fileName)
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/clean.py "+fileName);
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
            System.out.println("===Cannot Preprocess Data; File: clean.py===");
            System.exit(0);
        }
    }
    
    public static void lemmatize()
    {
        //System.out.println("Inside Lemmatize");
        try
        {    
            //System.out.println("Inside Lemmatize.try");
            Runtime.getRuntime().exec("python PythonFiles/Lemmatization.py");
            System.out.println("=== Lemmatization Removed ===");

           
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Preprocess Data; File: Lemmatization.py===");
            System.exit(0);
        }
    }
    
    public static void relevant_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_relevant.py");
            System.out.println("=== Converted to Arff for Relevancy Prediction ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_relevant.py===");
            System.exit(0);
        }
    }
    
    public static void theme_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_theme.py");
            System.out.println("=== Converted to Arff for Content_Theme Prediction ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_theme.py===");
            System.exit(0);
        }
    }
    
    public static void topic_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_topic.py");
            System.out.println("=== Converted to Arff for Content_Topic Prediction ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_topic.py===");
            System.exit(0);
        }
    }
    
    public static void style_convert()
    {
        try
        {    
            Runtime.getRuntime().exec("python PythonFiles/format_convert_style.py");
            System.out.println("=== Converted to Arff for Content_Style Prediction ===");
        }        
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("===Cannot Convert to .ARFF; File: format_convert_style.py===");
            System.exit(0);
        }
    }
}
