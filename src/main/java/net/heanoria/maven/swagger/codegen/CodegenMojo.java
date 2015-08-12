package net.heanoria.maven.swagger.codegen;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import net.heanoria.maven.swagger.codegen.generator.JavaClientCodegenerator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

@Mojo(name = "generate")
public class CodegenMojo extends AbstractMojo {

    private static final String TEMPLATE_DIR_PARAM = "templateDir";

    @Parameter(property = "generate.lang", defaultValue = "java2")
    private String lang = null;

    @Parameter(property = "generate.outputDirectory", defaultValue = "")
    private String output = null;

    @Parameter(property = "generate.templateDir", defaultValue = "")
    private String templateDir = null;

    @Parameter(property = "generate.spec", defaultValue = "")
    private String spec = null;

    @Parameter(property = "generate.invokerPackage", defaultValue = "io.swagger.client")
    private String invokerPackage = null;

    @Parameter(property = "generate.groupId", defaultValue = "io.swagger")
    private String groupId = null;

    @Parameter(property = "generate.artifactId", defaultValue = "swagger-java-client")
    private String artifactId = null;

    @Parameter(property = "generate.artifactVersion", defaultValue = "1.0.0")
    private String artifactVersion = null;

    @Parameter(property = "generate.excludePom", defaultValue = "false")
    private boolean excludePom = false;

    public void execute() throws MojoExecutionException, MojoFailureException {
        ClientOptInput input = new ClientOptInput();

        CodegenConfig config = forName(lang);
        if(config instanceof JavaClientCodegenerator) {
            JavaClientCodegenerator javaClientCodegenerator = (JavaClientCodegenerator) config;
            javaClientCodegenerator.setInvokerPackage(invokerPackage);
            javaClientCodegenerator.setGroupId(groupId);
            javaClientCodegenerator.setArtifactId(artifactId);
            javaClientCodegenerator.setArtifactVersion(artifactVersion);
            javaClientCodegenerator.setExcludePom(excludePom);
            javaClientCodegenerator.initProperties();
            config = javaClientCodegenerator;
        }
        config.setOutputDir(new File(output).getAbsolutePath());

        if (null != templateDir) {
            config.additionalProperties().put(TEMPLATE_DIR_PARAM, new File(templateDir).getAbsolutePath());
        }

        input.setConfig(config);

        Swagger swagger = new SwaggerParser().read(spec, input.getAuthorizationValues(), true);
        new DefaultGenerator().opts(input.opts(new ClientOpts()).swagger(swagger)).generate();
    }

    private CodegenConfig forName(String name) {
        ServiceLoader<CodegenConfig> loader = load(CodegenConfig.class);
        for (CodegenConfig config : loader) {
            if (config.getName().equals(name)) {
                return config;
            }
        }

        // else try to load directly
        try {
            return (CodegenConfig) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can't load config class with name ".concat(name), e);
        }
    }
}
