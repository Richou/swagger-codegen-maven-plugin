package net.heanoria.maven.swagger.codegen.generator;

import com.wordnik.swagger.codegen.CodegenConfig;
import com.wordnik.swagger.codegen.languages.JavaClientCodegen;

public class JavaClientCodegenerator extends JavaClientCodegen implements CodegenConfig {

    @Override
    public String getName() {
        return "java2";
    }

    public void setInvokerPackage(String invokerPackage) {
        this.invokerPackage = invokerPackage;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

}
