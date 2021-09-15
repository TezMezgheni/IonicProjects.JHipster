package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
