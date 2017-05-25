package de.novatec.aqe.assetoverview.app

import java.util.*


class Project {

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
            return "Artifact(" +
                    "groupId=$groupId," +
                    "artifactId=$artifactId" +
                    ")"
        }
        
    }

    override fun toString(): String {
        return "Project(" +
                "name=$name," +
                "organization=$organization," +
                "repository=$repository," +
                "documentation=$documentation," +
                "branches=$branches," +
                "services=$services," +
                "artifacts=$artifacts" +
                ")"
    }

}
