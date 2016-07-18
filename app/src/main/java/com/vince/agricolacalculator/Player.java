package com.vince.agricolacalculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
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
    private int totalScore;
    private String name;
    private PlayerColour colour;
    private HashMap<String, Integer> scores;

    public Player(String name, PlayerColour colour) {
        this.name = name;
        this.colour = colour ;
        scores = new HashMap<String, Integer>();
        totalScore = 0;
    }

    public Player(Parcel in) {
        name = in.readString();
        colour = PlayerColour.valueOf(in.readString());
        scores = in.readHashMap(Integer.class.getClassLoader());
    }


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



    public void addScore(String item, int score) {
        totalScore += score;
        scores.put(item, score);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public HashMap<String, Integer> getScores() {
        return this.scores;
    }

    public void clearScores() {
        scores.clear();
    }

    public PlayerColour getColour() {
        return this.colour;
    }

    public String getName() {
        return this.name;
    }
}
