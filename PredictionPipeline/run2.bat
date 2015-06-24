@ECHO OFF 
javac step02/preprocess.java
javac -cp weka.jar step02/model_evaluator.java
javac step02/Prediction_pipeline.java
java -classpath .;weka.jar step02.Prediction_pipeline 