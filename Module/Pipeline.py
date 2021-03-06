#!usr/bin/python
#This code will use Alchemy API to extract entity and entity-type from a file containing tweets

#Input Format: Tweet ID [Tab] Tweet
#Output Format: Tweet ID [Tab] Tweet [Tab] Entity1 [Tab] EntityType1 [Tab] Entity2 [Tab] EntityType2 ... EntityN [Tab] EntityTypeN

#Usage: python Pipeline.py Input_File Output_File

import os
import sys

#Input_File
input_file_name=sys.argv[1]

#Output_File
output_file_name=sys.argv[2]

#Calling another file to preprocess the data
os.system("python clean.py "+input_file_name)

#Extracting Features
os.system("python Knowledge_Extraction.py clean.csv "+output_file_name)
