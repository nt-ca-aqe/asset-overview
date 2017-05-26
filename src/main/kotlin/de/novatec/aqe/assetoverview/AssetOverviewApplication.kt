package de.novatec.aqe.assetoverview

import de.novatec.aqe.assetoverview.business.ProjectRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.TimeUnit

@SpringBootApplication
class AssetOverviewApplication {

    @Configuration
    @EnableScheduling
    class SchedulingConfiguration(private val repository: ProjectRepository) : SchedulingConfigurer {

        override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
            taskRegistrar.addFixedDelayTask(repository::refresh, TimeUnit.HOURS.toMillis(1))
        }

    }

}

fun main(args: Array<String>) {
    SpringApplication.run(AssetOverviewApplication::class.java, *args)
}
