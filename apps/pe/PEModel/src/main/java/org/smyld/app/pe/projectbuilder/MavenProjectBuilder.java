package org.smyld.app.pe.projectbuilder;

public class MavenProjectBuilder extends ProjectBuilder {
    String projectPath;
    String group;
    String sourcePath;




    public MavenProjectBuilder() {
        super(ProjectBuildSource.Maven);
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
}
