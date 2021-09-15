package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Collaborator;
import com.mycompany.myapp.repository.CollaboratorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    public CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public Collaborator createCollaborator(Collaborator collaborator) {
        collaboratorRepository.save(collaborator);
        return collaborator;
    }

    public void deleteCollaborator(Collaborator collaborator) {
        collaboratorRepository.delete(collaborator);
    }

    protected List<Collaborator> getCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Collaborator getCollaboratorById(long collaboratorID) {
        return collaboratorRepository.findById(collaboratorID).get();
    }
}
