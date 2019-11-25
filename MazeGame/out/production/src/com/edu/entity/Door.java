package com.edu.entity;

public class Door implements MapSite {
    private Room roomFrom, roomTo;

    public Door() {};

    @Override
    public void enter() {
    }

    public Room getRoomFrom() {
        return roomFrom;
    }

    public void setRoomFrom(Room roomFrom) {
        this.roomFrom = roomFrom;
    }

    public Room getRoomTo() {
        return roomTo;
    }

    public void setRoomTo(Room roomTo) {
        this.roomTo = roomTo;
    }
}

