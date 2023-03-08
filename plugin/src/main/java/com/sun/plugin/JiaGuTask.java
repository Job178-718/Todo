package com.sun.plugin;

import org.gradle.api.Action;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskAction;
import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseProject;
import org.gradle.plugins.ide.internal.tooling.eclipse.DefaultEclipseTask;
import org.gradle.process.ExecSpec;

import java.io.File;

import javax.inject.Inject;

public class JiaGuTask extends DefaultTask{

    private final File apk;
    private final JiaGuExt jiagu;

    @Inject
    public JiaGuTask(File apk, JiaGuExt jiaGuExt){
        setGroup("jiagu");
        this.apk = apk;
        this.jiagu = jiaGuExt;
    }

    @TaskAction
    public void executeAction(){
        getProject().exec(new Action<ExecSpec>() {
              @Override
              public void execute(ExecSpec execSpec) {
              execSpec.commandLine("java", "-jar","-Dfile.encoding=utf-8",jiagu.getJiaguToolPath(),"-login",apk.getAbsolutePath(),jiagu.getUserName(),jiagu.getUserPsd());
              }
        });
    }


}
