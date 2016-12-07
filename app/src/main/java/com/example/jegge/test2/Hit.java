package com.example.jegge.test2;

import io.realm.RealmObject;

/**
 * Created by Jegge on 2016-12-06.
 */
public class Hit extends RealmObject {

    float club;
    float distance;
    String wind;
    String hit;

    public float getClub(){
        return club;
    }

    public void setClub(float club){
        this.club = club;
    }

    public float getDistance(){
        return distance;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public String getWind(){
        return wind;
    }

    public void setWind(String wind){
        this.wind = wind;
    }

    public String getHit(){
        return hit;
    }

    public void setHit(String hit){
        this.hit = hit;
    }

}
