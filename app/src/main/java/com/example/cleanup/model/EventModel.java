package com.example.cleanup.model;

public class EventModel {
    public String event_name,id,notes,schedule;

    public EventModel(String event_name, String id, String notes, String schedule) {
        this.event_name = event_name;
        this.id = id;
        this.notes = notes;
        this.schedule = schedule;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public EventModel(){}
}
