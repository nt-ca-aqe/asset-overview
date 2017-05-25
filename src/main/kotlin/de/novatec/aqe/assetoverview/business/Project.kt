package de.novatec.aqe.assetoverview.business


class Project {

    var id: String? = null
    var name: String? = null

    var organization: String? = null
    var repository: String? = null

    var documentation: String? = null

    var branches = mutableListOf<String>()
    var services = mutableListOf<String>()

    var artifacts = mutableListOf<Artifact>()

    class Artifact {

        var groupId: String? = null
        var artifactId: String? = null

        override fun toString(): String = "Artifact(" +
                "groupId=$groupId," +
                "artifactId=$artifactId" +
                ")"

    }

    override fun toString(): String = "Project(" +
            "name=$name," +
            "organization=$organization," +
            "repository=$repository," +
            "documentation=$documentation," +
            "branches=$branches," +
            "services=$services," +
            "artifacts=$artifacts" +
            ")"

}
