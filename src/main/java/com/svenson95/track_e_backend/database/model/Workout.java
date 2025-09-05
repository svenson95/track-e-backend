package com.svenson95.track_e_backend.database.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workouts")
public class Workout {
  @Id private String _id;

  private String userId; // MongoDB doc id
  private Long workoutId;
  private String lastUpdated; // UnixTimestring
  private String name;
  private List<WorkoutUnit> units;

  public Workout() {}

  public Workout(
      String userId, Long workoutId, String lastUpdated, String name, List<WorkoutUnit> units) {
    this.userId = userId;
    this.workoutId = workoutId;
    this.lastUpdated = lastUpdated;
    this.name = name;
    this.units = units;
  }

  public String getId() {
    return _id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
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

  public List<WorkoutUnit> getUnits() {
    return units;
  }

  public void setUnits(List<WorkoutUnit> units) {
    this.units = units;
  }

  public static class WorkoutUnit {
    private String name;
    private List<WorkoutExercise> exercises;

    public WorkoutUnit() {}

    public WorkoutUnit(String name, List<WorkoutExercise> exercises) {
      this.name = name;
      this.exercises = exercises;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<WorkoutExercise> getExercises() {
      return exercises;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
      this.exercises = exercises;
    }
  }

  public static class WorkoutExercise {
    private String name;
    private MuscleGroup muscleGroupPrimary;
    private MuscleGroup muscleGroupSecondary;
    private String sets;
    private String reps;
    private String rest;

    public WorkoutExercise() {}

    public WorkoutExercise(
        String name,
        MuscleGroup muscleGroupPrimary,
        MuscleGroup muscleGroupSecondary,
        String sets,
        String reps,
        String rest) {
      this.name = name;
      this.muscleGroupPrimary = muscleGroupPrimary;
      this.muscleGroupSecondary = muscleGroupSecondary;
      this.sets = sets;
      this.reps = reps;
      this.rest = rest;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public MuscleGroup getMuscleGroupPrimary() {
      return muscleGroupPrimary;
    }

    public void setMuscleGroupPrimary(MuscleGroup muscleGroupPrimary) {
      this.muscleGroupPrimary = muscleGroupPrimary;
    }

    public MuscleGroup getMuscleGroupSecondary() {
      return muscleGroupSecondary;
    }

    public void setMuscleGroupSecondary(MuscleGroup muscleGroupSecondary) {
      this.muscleGroupSecondary = muscleGroupSecondary;
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
