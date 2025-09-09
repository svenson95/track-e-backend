package com.svenson95.track_e_backend.database.dto;

import java.util.List;

public class UserDTO {
  private String id; // MongoDB doc id
  private String googleId;
  private String name;
  private String picture;
  private String email;
  private Number weight;
  private Number height;
  private List<Long> workoutIds;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Number getWeight() {
    return weight;
  }

  public void setWeight(Number weight) {
    this.weight = weight;
  }

  public Number getHeight() {
    return height;
  }

  public void setHeight(Number height) {
    this.height = height;
  }

  public List<Long> getWorkoutIds() {
    return workoutIds;
  }

  public void setWorkoutIds(List<Long> workoutIds) {
    this.workoutIds = workoutIds;
  }
}
