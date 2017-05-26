package de.novatec.aqe.assetoverview.ui

import de.novatec.aqe.assetoverview.business.Project
import de.novatec.aqe.assetoverview.business.ProjectRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
internal class UiController(
        private val repository: ProjectRepository
) {

    private val log: Logger = getLogger(javaClass)


    @GetMapping("/")
    fun getIndex(model: Model): String {
        val projects: List<Project> = repository.findAll();
        model.addAttribute("projects", projects)
        return "index"
    }


    @GetMapping("/{id}")
    fun getProject(@PathVariable id: String, model: Model): String {
        val project: Project = repository.findById(id) ?: throw NotFoundException(id)

        model.addAttribute("project", project)

        model.addAttribute("render_documentation", project.documentation?.isNotBlank() ?: false)

        model.addAttribute("render_ci", project.branches.isNotEmpty())
        model.addAttribute("render_artifacts", project.artifacts.isNotEmpty())

        model.addAttribute("render_travis", project.services.contains("travis"))
        model.addAttribute("render_codecov", project.services.contains("codecov"))
        model.addAttribute("render_bettercode", project.services.contains("bettercode"))

        return "project"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException) {
        log.warn("could not find project with ID <{}>", e.id, e)
    }

    class NotFoundException(val id: String) : RuntimeException()

}
