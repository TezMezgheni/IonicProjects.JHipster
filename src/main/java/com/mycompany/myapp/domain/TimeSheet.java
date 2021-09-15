package com.mycompany.myapp.domain;

import java.util.Date;
import javax.persistence.*;

//Creation Timesheet (id, localDate, hours, summary, comment, type).

@Table(name = "time_sheet")
@Entity
public class TimeSheet {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "local Date", nullable = false)
    private Date localDate;

    @Column(name = "Hours")
    private int hours;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Type")
    private String type;

    @ManyToOne
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TimeSheet(Date localDate, int hours, String summary, String comment, String type) {
        this.localDate = localDate;
        this.hours = hours;
        this.summary = summary;
        this.comment = comment;
        this.type = type;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
