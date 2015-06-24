# Here I will convert csv file to arff
# Kushal Shah
# Input type: TweetID[tab] Tweet
# Usage: arff_convert.py

import re
import sys

#Data File (Enter Data File)
file1 = input("\nEnter the data file: ")

#Output File to store arff
outputfile1name = input("\nEnter the name of output .arff file: ") 
outputfile1name = outputfile1name + ".arff"
outputfile1 = open(outputfile1name,'w')


print ("\n\tInput File: "+file1)
print ("\n\tOutput .arff File: "+outputfile1name)


#Entering relation in arff file
outputfile1.write("@relation " + file1 + "\n") 

#Entering Id attribute in arff file
outputfile1.write("@attribute ID Numeric" + "\n")
#Adding attributes in arff file
outputfile1.write("@attribute tweet String \n")
outputfile1.write("@attribute class {yes,no,CANNOT_DECIDE} \n")
outputfile1.write("\n")

#Writing @data line in .arff file!
outputfile1.write("@data\n")

#In the for loop below, I will append the frequency of keywords in the output files! 

for line in open(file1):
	
	#Splitting the input into two parts! 
	tokens = line.strip().split("\t") 
	
	#Tweet ID
	tweetId = tokens[0] 

	#Writing tweet Id in arff file
	outputfile1.write("\"" + tweetId + "\"" ) 
	outputfile1.write(",") 
	
	#The complete Tweet!
	tweet = str(tokens[1]) 
		
	#Adding Tweer in File
	outputfile1.write("\"" + tweet + "\"" ) 
	outputfile1.write(",") 
	
	classlabel=str(tokens[2])
	outputfile1.write("\"" + classlabel + "\"" ) 
	outputfile1.write("\n")
	
outputfile1.close()

print("\n------------------------- Bigram Features Extracted! -----------------------	---\n")
