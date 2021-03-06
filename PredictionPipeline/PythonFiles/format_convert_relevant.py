#!usr/bin/python
#
# Aim: To convert .csv or .tsv file to .arff format
#
# INPUT-FORMAT:
# 		Tweet Id [Tab] Tweetwords [Tab] Is_Relevant [Tab] Content Theme [Tab] Content Topic [Tab] Content Style 
#
# OUTPUT-FORMAT (.arff file): 
# 		Tweet Id,Tweetwords,Is_Relevant  
#
#  USAGE: format_convert_relevant.py 
# 
# 
#

import re
import sys

#Data File (Enter Data File)
#file1 = input("\nEnter the data file: ")

file1="TempFiles/temp1.csv"

#Output File to store cleaned version
#wekafilename = input("\nEnter the name of output .arff file: ") 
wekafilename="InputFiles/relevant"
wekafile  = wekafilename + ".arff"
wekafile = open(wekafile,'w')

#print ("\n\tInput File: "+file1)
#print ("\n\tOutput .arff File: "+wekafilename)

wekafile.write("@relation " + file1 + "\n") 

#Entering Id attribute in arff file
wekafile.write("@attribute ID numeric" + "\n")
wekafile.write("@attribute Tweet string" + "\n")
wekafile.write("@attribute classlabel {yes, no} \n")
wekafile.write("\n")
wekafile.write("@data")
wekafile.write("\n")

# now read each tweet-row and process
for line in open(file1):
	#skip empty lines

	#line = "265467549616046000 [TAB] \"Justin will work with ' Red Cross' ; which would assist in the collection of donations for the victims of hurricane \' sandy  \""
	#print line
	tokens = line.strip().split("\t")
	#print(len(tokens))
	
	#print("",	len(tokens))
	#print (line.encode("utf-8"))
	#continue

	tweetId = tokens[0]
	#print("\n",tweetId)
	tweetWordsStr = str(tokens[1]) #tokenized tweet words
	#print("\t",tweetWordsStr)

	#if(len(tokens)>=3):
	#	classlabel_relevant=str(tokens[2])
	#else:
	#	classlabel_relevant='no'
	
	classlabel=str(tokens[2])
	#classlabel_relevant=str(tokens[2])
	#classlabel_theme=str(tokens[3])
	#classlabel_topic=str(tokens[4])
	#classlabel_style=str(tokens[5])
		
	wekafile.write("\"" + str(tweetId) + "\"" ) 
	wekafile.write(",")

	wekafile.write("\"" + str(tweetWordsStr) + "\"" ) 
	wekafile.write(",")
	
	wekafile.write(str(classlabel)) 
	
	wekafile.write("\n")
	
#done-for-all-input-files
wekafile.close()

print ('\n------ Converted ------\n')
