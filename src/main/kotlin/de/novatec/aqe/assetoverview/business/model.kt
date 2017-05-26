package de.novatec.aqe.assetoverview.business


data class Project(
        var id: String? = null,
        var name: String? = null,
        var organization: String? = null,
        var repository: String? = null,
        var documentation: String? = null,
        var branches: List<String> = listOf<String>(),
        var services: List<String> = listOf<String>(),
        var artifacts: List<Artifact> = listOf<Artifact>()
)

data class Artifact(
        var groupId: String? = null,
        var artifactId: String? = null
)

