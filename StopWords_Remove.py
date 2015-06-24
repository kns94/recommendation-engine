#!usr/bin/python
#
# clean up words in each tweet row of the datasets for WEKA processing
# INPUT-FORMAT:
#  id [TAB] tweet
# e.g, #line = "265467549616046000 [TAB] \"Justin will work with ' Red Cross' ; which would assist in the collection of donations for the victims of hurricane \' sandy  \""
#
#  USAGE: StopWords_Remove.py 
#
# 
#

import re
import sys

# ==================== FUNCTIONS ==================================
def stopwordRemover(str1):
    tokens = str(str1).split()
    clnTokens = [s.lower() for s in tokens if s.lower() not in stopwordsList]
    clnstr = " ".join(clnTokens)
    return clnstr

def singleCharRemover(str2):
    tokens = str(str2).split()
    cp_tokens = tokens
    for t in tokens:
        if len(t)==1:
            cp_tokens.remove(t)
    clnstr = " ".join(cp_tokens)
    return clnstr
# =====================END OF FUNCTIONS ============================

#Data File (Enter Data File)
file1 = input("\nEnter the data file: ")

#Stopwords file
stopwordsfile = input("\nEnter the stopwords file: ")
stopwordsList = [line.strip() for line in open(stopwordsfile, 'r')]

#Output File to store cleaned version
filenameTextCleaned = input("\nEnter the name of output .csv file: ") 
filenameTextCleaned = filenameTextCleaned + ".csv"
fileTextCleaned = open(filenameTextCleaned,'w')

print ("\n\tInput File: "+file1)
print ("\n\tStopwords File: "+stopwordsfile)
print ("\n\tOutput .csv File: "+filenameTextCleaned)

# now read each tweet-row and process
for line in open(file1):
    #skip empty lines
	
    #line = "265467549616046000 [TAB] \"Justin will work with ' Red Cross' ; which would assist in the collection of donations for the victims of hurricane \' sandy  \""
    #print line
    tokens = line.strip().split("\t")
	
    #print("",	len(tokens))
	#print (line.encode("utf-8"))
    #continue

    tweetId = tokens[0]
    #print("\n",tweetId)
    tweetWordsStr = str(tokens[1]) #tokenized tweet words
    #print("\t",tweetWordsStr)
    classlabel=str(tokens[2])    
        
    fileTextCleaned.write(tweetId+"\t")
   
    #fileTextCleaned.write(",")
    #fileTextCleaned.write(",")
    #clean as you wish!
    cleanedStr = " " + tweetWordsStr
    # case-1
    # no-filtering!!!!
    
    # case-2
    cleanedStr = cleanedStr.replace("_URL_", " ")
    # case-1,2
    cleanedStr = cleanedStr.replace(" RT ", "")
    # case-3
    cleanedStr = cleanedStr.replace("_RT_", "")
    #cleanedStr = cleanedStr.replace(",", ";")
    cleanedStr = cleanedStr.replace("\"", "")
    # urls
    cleanedStr = re.sub(r'(http://\S*)', ' _URL_ ', cleanedStr)
    cleanedStr = re.sub(r'(https://\S*)', ' _URL_ ', cleanedStr)
    #cleanedStr = cleanedStr.replace("_URL_"," ")
    #numbers
    cleanedStr = re.sub(r'[\d-]+', '_NUM_', cleanedStr)
    cleanedStr = cleanedStr.replace("_NUM_"," ")
    #mentions
    # case-2
    cleanedStr = re.sub(r'(@\w+)', ' ', cleanedStr)
    # case-3
    cleanedStr = re.sub(r'(@\w+)', '_MENTION_', cleanedStr)
            
    cleanedStr = re.sub(r'[[^\w\s_#@%.,;-]]+', '', cleanedStr)
    cleanedStr = re.sub(r'[^\w\s_-]+', ' ', cleanedStr)
            
    #--CALL FOR STOPWORDS --
    cleanedStr = stopwordRemover(cleanedStr)
    #print cleanedStr
	
	#--CALL FOR SINGLE CHAR REMOVAL --
    cleanedStr = singleCharRemover(cleanedStr)
    #print cleanedStr

    cleanedStr = re.sub(r'(\s+)', ' ', cleanedStr) #for multiple spaces
    cleanedStr = cleanedStr.strip()
    #print cleanedStr
    fileTextCleaned.write(cleanedStr+"\t")
    fileTextCleaned.write(classlabel)
        
    #c = c + 1
    #if c > 50:
    #    break
    #exit(0)
    fileTextCleaned.write("\n")
              
#done-for-all-input-files
fileTextCleaned.close()

print ('\n------ text cleaning done ------\n')
