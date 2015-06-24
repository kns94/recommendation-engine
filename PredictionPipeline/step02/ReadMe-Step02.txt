Step02:

This code will put in labels by evaluating the models prepared in step01

Input Files:
1) Unlabeled Data File
2) InputFiles/relevant.model
   InputFiles/theme.model
   InputFiles/topic.model
   InputFiles/style.model
   
Steps:
Input File --> Stopword removal --> Lemmatization --> Unlabeled Arff convert --> Filetered Classifier Model Evaluate --> Predicted Labels

Output Files:
OutputFiles/relevant.arff
OutputFiles/theme.arff
OutputFiles/topic.arff
OutputFiles/style.arff
OutputFiles/OutputLabels.csv
