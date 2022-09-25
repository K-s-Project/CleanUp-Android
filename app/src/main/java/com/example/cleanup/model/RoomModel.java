package com.example.cleanup.model;


public class RoomModel {
    public String building_name,id,room_number,schedule,notes;

    public RoomModel(String building_name, String id, String room_number, String schedule, String notes) {
        this.building_name = building_name;
        this.id = id;
        this.room_number = room_number;
        this.schedule = schedule;
        this.notes = notes;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RoomModel(){}
}
