package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.service.ProjectService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "api/project/new")
    public Project addNewProject(Project project) {
        projectService.createProject(project);
        return project;
    }

    @GetMapping(path = "api/project/get")
    public Project findProject(long projectId) {
        return projectService.getProject(projectId);
    }

    @DeleteMapping(path = "api/project/delete")
    public void deleteProject(Project project) {
        projectService.deleteProject(project);
    }
}
