package org.smyld.app.pe.projectbuilder;

public class ProjectBuilder {
    ProjectBuildSource source;

    public ProjectBuilder(ProjectBuildSource source) {
        this.source = source;
    }

    public ProjectBuildSource getSource() {
        return source;
    }

    public void setSource(ProjectBuildSource source) {
        this.source = source;
    }

}
