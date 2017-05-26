package de.novatec.aqe.assetoverview.ui

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import de.novatec.aqe.assetoverview.business.Project
import de.novatec.aqe.assetoverview.business.ProjectRepository
import de.novatec.aqe.assetoverview.ui.UiController.NotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.ui.Model


internal class UiControllerTest {

    val repository: ProjectRepository = mock()
    val cut: UiController = UiController(repository)

    @Test
    fun `get index HTML loads all projects into the model`() {
        val foundProjects: List<Project> = mock()
        doReturn(foundProjects).whenever(repository).findAll()

        val model: Model = mock()
        val templateName = cut.getIndex(model)

        assertThat(templateName).isEqualTo("index")
        verify(model).addAttribute("projects", foundProjects)
    }

    @Test
    fun `get project HTML loads the project into the model`() {
        val foundProject: Project = Project()
        doReturn(foundProject).whenever(repository).findById("projectId")

        val model: Model = mock()
        val templateName = cut.getProject("projectId", model)

        assertThat(templateName).isEqualTo("project")
        verify(model).addAttribute("project", foundProject)
    }

    @Test
    fun `get project HTML throws exception if project is not found`() {
        doReturn(null).whenever(repository).findById("projectId")
        assertThrows(NotFoundException::class.java, {
            cut.getProject("projectId", mock())
        }).also { e ->
            assertThat(e.id).isEqualTo("projectId")
        }
    }

}
