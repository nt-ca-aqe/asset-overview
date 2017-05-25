package de.novatec.aqe.assetoverview.app

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/view")
class OverviewController(
        private val repository: GitProjectRepository
) {

    private val log: Logger = getLogger(javaClass)

    @GetMapping("/{id}")
    fun getOverview(@PathVariable id: String, model: Model): String {
        val project: Project = repository.findById(id) ?: throw NotFoundException(id)
        model.addAttribute("project", project)
        return "overview"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException) {
        log.warn("could not find project with ID <{}>", e.id, e)
    }

    class NotFoundException(val id: String) : RuntimeException()

}
