package com.svenson95.track_e_backend.database.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workouts")
public class Workout {
  @Id private String id;

  private Long userId; // MongoDB doc id
  private Long workoutId;
  private String lastUpdated; // UnixTimestring
  private String name;
  private List<ListItem> list;

  public Workout() {}

  public Workout(
      Long userId, Long workoutId, String lastUpdated, String name, List<ListItem> list) {
    this.userId = userId;
    this.workoutId = workoutId;
    this.lastUpdated = lastUpdated;
    this.name = name;
    this.list = list;
  }

  public String getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getWorkoutId() {
    return workoutId;
  }

  public void setWorkoutId(Long workoutId) {
    this.workoutId = workoutId;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ListItem> getList() {
    return list;
  }

  public void setList(List<ListItem> list) {
    this.list = list;
  }

  public static class ListItem {
    private String name;
    private ItemType type;

    public ListItem() {}

    public ListItem(String name, ItemType type) {
      this.name = name;
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public ItemType getType() {
      return type;
    }

    public void setType(ItemType type) {
      this.type = type;
    }
  }

  public static class Exercise extends ListItem {
    private MuscleGroup muscleGroup;
    private String sets;
    private String reps;
    private String rest;

    public Exercise() {}

    public Exercise(
        String name,
        ItemType type,
        MuscleGroup muscleGroup,
        String sets,
        String reps,
        String rest) {
      super(name, type);
      this.muscleGroup = muscleGroup;
      this.sets = sets;
      this.reps = reps;
      this.rest = rest;
    }

    public MuscleGroup getMuscleGroup() {
      return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
      this.muscleGroup = muscleGroup;
    }

    public String getSets() {
      return sets;
    }

    public void setSets(String sets) {
      this.sets = sets;
    }

    public String getReps() {
      return reps;
    }

    public void setReps(String reps) {
      this.reps = reps;
    }

    public String getRest() {
      return rest;
    }

    public void setRest(String rest) {
      this.rest = rest;
    }
  }

  public enum ItemType {
    EXERCISE,
    LABEL,
    SPACE
  }

  public enum MuscleGroup {
    calves,
    adductors,
    abductors,
    hamstrings,
    quads,
    glutes,
    forearms,
    triceps,
    biceps,
    lats,
    abs,
    core,
    chest,
    traps,
    shoulders,
    neck
  }
}
