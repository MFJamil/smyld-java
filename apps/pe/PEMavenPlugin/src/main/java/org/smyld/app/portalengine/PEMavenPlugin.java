package org.smyld.app.portalengine;


import org.smyld.gui.portal.engine.PortalManager;
import org.smyld.app.pe.projectbuilder.MavenProjectBuilder;
import org.smyld.app.pe.projectbuilder.ProjectBuilder;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Mojo(name = "generate",defaultPhase = LifecyclePhase.GENERATE_SOURCES,requiresProject = true, threadSafe = true)
public class PEMavenPlugin extends AbstractMojo {
    @Parameter
    private String app;

    @Parameter
    private String source;

    @Parameter
    private String output;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    @Parameter( defaultValue = "${localRepository}", required = true, readonly = true )
    private ArtifactRepository localRepository;



    private PortalManager portalManager;

    private void printProps(){
        System.getProperties().keySet().stream().forEach(curKey -> System.out.println("Key ( " + curKey.toString() + ") : " + System.getProperty((String)curKey)));

    }
    public void execute() throws MojoExecutionException, MojoFailureException {
        //printProps();

        app = getResources() + "/" + app;
        getLog().info("This is the SMYLD Portal Engine Plugin ....... ");
        getLog().info("App                : " + app);



        //variables.forEach((curKey,curVal) -> getLog().info("\t " + curKey +  " : " + curVal));
        try {


            // Forwarding the provided source files (language files, images, text files) to be placed in the portal engine right source
            // Warning : this might cause issue if the developer using the plugin wants the resources elsewhere
            for (Object curRes:project.getResources()){
                if (curRes instanceof Resource){
                   Resource mvnRes = (Resource)curRes;
                   mvnRes.setTargetPath("org/smyld/resources");
                   getLog().info("Setting the resource directory  ");
                }
            }


            // Purpose : We need to include all required jar files in one deployed jar file

            // Attempt 1 : to locate the required jar files via the dependencies available and pass them to Portal Engine APIs to update the jar file
            // Problem   : Did not manage to locate the physical jar file using the dependency management key
            // Tip : the local repository works fine, which can be used to fetch the library
            //*
            for (Object curDep:project.getDependencies()){
                if (curDep instanceof Dependency){
                    Dependency mvnDep = (Dependency)curDep;
                    getLog().info("Artifact ID    : " + mvnDep.getArtifactId());
                    getLog().info("Management Key : " + mvnDep.getManagementKey());
                    //getLog().info("Management Loc : " + mvnDep.getLocation(mvnDep.getManagementKey()));
                    getLog().info("URL            : " + localRepository.getUrl());

                }
            }
            //*/

            // Attempt 2 : to add Maven Assembly plugin dynamically to the project
            // Problem   : did not manage to find how and which class in the maven plugin should be instantiated and added to the addPlugin method
            /*
            SingleAssemblyMojo assemblyMojo = new SingleAssemblyMojo();
            MavenArchiveConfiguration mavenArchiveConfiguration = new MavenArchiveConfiguration();
            assemblyMojo.setArchive(mavenArchiveConfiguration);
            mavenArchiveConfiguration.addManifestEntry("Test","Value");
            project.addPlugin(assemblyMojo); ??
            //*/


            executeVersion1(); // Generating the source files
            project.addCompileSourceRoot(getGeneratedSourceFiles()); // Adding them to the source root


        } catch (Exception e) {
            getLog().error("Exception while generating source code ",e);
        }
    }
    private String getGeneratedSourceFiles(){
        return getTargetPath() + "generated-sources/portalengine";

    }

    private String getTargetPath(){
        return project.getBasedir().getPath() + "/target/";

    }

    private String getResources(){
        return project.getBasedir().getPath() + "/src/main/resources";

    }

    private Map<String,String> initVariablesValues(){
        Map<String,String> variables = new HashMap<>();
        variables.put("source"    ,source!=null?source:getResources());
        variables.put("output_dir",output!=null?output:getGeneratedSourceFiles());
        return variables;

    }


    /**
     * This is the first method that will put more build setup in the xml file, variables like Home, Generated_Source are not used here.
     *
     * */
    private void executeVersion1() throws Exception{
        List<String> args = new ArrayList<>();
        initVariablesValues().forEach((curKey,curVal) -> {args.add("-var:" +curKey);args.add(curVal);});
        String[] finalArgs = new String[args.size()];
        args.toArray(finalArgs);
        getLog().info("Running the portal Engine with 2 parameters  : " );
        getLog().info("App   : " + app );
        getLog().info("Args  : " + finalArgs );
        MavenProjectBuilder mavenBuilder = new MavenProjectBuilder();
        mavenBuilder.setProjectPath(getTargetPath());
        mavenBuilder.setGroup(project.getGroupId());
        mavenBuilder.setSourcePath(getGeneratedSourceFiles());
        portalManager = new PortalManager(mavenBuilder,app,finalArgs);


    }

    //TODO Need a more robust and clear maven setup for building the sources, migrating what is inside build tag in the protal engine xml file to maven pom file
}
