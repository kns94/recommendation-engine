# Here I will try to find frequency of bigram tokens from a file using NTLK toolkit! 
# Kushal Shah
# Input type: TweetID[tab] Tweet
# Usage: Bigram_Frequency.py

import re
import sys
import nltk
from nltk.tokenize.punkt import PunktWordTokenizer
from nltk.probability import FreqDist
from collections import Counter
from nltk import bigrams
from nltk import stem

#Data File (Enter Data File)
file1 = input("\nEnter the data file: ")

#Output File to store Bigram Frequency
outputfilename = input("\nEnter the name of output .csv file: ") 
outputfilename = outputfilename + ".csv"
outputfile = open(outputfilename,'w')

outputfile1name = input("\nEnter the name of output .arff file: ") 
outputfile1name = outputfile1name + ".arff"
outputfile1 = open(outputfile1name,'w')


print ("\n\tInput File: "+file1)
print ("\n\tOutput .csv File: "+outputfilename)
print ("\n\tOutput .arff File: "+outputfile1name)

#This bigrams list will contain the list of all unique bigrams from our data!
bigrams = []
outputfile.write("\t")


#Entering relation in arff file
outputfile1.write("@relation " + file1 + "\n") 

#Entering Id attribute in arff file
outputfile1.write("@attribute ID Numeric" + "\n")
	
#In the for loop below, I will enter bigram tokens as attributes in .tsv file and as attributes in .arff file format!

for line in open(file1):
	
	#Splitting the input into two parts!
	tokens = line.strip().split("\t")  
	
	#The complete Tweet!
	tweet = str(tokens[1]) 
	
	tweetwords = nltk.bigrams(tweet.split())	
	
	for item in tweetwords:

		temp = str(item).strip().split("\n")	

		for i in temp:

			#Removing unnecessary items!			
			i = i.replace(" ","") 				
			i = i.replace(","," ") 
			i = i.replace("'","")
			i = i.replace("(","")
			i = i.replace(")","") 

			bg = i.split()			
			i=i.replace(" ","_")	
						

			if(i not in bigrams):
	
				
				#Append Bigrams
				bigrams.append(str(i)) 
			
				#Fill Bigrams as attributes in tsv file
				outputfile.write(str(i)) 
				outputfile.write("\t")
	
				#Adding attributes in arff file
				outputfile1.write("@attribute " + str(i) + " Numeric \n") 


outputfile1.write("@attribute classlabel {yes,no,CANNOT_DECIDE} \n")
outputfile.write("\n")
outputfile1.write("\n")

#Writing @data line in .arff file!
outputfile1.write("@data\n")

#In the for loop below, I will append the frequency of keywords in the output files! 

for line in open(file1):
	
	#Splitting the input into two parts! 
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
		
	for item in bigrams:
		
		#Removing unnecessary items
		item = item.replace(",","") 
		item = item.replace("'","")
		item = item.replace("  ","") 
		item = item.replace("(","")
		item = item.replace(")","") 
		item = item.replace("_"," ")
		
		bg = item.strip()	

		#Counting Frequency of bigrams
		#temp = tweet.count(str(item))
		regex = re.compile(r"\b(%s)"%item)
		count = len(regex.findall(tweet))
			
		#Adding Count in File
		outputfile.write(str(count)) 
		outputfile.write("\t")
		
		#Adding Count in File
		outputfile1.write("\"" + str(count) + "\"" ) 
		outputfile1.write(",")
		
	outputfile.write(classlabel)
	outputfile.write("\n")
		
	outputfile1.write("\"" + classlabel + "\"" )
	outputfile1.write("\n")
		
outputfile.close()
outputfile1.close()

print("\n------------------------- Bigram Features Extracted! -----------------------	---\n")
