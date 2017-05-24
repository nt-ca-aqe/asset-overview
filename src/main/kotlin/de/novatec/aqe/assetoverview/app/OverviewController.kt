package de.novatec.aqe.assetoverview.app

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/view")
class OverviewController(
        private val repository: ProjectRepository
) {

    @GetMapping("/{id}")
    fun getOverview(@PathVariable id: String, model: Model): String {
        val project = repository.findById(id).orElseThrow { IllegalArgumentException("not-found") }
        model.addAttribute("project", project)
        return "overview"
    }

}
