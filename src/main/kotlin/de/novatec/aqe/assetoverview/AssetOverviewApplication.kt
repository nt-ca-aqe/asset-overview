package de.novatec.aqe.assetoverview

import de.novatec.aqe.assetoverview.app.Project
import de.novatec.aqe.assetoverview.app.Project.Artifact
import de.novatec.aqe.assetoverview.app.ProjectRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class AssetOverviewApplication

@Component
class TestData(
        private val repository: ProjectRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        addWebTester()
        addTestUtils()
    }

    private fun addWebTester() {
        val webTester = Project()
        with(webTester) {
            id = "webtester" // UUID.randomUUID().toString()
            name = "WebTester"
            organization = "testIT-WebTester"
            repository = "webtester2-core"
            documentation = "https://oss.sonatype.org/service/local/artifact/maven/redirect?r=releases&g=info.novatec.testit&a=webtester-documentation&v=LATEST&e=html"
            branches = arrayListOf(
                    "master",
                    "releases/2.2.x",
                    "releases/2.1.x",
                    "releases/2.0.x"
            )
            services = arrayListOf(
                    "travis-ci",
                    "codecov",
                    "bettercode"
            )
            artifacts = arrayListOf(
                    artifact("info.novatec.testit", "webtester-core"),
                    artifact("info.novatec.testit", "webtester-support-assertj3"),
                    artifact("info.novatec.testit", "webtester-support-hamcrest"),
                    artifact("info.novatec.testit", "webtester-support-junit4"),
                    artifact("info.novatec.testit", "webtester-support-junit5"),
                    artifact("info.novatec.testit", "webtester-support-spring4")
            )
        }
        repository.save(webTester)
    }

    private fun addTestUtils() {
        val testUtils = Project()
        with(testUtils) {
            id = "testutils" // UUID.randomUUID().toString()
            name = "TestUtils"
            organization = "nt-ca-aqe"
            repository = "testit-testutils"
            branches = arrayListOf(
                    "master"
            )
            services = arrayListOf(
                    "travis-ci",
                    "codecov",
                    "bettercode"
            )
            artifacts = arrayListOf(
                    artifact("info.novatec.testit", "testutils-logrecorder"),
                    artifact("info.novatec.testit", "testutils-logrecorder-logback"),
                    artifact("info.novatec.testit", "testutils-logsuppressor-logback"),
                    artifact("info.novatec.testit", "testutils-mockito")
            )
        }
        repository.save(testUtils)
    }

    private fun artifact(groupId: String, artifactId: String): Artifact {
        val artifact = Artifact()
        artifact.groupId = groupId
        artifact.artifactId = artifactId
        return artifact
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(AssetOverviewApplication::class.java, *args)
}
