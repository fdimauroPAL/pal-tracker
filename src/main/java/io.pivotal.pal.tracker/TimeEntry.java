package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {

    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.date = date;
        this.hours = hours;
        this.userId = userId;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this(0, projectId, userId, date, hours);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean equals(Object obj)  {
        TimeEntry newTimeEntry = (TimeEntry) obj; 
        return (this.getId() == newTimeEntry.getId() &&
                this.getDate().equals(newTimeEntry.getDate()) &&
                this.getHours() == newTimeEntry.getHours() &&
                this.getProjectId() == newTimeEntry.getProjectId() &&
                this.getUserId() == newTimeEntry.getUserId());
    }
}
