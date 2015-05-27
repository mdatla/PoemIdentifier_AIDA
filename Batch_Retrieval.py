import urllib
import json
import os
import sys
from pgmagick import Image

# This script will pull all images for all newspapers in a certain year range from Chronicaling America.
# Dates are taken in via the command line but are optional. You can either give no dates (in which case the script will
# attempt to pull EVERY image from the Chronicaling America Database) or you can give two years which will cause
# the script to pull only images from between those two years. If you want only images from a single year just input the year twice.
# The Chronicaling America Database currently starts at 1830.
# The script will also make all neccessary directories using the location of the script as the start directory.
# Usage: python Batch_Retrieval.py "Optional: Begin Year" "Optional: End Year"

#Gets the 50 full batches
def getFullBatches():
    mainDirectory = os.getcwd()
    if not os.path.exists(mainDirectory+'/batches_json'):
        os.makedirs('batches_json')
    os.chdir('batches_json')
    batchName = "all_batches1.json"
    flag = 1
    urllib.urlretrieve('http://chroniclingamerica.loc.gov/batches.json', batchName)
    count = 2
    dots = 0
    #continue getting batch files until no more exist
    while flag is 1:
        file = open(batchName, 'r')
        data = json.load(file)
        try:
            nextURL = data['next']
            batchName = "all_batches"+str(count)+".json"
            urllib.urlretrieve(nextURL, batchName)
            if dots is 0:
                sys.stdout.write("\rRetrieveing Full Batches   ")
                sys.stdout.flush()
                dots += 1
            elif dots is 1:
                sys.stdout.write("\rRetrieveing Full Batches.  ")
                sys.stdout.flush()
                dots += 1
            elif dots is 2:
                sys.stdout.write("\rRetrieveing Full Batches.. ")
                sys.stdout.flush()
                dots += 1
            elif dots is 3:
                sys.stdout.write("\rRetrieveing Full Batches...")
                sys.stdout.flush()
                dots = 0
            count += 1
        except:
            flag = 0
        file.close()
        #break
    sys.stdout.write("\rGot all Full Batches       ")
    print("")
    os.chdir('..')
    return

#Retrieves the smaller batches that points to a subset of full newspapers
def getIndividualBatches():
    mainDirectory = os.getcwd()
    if not os.path.exists(mainDirectory+'/issueBatches'):
        os.makedirs('issueBatches')
    os.chdir('batches_json')
    fullBatchCount = len(os.listdir(os.getcwd()))
    currentBatch = 0
    for i in os.listdir(os.getcwd()):
        #Only work on .json files
        if i.endswith(".json"):
            currentBatch += 1
            file = open(i, 'r')
            data = json.load(file)
            size = len(data['batches'])
            count = 0
            for x in range(0, size):
                count += 1
                issueBatchURL = data['batches'][x]['url']
                batchName = issueBatchURL.replace('http://chroniclingamerica.loc.gov/batches/batch_', '')
                urllib.urlretrieve(issueBatchURL, mainDirectory+"/issueBatches/"+batchName)
                sys.stdout.write("\rIndividual Batches: Full Batch "+str(currentBatch)+"/"+str(fullBatchCount)+" Newspaper: "+str(count)+"/"+str(size))
                sys.stdout.flush()
            file.close()
            #break
    print("")
    os.chdir('..')
    return

#Retrieves each issue of a newspaper for its entire existance
#Also allows for date ranges to cut down on how many issues we are retrieving
#No years for all newspapers, two years for a date range, one year entered twice for a single year
def getIssues(beginYear=0, endYear=2015):
    mainDirectory = os.getcwd()
    if not os.path.exists(mainDirectory+'/individualIssueBatches'):
        os.makedirs('individualIssueBatches')
    os.chdir('issueBatches')
    newspaperCount = len(os.listdir(os.getcwd()))
    currentPaper = 0
    for i in os.listdir(os.getcwd()):
        #Only work on .json files
        if i.endswith(".json"):
            currentPaper += 1
            file = open(i, 'r')
            data = json.load(file)
            size = len(data['issues'])
            name = i[:-5]
            total = 0
            for y in range(0, size):
                yearIssued = data['issues'][y]['date_issued'][:4]
                year = int(yearIssued)
                if year >= int(beginYear) and year <= int(endYear):
                    total += 1
            count = 0
            for x in range(0, size):
                issueURL = data['issues'][x]['url']
                yearIssued = data['issues'][x]['date_issued'][:4]
                year = int(yearIssued)
                #makes the check for year issued. If not in the range then it won't retrieve the file.
                if year >= int(beginYear) and year <= int(endYear):
                    if not os.path.exists(mainDirectory+'/individualIssueBatches/'+name):
                        os.makedirs(mainDirectory+'/individualIssueBatches/'+name)
                    count += 1
                    issueName = issueURL.replace('http://chroniclingamerica.loc.gov/lccn/', '').replace('/', '_')
                    urllib.urlretrieve(issueURL, mainDirectory+"/individualIssueBatches/"+name+'/'+issueName)
                    sys.stdout.write("\rIssues: Newspaper "+str(currentPaper)+"/"+str(newspaperCount)+": got issue "+str(count)+"/"+str(total)+" in "+name+"      ")
                    sys.stdout.flush()
                #break
            file.close()
            #break
    os.chdir('..')
    print("")
    return

