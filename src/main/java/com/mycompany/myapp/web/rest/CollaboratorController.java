package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Collaborator;
import com.mycompany.myapp.service.CollaboratorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollaboratorController {

    public final CollaboratorService collaboratorService;

    public CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @PostMapping(path = "api/collaborator/new")
    public Collaborator addNewCollaborator(String firstName, String lastName, String position, String email) {
        Collaborator newCollaborator = new Collaborator(firstName, lastName, position, email);
        collaboratorService.createCollaborator(newCollaborator);
        return newCollaborator;
    }

    @DeleteMapping(path = "api/collaborator/delete")
    public void deleteCollaborator(Collaborator collaborator) {
        collaboratorService.deleteCollaborator(collaborator);
    }

    @GetMapping(path = "api/collaborator/get")
    public Collaborator getCollaborator(long collaboratorID) {
        return collaboratorService.getCollaboratorById(collaboratorID);
    }
}
