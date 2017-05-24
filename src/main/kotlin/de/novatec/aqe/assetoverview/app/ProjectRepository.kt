package de.novatec.aqe.assetoverview.app

import org.springframework.data.mongodb.repository.MongoRepository

interface ProjectRepository : MongoRepository<Project, String>
