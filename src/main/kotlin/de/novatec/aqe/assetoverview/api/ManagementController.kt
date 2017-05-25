package de.novatec.aqe.assetoverview.api

import de.novatec.aqe.assetoverview.business.ProjectRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manage")
internal class ManagementController(
        private val repository: ProjectRepository
) {

    @GetMapping("refresh")
    @PostMapping("refresh")
    fun refresh(): String {
        repository.refresh()
        return "refreshed data storage"
    }

}
