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



    public RoomModel(){}
}
