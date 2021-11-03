package de.entjic.model;

public class Hotel {
    public final int time;
    public final float rating;


    public Hotel(int time, float rating) {
        this.rating = rating;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "time=" + time +
                ", rating=" + rating +
                '}';
    }
}
