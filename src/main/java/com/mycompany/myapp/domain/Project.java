package com.mycompany.myapp.domain;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

//Creation des projets. (id, Name, type, status, startDate, endDate, imageUrl).

@Table(name = "project")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Type")
    private String type;

    @Column(name = "Status")
    private String status;

    @Column(name = "start Date")
    private Date startDate;

    @Column(name = "end Date")
    private Date endDate;

    @Column(name = "image URL")
    private String imageUrl;

    @OneToMany
    private List<Collaborator> collaboratorList;

    @OneToMany
    private List<TimeSheet> timeSheetList;

    public List<Collaborator> getCollaboratorList() {
        return collaboratorList;
    }

    public void setCollaboratorList(List<Collaborator> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public List<TimeSheet> getTimeSheetList() {
        return timeSheetList;
    }

    public void setTimeSheetList(List<TimeSheet> timeSheetList) {
        this.timeSheetList = timeSheetList;
    }

    public Project(String name, String type, String status, Date startDate, Date endDate, String imageUrl) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
