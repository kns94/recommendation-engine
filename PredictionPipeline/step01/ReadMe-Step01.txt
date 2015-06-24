Step01:

This code will prepare classification (SVM) model for the data entered

Input File: Labeled Data File
Expected Format: ID [Tab] Tweet [Tab] Classlabel_relevant [Tab] Classlabel_Theme [Tab] Classlabel_Topic [Tab] Classlabel_Style

Steps:
Input File --> Stopword removal --> Lemmatization --> Arff Convert --> Filetered Classifier Model Train

Output Files:
InputFiles/relevant.model
InputFiles/theme.model
InputFiles/topic.model
InputFiles/style.model