#gets the final .json files that ultimately point to the JP2 images
def getPages():
    os.chdir('individualIssueBatches')
    newspaperCount = len(os.listdir(os.getcwd()))
    currentPaper = 0
    problemFiles = []
    for i in os.listdir(os.getcwd()):
        #Exclude hidden directories
        if '.' not in i:
            currentPaper += 1
            os.chdir(i)
            list = os.listdir(os.getcwd())
            issueCount = 0
            currentIssue = 0
            for k in list:
                if k.endswith('.json'):
                    issueCount += 1
            for j in list:
                #Only work on .json files
                if j.endswith(".json"):
                    currentIssue += 1
                    try:
                        file = open(j, 'r')
                        data = json.load(file)
                        size = len(data['pages'])
                        name = data['url'].replace('http://chroniclingamerica.loc.gov/lccn/', '').replace('/', '_').replace('.json', '')
                        if not os.path.exists(os.getcwd()+'/'+name):
                            os.makedirs(name)
                        os.chdir(name)
                        count = 0
                        for x in range(0, size):
                            count += 1 
                            pageURL = data['pages'][x]['url']
                            page = pageURL.replace('http://chroniclingamerica.loc.gov/lccn/', '').replace('/', '_')
                            urllib.urlretrieve(pageURL, page)
                            sys.stdout.write("\rPages: Newspaper "+str(currentPaper)+"/"+str(newspaperCount)+": Issue "+str(currentIssue)+"/"+str(issueCount)+": Page "+str(count)+"/"+str(size)+" in "+name+"      ")
                            sys.stdout.flush()
                        file.close()
                        os.chdir('..')
                        #break
                    except:
                        problemFiles.append(j)
            os.chdir('..')
    os.chdir('..')
    if len(problemFiles) > 0:
        print("These are files that had problems. Please Check them manually.")
        print(problemFiles)
    else:
        print("")
    return

#What it says on the tin. Retrieves all of the JP2 images based on the previous acquired .json files.
#Also converts the JP2s into jpg, then deletes the JP2 images to conserve space.
def getImages():
    if not os.path.exists(os.getcwd()+'/data/FullPages'):
        os.makedirs('data/FullPages')
    fullPages = os.getcwd()+'/data/FullPages'
    os.chdir('individualIssueBatches')
    newspaperCount = len(os.listdir(os.getcwd()))
    currentPaper = 0
    problemFiles = []
    for i in os.listdir(os.getcwd()):
        #Exclude hidden directories
        if '.' not in i:
            currentPaper += 1
            if not os.path.exists(fullPages+'/'+i):
                os.makedirs(fullPages+'/'+i)
            os.chdir(i)
            currentlist = os.listdir(os.getcwd())
            issueCount = 0
            currentIssue = 0
            for a in currentlist:
                if a.endswith('.json'):
                    issueCount += 1
            for j in currentlist:
                #Need just the visible directories
                if not (j.endswith('.json') or '.' in j):
                    currentIssue += 1
                    if not os.path.exists(fullPages+'/'+i+'/'+j):
                        os.makedirs(fullPages+'/'+i+'/'+j)
                    os.chdir(j)
                    pageCount = len(os.listdir(os.getcwd()))
                    currentPage = 0
                    for k in os.listdir(os.getcwd()):
                        #now we can work on .json files
                        if k.endswith('.json'):
                            currentPage += 1
                            try:
                                file = open(k, 'r')
                                data = json.load(file)
                                jp2URL = data['jp2']
                                name = jp2URL.replace('http://chroniclingamerica.loc.gov/lccn/', '').replace('/', '_')
                                urllib.urlretrieve(jp2URL, fullPages+'/'+i+'/'+j+'/'+name)
                                returnDir = os.getcwd()
                                os.chdir(fullPages+'/'+i+'/'+j)
                                im = Image(str(name))
                                newname = name[:-3]+'jpg'
                                im.write(str(newname))
                                #remove the jp2 file to conserve space
                                os.remove(name)
                                os.chdir(returnDir)
                                s = name[:-4]
                                sys.stdout.write("\rImages: Newspaper "+str(currentPaper)+"/"+str(newspaperCount)+": Issue "+str(currentIssue)+"/"+str(issueCount)+": Image "+str(currentPage)+"/"+str(pageCount)+" in "+s+"    ")
                                sys.stdout.flush()
                                file.close()
                            except:
                                problemFiles.append(k)
                    os.chdir('..')
                    #break
            os.chdir('..')
    os.chdir('..')
    if len(problemFiles) > 0:
        print("These are files that had problems. Please Check them manually.")
        print(problemFiles)
    else:
        print("")
    return


if len(sys.argv) is 3:
    if int(sys.argv[2]) < int(sys.argv[1]):
        print("HEY! The start year can't be greater than the end year!!!")
        sys.exit(1)
elif len(sys.argv) is 2:
    print("Proper Usage: Batch_Retrieval.py (Optional: Begin Date) (Optional: End Date)")
    print("You must have either no dates or both dates. NOT JUST ONE")
    sys.exit(2)
elif len(sys.argv) > 3:
    print("Proper Usage: Batch_Retrieval.py (Optional: Begin Date) (Optional: End Date)")
    print("What are you doing putting in so many arguments?")
    sys.exit(3)

getFullBatches()
getIndividualBatches()
if len(sys.argv) is 3:
    getIssues(sys.argv[1], sys.argv[2])
else:
    getIssues()
getPages()
getImages()
