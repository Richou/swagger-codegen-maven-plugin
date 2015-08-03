package net.heanoria.maven.swagger.codegen.generator;

import com.wordnik.swagger.codegen.CodegenConfig;
import com.wordnik.swagger.codegen.SupportingFile;
import com.wordnik.swagger.codegen.languages.JavaClientCodegen;

import java.io.File;

public class JavaClientCodegenerator extends JavaClientCodegen implements CodegenConfig {

    public Boolean excludePom = false;

    public void initProperties() {
        templateDir = "Java2";
        apiPackage = invokerPackage + ".api";
        modelPackage = invokerPackage + ".model";

        additionalProperties.put("invokerPackage", invokerPackage);
        additionalProperties.put("groupId", groupId);
        additionalProperties.put("artifactId", artifactId);
        additionalProperties.put("artifactVersion", artifactVersion);
    }

    public void initSupportingFiles() {
        supportingFiles.clear();
        if(!excludePom)
            supportingFiles.add(new SupportingFile("pom.mustache", "", "pom.xml"));

        supportingFiles.add(new SupportingFile("apiInvoker.mustache", (sourceFolder + File.separator + invokerPackage).replace(".", java.io.File.separator), "ApiInvoker.java"));
        supportingFiles.add(new SupportingFile("JsonUtil.mustache", (sourceFolder + File.separator + invokerPackage).replace(".", java.io.File.separator), "JsonUtil.java"));
        supportingFiles.add(new SupportingFile("apiException.mustache", (sourceFolder + File.separator + invokerPackage).replace(".", java.io.File.separator), "ApiException.java"));
    }

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

    public void setExcludePom(Boolean excludePom) {
        this.excludePom = excludePom;
    }
}
