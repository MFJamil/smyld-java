echo Releasing for version $1
git clone https://github.com/MFJamil/smyld-java.git Release_$1
cd Release_$1
command="find -name \"pom.xml\"|xargs sed -i 's/1.0.2-SNAPSHOT/$1/g'"
echo Updating the version with command ${command} 
eval ${command}
cd ..
echo Adding Maven repository package information
for i in `find -name "pom.xml"`; do sed -i "/MavenRepoInfo/r  maven_package_info.txt" $i; done;  
echo Deploying the artifacts
cd Release_$1
mvn clean deploy

