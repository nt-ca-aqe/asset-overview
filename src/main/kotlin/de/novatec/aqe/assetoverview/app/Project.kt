package de.novatec.aqe.assetoverview.app

import org.springframework.data.annotation.Id
import java.util.*


class Project {

    @Id
    var id: String? = null
    var name: String? = null

    var organization: String? = null
    var repository: String? = null

    var documentation: String? = null

    var branches = ArrayList<String>()
    var services = ArrayList<String>()
    var artifacts = ArrayList<Artifact>()

    class Artifact {
        var groupId: String? = null
        var artifactId: String? = null
        override fun toString(): String {
            return "Artifact(groupId=$groupId, artifactId=$artifactId)"
        }
    }

    override fun toString(): String {
        return "Project(id=$id, name=$name, organization=$organization, repository=$repository, branches=$branches, services=$services, artifacts=$artifacts)"
    }

}
