package com.edu.entity;

public class Door implements MapSite {
    private boolean isOpen;
    private MapSite roomFrom, roomTo;

    public Door(){}

    public Door(MapSite roomFrom, MapSite roomTo) {
        this.roomFrom = roomFrom;
        this.roomTo = roomTo;
        isOpen = true;
    }

    @Override
    public void enter() {
        if(isOpen()){

        }else{
            System.out.println("Hit a wall!");
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    // will be used later on 2.
    public void setOpen(boolean open) {
        isOpen = open;
    }

    public MapSite getRoomTo() {
        return roomTo;
    }
}
