package net.heanoria.maven.swagger.codegen.generator;

import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.JavaClientCodegen;

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

    @Override
    public void processOpts() {
        super.processOpts();
        supportingFiles.clear();
        if(!excludePom)
            supportingFiles.add(new SupportingFile("pom.mustache", "", "pom.xml"));

        final String invokerFolder = (sourceFolder + File.separator + invokerPackage).replace(".", File.separator);
        supportingFiles.add(new SupportingFile("ApiClient.mustache", invokerFolder, "ApiClient.java"));
        supportingFiles.add(new SupportingFile("apiException.mustache", invokerFolder, "ApiException.java"));
        supportingFiles.add(new SupportingFile("Configuration.mustache", invokerFolder, "Configuration.java"));
        supportingFiles.add(new SupportingFile("JsonUtil.mustache", invokerFolder, "JsonUtil.java"));
        supportingFiles.add(new SupportingFile("StringUtil.mustache", invokerFolder, "StringUtil.java"));

        final String authFolder = (sourceFolder + File.separator + invokerPackage + ".auth").replace(".", File.separator);
        supportingFiles.add(new SupportingFile("auth/Authentication.mustache", authFolder, "Authentication.java"));
        supportingFiles.add(new SupportingFile("auth/HttpBasicAuth.mustache", authFolder, "HttpBasicAuth.java"));
        supportingFiles.add(new SupportingFile("auth/ApiKeyAuth.mustache", authFolder, "ApiKeyAuth.java"));
        supportingFiles.add(new SupportingFile("auth/OAuth.mustache", authFolder, "OAuth.java"));
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
