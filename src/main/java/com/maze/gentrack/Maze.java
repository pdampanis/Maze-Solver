package com.maze.gentrack;

import java.util.*;

public class Maze {

    private Set<Point> solutionPath;
    private boolean hasSolution;

    public Maze(Set<Point> solutionPath, boolean hasSolution){
        this.solutionPath = new LinkedHashSet<Point>(solutionPath);
        this.hasSolution = hasSolution;
    }

    public Maze(boolean hasSolution){
        this.hasSolution = hasSolution;
    }

    public Set<Point> getSolutionPath() {
        return solutionPath;
    }

    public boolean hasSolution() {
        return hasSolution;
    }
}
