@ECHO OFF 
javac step01\preprocess.java
javac -cp weka.jar step01\smo.java 
javac step01\training.java 
java -classpath .;weka.jar step01.training