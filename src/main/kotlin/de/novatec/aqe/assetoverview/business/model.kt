package de.novatec.aqe.assetoverview.business


data class Project(
        var id: String? = null,
        var name: String? = null,
        var organization: String? = null,
        var repository: String? = null,
        var documentation: String? = null,
        var branches: MutableList<String> = mutableListOf<String>(),
        var services: MutableList<String> = mutableListOf<String>(),
        var artifacts: MutableList<Artifact> = mutableListOf<Artifact>()
)

data class Artifact(
        var groupId: String? = null,
        var artifactId: String? = null
)

