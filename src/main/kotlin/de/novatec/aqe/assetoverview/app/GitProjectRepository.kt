package de.novatec.aqe.assetoverview.app

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toList
import javax.annotation.PostConstruct

@Service
@ConfigurationProperties("git")
class GitProjectRepository(
        private val objectMapper: ObjectMapper
) {

    var repositoryUrl: String? = null

    private val localRepository = LocalGitRepository()
    private val fileSuffixFilter = { file: File ->
        file.name.endsWith(".project")
    }

    @PostConstruct
    fun initLocalRepository() {
        with(localRepository) {
            setOriginRemote(repositoryUrl!!)
            fetchOrigin()
            resetToOriginMaster()
        }
    }

    fun findAll(): List<Project> {
        val workingDirectory = localRepository.getWorkingDirectory()
        return Files.list(workingDirectory)
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(fileSuffixFilter)
                .map(this::readAsProject)
                .collect(toList())
    }

    fun findById(id: String): Project? {
        return Files.list(localRepository.getWorkingDirectory())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(fileSuffixFilter)
                .filter { file -> id == file.nameWithoutExtension }
                .map(this::readAsProject)
                .findFirst().orElse(null)
    }

    private fun readAsProject(projectFile: File): Project {
        return objectMapper.readValue(projectFile, Project::class.java)
    }

}
