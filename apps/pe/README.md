# SMYLD Portal Engine
Portal Engine is establishing a compelete segregation between GUI layer and other modules of the software, the target is to eleminate the boilerplate code and to alleviates dealing with GUI components. The framework was originally designed to deal with Swing Components back in 2004, was successfully used with many applications which saved considerable time.

### Swing! are you serious? in 2019!
Well you are right, many alternatives are available but do not forget some facts:

#### 1 - Multi-threading 
#### 2 - Performance
#### 3 - Intellij

**Intellij !** ... YES! one of the most powerful editors in the market is still written with Swing. However Jet Brains holds also a powerful development team. Still, it is written in Swing.

**What about Java FX!** Well, till now, the performance is still on the side of Swing.


**However this is not only about Swing!** 



## Portal Engine Concept
   The Portal Engine concept is to deal with the GUI layer as a separate module that will interact with the rest of the modules via defined interfaces. The GUI definition can take different formats: 
   * **XML file**   
   * **YAML file**
   * **JSON file**
   * **Data Base**

The Portal Engine will handle reading the file and generating the required components. The Components can belong to any technology/framework. We can define the components in two types:

#### 1 - Desktop:
   Desktop based widgets can be any of the following:
   * **JDK/Swing**
   * **JDK/SWT**
   * **Node.js/Electron**
   * **Node.js/NW.js**
  
  And many more..

#### 2 - Web.
   Web based frameworks are available and involve some complexity that can be eliminated by the Portal Engine as well, although that the component oriented approach might look like a limitation for the creativity of the developer when working with such an approach, but saving a lot of time might be needed in some projects, some of the well known frameworks can be:
   * **Angular**
   * **React**
   * **Vue**

   And many more ..

## Building Process
   Building tools are rich and the Portal Engine can be extended to use most of them. Currently the available building ways are:
   * **Stand Alone Application**
   * **Maven Plugin**
   * **Spring Boot Application**
   
   However, it can also be developed to use Gradle, Jenkins, Docker ...etc.

## Developing Portal Engine
   Sure we will be so happy if other developers will love to participate in this project and extend it to fullfill the concept mentioned above. The more collaborating developers with ultimate diligence to foster the Engine, the more prominence it will be. Any provision of a help will be highly appreciated.  

## Using Portal Engine
   The current version of Portal Engine can be used to generate Swing Applications and the GUI source format currently being supported is only XML. Recently, the Engine was partially updated to adop to the technology changes. Spring Boot in its simplicity was inspiring us to follow a similar approach. With a relatively small POM file, the engine will be ready to pick up the defined XML file by the developer and to generate the required components.
   **The current Portal Engine is already available on Maven Central Repository!**, below are some tutorials that can give a quick start:
 
### using Maven Archetype
   Via issuing the command below, you can generate a complete maven project that holds a startup swing based maven project.
   ```shell
   mvn archetype:generate -DgroupId=[Your Group ID] -DartifactId=[Your Artifact ID] -DarchetypeGroupId=org.smyld.app.pe -DarchetypeArtifactId=PESample-archetype -DinteractiveMode=false 
   ```
   The Group ID is usually the package name of your project, the Artifact ID will be the project name. Let us suppose that you will need a project to control some process, let us name it "ProcessController" and your package will be "com.mycompany.apps". You can issue it as follows:
   ```shell
   mvn archetype:generate -DgroupId=com.mycompany.apps -DartifactId=ProcessController -DarchetypeGroupId=org.smyld.app.pe -DarchetypeArtifactId=PESample-archetype -DinteractiveMode=false 
   ```
   
   
   
   
   
   
_More detailed description with tutorials on using the engine will follow_

