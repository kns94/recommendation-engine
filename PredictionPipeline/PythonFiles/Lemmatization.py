# Aim: To lemmatize twitter text, which is to bring different keywords to its root form. 
# Method Used: Lancaster Lemmatization in the default nltk toolkit
# 
# Input-FORMAT: 
# 		Tweet Id [Tab] Tweetwords after removing stopwords [Tab] Is_Relevant [Tab] Content Theme [Tab] Content Topic [Tab] Content Style 
#
# OUTPUT-FORMAT: 
# 		Tweet Id [Tab] Tweetwords after bringing every keyword to its root form [Tab] Is_Relevant [Tab] Content Theme [Tab] Content Topic [Tab] Content Style 
#
# Usage: Lemmatization.py

import re
import sys
from nltk import stem

#Data File (Enter Data File)
file1 = "TempFiles/temp.csv"

#Output File to store Lemmatized version
#outputfilename = input("\nEnter the name of output .csv file: ") 
outputfilename = "TempFiles/temp1.csv"
outputfile = open(outputfilename,'w')

#print ("\n\tInput File: "+file1)
#print ("\n\tOutput .csv File: "+outputfilename)

# I will process each tweet and lemmatize the keywords

for line in open(file1):

	#Splitting the input into two parts! 
	tokens = line.strip().split("\t")
	
	#Tweet Id	
	tweetId = tokens[0]
    
	#The Complete tweet
	tweetWordsStr = str(tokens[1]) 
        
	outputfile.write(tweetId+"\t")
	
	classlabel=str(tokens[2])
	
	tweet = tweetWordsStr.split(" ")
	
	#Stemming and Lemmatizing bigram tokens
	lancaster = stem.lancaster.LancasterStemmer()

	temp=''

	for items in tweet:
		      
		temp = temp + " " + lancaster.stem(str(items))	 
		#print(temp)
	
	temp1 = temp[1:]
	outputfile.write(temp1+"\t")
	outputfile.write(classlabel)
	
	#if(len(tokens)>=4):
	
	#	classlabel_theme=str(tokens[3])
#		classlabel_topic=str(tokens[4])
	#	classlabel_style=str(tokens[5])
		
	#	outputfile.write(classlabel_theme+"\t")
	#	outputfile.write(classlabel_topic+"\t")
	#	outputfile.write(classlabel_style)
		
	outputfile.write("\n")
               
outputfile.close()

#print ('\n------ Lemmatization Done !! ------\n')
