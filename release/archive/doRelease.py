#! /usr/bin/env python
import os,sys
if (len(sys.argv)==1):
    print ('doRelease gitLink')
    sys.exit(-1)
gitLink = sys.argv[1]



gitName = gitLink[gitLink.rfind('/')+1:]

releaseVersions = {'release.version','pe.version','ep.version'}
snapshotVers = {}
def startProcess():
    print ('Releasing artifacts in  ' + gitLink + ' , holding git name \'' + gitName + '\'')
    for line in open(gitName + '/pom.xml', 'r').readlines():
        line = line.rstrip()
        for ver in releaseVersions:
            if ('<' + ver + '>' in line):
                newVersion = processVersion(line[line.find('>')+1:line.rfind('<')])
                command = 'find -name "pom.xml"|xargs sed -i \'s/\\${' + ver + '}/' + newVersion + '/g\''
                print ('Running Command : ' + command)
                os.popen(command)

def processVersion(value):
    curVer = value[value.find('>')+1:value.rfind('<')]
    curFullVer = curVer[:curVer.find('-')]
    curBuild = curFullVer[curFullVer.rfind('.')+1:]
    print ('Full Version : ' + curFullVer + ', build ' + curBuild)
    newBuild = int(curBuild) + 1
    newVersion = curFullVer[:curFullVer.rfind('.')] + '.'  + str(newBuild)
    return newVersion

# Commenting out the cloning of the git
#result = os.popen('git clone ' + gitLink)
#lines = result.readlines()
#for curLine in lines:
#    print (curLine)
startProcess()
            

