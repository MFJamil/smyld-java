# SMYLD Portal Engine
Portal Engine is establishing a compelete segregation between GUI layer and other modules of the software, the target is to eleminate the boilerplate code and to alleviates dealing with GUI components. The framework was originally designed to deal with Swing Components back in 2004, was successfully used with many applications which saved considerable time.

### Swing! are you serious? in 2019!
Well you are right, many alternatives are available but do not forget two facts:

#### 1 - Multi-threading 
#### 2 - Performance
#### 3 - Intellij

**Intellij !** ... YES! one of the most powerful editors in the market is still written with Swing. However Jet Brains holds also a powerful development team. Still, it is written in Swing.

**What about Java FX!** Well, till now, the performance is still on the side of Swing.


**However this is not only about Swing!** 



## Portal Engine Concept
The Portal Engine concept is to deal with the GUI layer as a separate module that will interact with the rest of the modules via defined interfaces. The GUI definition can take different forms:
#### 1 - XML file
#### 2 - YAML file
#### 3 - JSON file
#### 4 - Data Base

The Portal Engine will handle reading the file and generating the required components. The Components can belong to any technology/framework. We can define the components to two types:

#### 1 - Desktop:
Desktop based widgets can be any of the following:
##### 1 - JDK/Swing
##### 2 - JDK/SWT
##### 3 - Node.js/Electron
##### 4 - Node.js/NW.js
And many more..

#### 2 - Web.
Web based frameworks are available and involve some complexity that can be eliminated by the Portal Engine as well, component oriented approach might look like a limitation for the creativity of the developer when working with such frameworks, but saving a lot of time might be needed in some projects, some of the well known frameworks can be:
##### 1 - Angular
##### 2 - React
##### 3 - Vue
And many more ..

## Building Process
Building tools are rich and the Portal Engine can be extended to use most of them. Currently the available building ways are:
##### 1 - Stand Alone Application.
##### 2 - Maven artifact
##### 3 - Maven Plugin
##### 4 - Spring Boot Application.
However, it can also be developed to use Gradle, Jenkins, Docker ...etc.















_To Be Continued_
