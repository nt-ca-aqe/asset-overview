package de.novatec.aqe.assetoverview.app

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/projects")
class ProjectRestController(
        private val repository: ProjectRepository
) {

    @PostMapping
    fun postProject(@RequestBody project: Project): Project {
        project.id = UUID.randomUUID().toString();
        return repository.save(project)
    }

    @GetMapping
    fun getProjects(): List<Project> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: String): Project {
        return repository.findById(id).orElseThrow { IllegalArgumentException("not found") }
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: String) {
        repository.deleteById(id)
    }

}
