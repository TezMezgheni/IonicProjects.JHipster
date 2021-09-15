package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Project;
import com.mycompany.myapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    public Project getProject(long projectId) {
        return projectRepository.findById(projectId).get();
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
