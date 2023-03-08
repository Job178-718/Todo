package com.sun.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import java.io.File

class JiaGuPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        //配置信息
        val jiagu = project.extensions.create("jiagu", JiaGuExt::class.java)
        //
        project.afterEvaluate {
            val appExt = project.extensions.getByType(AppExtension::class.java)
            appExt.applicationVariants.all{ it ->
                it.outputs.all {baseVariantOutput ->
                    val file = baseVariantOutput.outputFile
                    val name = baseVariantOutput.name
                    ////创建Task
                    project.tasks.create("jiagu${name}",JiaGuTask::class.java,file,jiagu)
                }
            }
        }

    }
}