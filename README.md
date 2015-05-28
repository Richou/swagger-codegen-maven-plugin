# swagger-codegen-maven-plugin

A plugin maven that helps to generate classes for a webservice that use swagger, the plugin uses swagger codegen.  
I created this plugin, because in the swagger codegen, there is no way to change the package of generated classes.

### Usage

Basic usage :

In your pom.xml

```
<plugin>
    <groupId>net.heanoria.maven</groupId>
    <artifactId>swagger-codegen-maven-plugin</artifactId>
    <version>1.0.1</version>
    <executions>
        <execution>
            <id>japi-generate</id>
            <phase>compile</phase>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <lang>java</lang>
        <spec>${project.build.directory}/resources/swagger/swagger.json</spec>
    </configuration>
</plugin>
```

Change generated package

```
<plugin>
    <groupId>net.heanoria.maven</groupId>
    <artifactId>swagger-codegen-maven-plugin</artifactId>
    <version>1.0.1</version>
    <executions>
        <execution>
            <id>japi-generate</id>
            <phase>compile</phase>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <lang>java2</lang>
        <output>${project.build.directory}/gen/</output>
        <groupId>fr.energieperspective</groupId>
        <artifactId>${module-artifactid}</artifactId>
        <invokerPackage>${invoker-package}</invokerPackage>
        <artifactVersion>${module-artifact-version}</artifactVersion>
        <spec>${project.build.directory}/resources/swagger/swagger.json</spec>
        <templateDir>${project.basedir}/src/main/resources/templates/Java2</templateDir>
    </configuration>
</plugin>
```

This configuration will generate a complete maven project with a pom.xml, generate a jar with 

```
mvn install
```

Configuration :

`lang` language for generation  
`output` directory that will contains the generated class  
`groupId` for java2, the groupId for the generated pom.xml  
`artifactId` for java2, the artifact id of the jar built with the generated pom.xml  
`invokerPackage` for java2, package of generated classes  
`artifactVersion` for java2, version of the artifact built with the generated pom.xml  
`spec` input swagger file  
`templateDir` directory containing the mustache template  
