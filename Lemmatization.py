# Here I will try to lemmatize the tokens using nltk toolkit! 
# Kushal Shah
# Input type: TweetID[tab] Tweet
# Usage: Unigram_Frequency.py

import re
import sys
from nltk import stem

#Data File (Enter Data File)
file1 = input("\nEnter the data file: ")

#Output File to store Lemmatized version
outputfilename = input("\nEnter the name of output .csv file: ") 
outputfilename = outputfilename + ".csv"
outputfile = open(outputfilename,'w')

print ("\n\tInput File: "+file1)
print ("\n\tOutput .csv File: "+outputfilename)

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
	
	outputfile.write("\n")
               
outputfile.close()

print ('\n------ Lemmatization Done !! ------\n')
