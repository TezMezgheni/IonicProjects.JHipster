package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {}
