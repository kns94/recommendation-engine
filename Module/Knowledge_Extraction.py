#!usr/bin/python
#This code will extract entities from a given file with Tweets
#
#Input Format: Tweet ID [Tab] Tweet - .csv format
#Output Format: 
#
#Usage: python Knowledge_Extraction.py Input_File Output_File

from alchemyapi import AlchemyAPI
import json
import sys
import re
import os

#Input File that you wish to process
input_file_name=sys.argv[1]

#print("\nInput File: "+input_file_name)

#Output File
output_file_name=sys.argv[2]

print("Output File: "+output_file_name)

print("\n\tExtracting Features!")
print("\n")

count=0
api_name="alchemyapi"+str(count)

# Create the AlchemyAPI Object
api_name = AlchemyAPI()

outputfile=open(output_file_name,"w")

for line in open(input_file_name):
	
	#Breaking the whole line by tab
	tokens=line.strip().split("\t")
	
	tweetId=tokens[0]

	#Extracting only tweets
	tweetWordStr=tokens[1]
	#print("\nTweet: "+tweetWordStr)

	#Extracting entities from Alchemy
	response = api_name.entities('text', tweetWordStr, {'sentiment': 1})

	#print(response)
	#print("count1:"+str(count))
	#print("\n")
	
	outputfile.write(tweetId+"\t")
	outputfile.write(tweetWordStr)

	if(response['status'] == 'OK'):
    		#print('## Response Object ##')
    		#print(json.dumps(response, indent=4))

		#print("count2:"+str(count))

		#count=count+1
    		#print('')
    		#print('## Entities ##')

    		for entity in response['entities']:
        		#print('text: ', entity['text'].encode('utf-8'))
        		#print('type: ', entity['type'])
        		#print('relevance: ', entity['relevance'])
        		#print('sentiment: ', entity['sentiment']['type'])

			text=entity['text'].encode('utf-8')
			text=text.replace("'",'')
			#print(text)
			outputfile.write("\t"+text+"\t")

			etype=entity['type']
			etype=etype.replace("'",'')
			#print(etype)
			outputfile.write(etype)

        		#if 'score' in entity['sentiment']:
            		#	print('sentiment score: ' + entity['sentiment']['score'])
        		#	print('')
			#else:
    			#	print('Error in entity extraction call: ', response['statusInfo'])
	
	else:
		print("Tweet ID:"+tweetId)
		print("Tweet:"+tweetWordStr)
		print(str(response)+"\n")
		
		if "daily-transaction-limit-exceeded" in str(response):
			key=raw_input("\n\t Looks like the daily limit is exceeded, please enter new key: ")
			os.system("python alchemyapi.pyc "+key)
			
			print("\n")

			count=count+1
			
			api_name="alchemyapi"+str(count)

			# Create a new AlchemyAPI Object
			api_name = AlchemyAPI()

			response = api_name.entities('text', tweetWordStr, {'sentiment': 1})
			
			if(response['status'] == 'OK'):
			 		
				for entity in response['entities']:
				
					text=entity['text'].encode('utf-8')
					text=text.replace("'",'')
					#print(text)
					outputfile.write("\t"+text+"\t")

					etype=entity['type']
					etype=etype.replace("'",'')
					#print(etype)
					outputfile.write(etype)
		
	outputfile.write("\n")

os.system("rm "+input_file_name)
outputfile.close()
