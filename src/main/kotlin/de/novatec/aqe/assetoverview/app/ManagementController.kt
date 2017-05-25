package de.novatec.aqe.assetoverview.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manage")
class ManagementController(
        private val repository: GitProjectRepository
) {

    @GetMapping("refresh")
    fun refresh(): String {
        repository.refresh()
        return "refreshed data storage"
    }

}
