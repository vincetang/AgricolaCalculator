package com.vince.agricolacalculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedHashMap;

/**
 * Created by Vince on 16-07-17.
 */
public class Player implements Parcelable {



    public static enum PlayerColour {
        NATURAL,
        BLUE,
        RED,
        PURPLE
    }
    private String name;
    private PlayerColour colour;
    private LinkedHashMap<String, Integer> scores;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(colour.toString());
        dest.writeMap(scores);
    }

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(Parcel in) {
        name = in.readString();
        colour = PlayerColour.valueOf(in.readString());
        scores = in.readMap();

    }

    public Player(String name, PlayerColour colour) {
        this.name = name;
        this.colour = colour ;
        scores = new LinkedHashMap<String, Integer>();
    }

    public void addScore(String item, int score) {
        scores.put(item, score);
    }

    public LinkedHashMap<String, Integer> getScores() {
        return this.scores;
    }

    public void clearScores() {
        scores.clear();
    }

    public PlayerColour getColour() {
        return this.colour;
    }
}
