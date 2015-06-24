# Here I will try to find frequency of unigram tokens from a file using NTLK toolkit! 
# Kushal Shah
# Input type: TweetID[tab] Tweet
# Usage: Unigram_Frequency.py

import re
import sys
import nltk
from nltk.tokenize.punkt import PunktWordTokenizer
#from nltk.probability import FreqDist
from collections import Counter

#Data File (Enter Data File)
file1 = input("\nEnter the data file: ")

#Output File to store Unigram Frequency
outputfilename = input("\nEnter the name of output .csv file: ") 
outputfilename = outputfilename + ".csv"
outputfile = open(outputfilename,'w')

outputfile1name = input("\nEnter the name of output .arff file: ") 
outputfile1name = outputfile1name + ".arff"
outputfile1 = open(outputfile1name,'w')


print ("\n\tInput File: "+file1)
print ("\n\tOutput .csv File: "+outputfilename)
print ("\n\tOutput .arff File: "+outputfile1name)

#This unigrams list will contain the list of all unique unigrams from our data!
unigrams = []

#Tab since, I would want to input unigram tokens in the .tsv file from column number 2 (Column1 contains ID)
outputfile.write("\t")

#Entering relation in arff file
outputfile1.write("@relation " + file1 + "\n") 

#Entering Id attribute in arff file
outputfile1.write("@attribute ID Numeric" + "\n")


#In the for loop below, I will enter unigram tokens as attributes in .tsv file and as attributes in .arff file format!

for line in open(file1):
	
	#Splitting the input into two parts! 
	tokens = line.strip().split("\t") 
	
	#The complete Tweet!
	tweet = str(tokens[1]) 

	#Extract Unigrams
	tweetwords = PunktWordTokenizer().tokenize(tweet) 
	
	
	
	for item in tweetwords:

		if(item not in unigrams):

			#Append Tweet Words
			unigrams.append(item)

			#Fill Unigrams as attributes in tsv file
			outputfile.write(str(item)) 
			outputfile.write("\t")

			#Adding attributes in arff file
			outputfile1.write("@attribute " + str(item) + " Numeric \n") 

outputfile1.write("@attribute classlabel {yes,no,CANNOT_DECIDE} \n")
outputfile.write("\n")
outputfile1.write("\n")

#Writing @data line in .arff file!
outputfile1.write("@data\n")

#In the for loop below, I will append the frequency of keywords in the output files! 

for line in open(file1):
	
	#Splitting the input into two parts
	tokens = line.strip().split("\t") 

	#Tweet ID
	tweetId = tokens[0] 

	#Classlabel
	classlabel=str(tokens[2])
	
	#Write Tweet Id inside the output file!
	outputfile.write(tweetId) 
	outputfile.write("\t")

	#Writing tweet Id in arff file
	outputfile1.write("\"" + tweetId + "\"" ) 
	outputfile1.write(",") 
	
	#The complete Tweet!
	tweet = str(tokens[1]) 
	tweet = tweet.lower()

	for item in unigrams:

		#Counting Frequency of unigram tokens
		regex = re.compile(r"\b(%s)"%item)
		count = len(regex.findall(tweet))
				
		#Adding count in tsv file
		outputfile.write(str(count)) 
		outputfile.write("\t")
	
		#Adding Count in arff File
		outputfile1.write("\"" + str(count) + "\"" ) 
		outputfile1.write(",")
	
	outputfile.write(classlabel)
	outputfile.write("\n")
	
	outputfile1.write("\"" + classlabel + "\"" )
	outputfile1.write("\n")
	
outputfile.close()
outputfile1.close()

print("\n------------------------ Unigram Features Extracted! --------------------------\n")
