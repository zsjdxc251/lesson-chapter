package com.lesson.builder.maven.plugin;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 *

 * @author zhengshijun
 * @version created on 2019/5/26.
 */
@Mojo(name = "htmlGenerate",defaultPhase = LifecyclePhase.COMPILE)
public class HtmpGeneratePlugin extends AbstractMojo {


    @Parameter(property = "username")
    private String username;


    @Parameter(property = "modules")
    private List<String> modules;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {


        this.getLog().info(StringUtils.repeat("*",20));

        this.getLog().info("username:".concat(username));

        modules.forEach(this.getLog()::info);

    }
}
