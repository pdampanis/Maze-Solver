package com.maze.gentrack;

import java.util.*;

public class Grid {

    public int width, height;
    public List<Point> walls = new ArrayList<>();

    public Grid(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public static final Point[] validMoves = new Point[]
    {
       new Point(1, 0),
       new Point(0, -1),
       new Point(-1, 0),
       new Point(0, 1)
    };

    public boolean isInsideGrid(Point point){
        return point.x >= 0 && point.x < height
                && point.y >= 0 && point.y < width;
    }

    public Point getWrappedPoint(Point point){
        int newX = point.x, newY = point.y;
        if(point.x < 0){
            newX = width - 1;
        } else if(point.x == width){
            newX = 0;
        } else if(point.y < 0){
            newY = height - 1;
        } else if(point.y == height){
            newY = 0;
        }
        return new Point(newX, newY);
    }

    public boolean canMoveTo(Point point){
        return !walls.contains(point);
    }

    public List<Point> getNeighbors(Point point){
        List<Point> pathPoints = new ArrayList<>();
        for(Point p : validMoves){
            Point next = new Point(point.x + p.x, point.y + p.y);
            if(isInsideGrid(next) && canMoveTo(next)){
                pathPoints.add(next);
            } else if (!isInsideGrid(next)){
                Point wrappedPoint = getWrappedPoint(next);
                if(canMoveTo(wrappedPoint)){
                    pathPoints.add(wrappedPoint);
                    walls.add(next);
                }
            }
        }
        return pathPoints;
    }

    // Breadth First Search
    public Maze search(Point start, Point goal){
        Queue<Point> frontier = new LinkedList<Point>();
        frontier.add(start);

        Map<Point, Point> visited = new HashMap<>();
        visited.put(start, null);

        while (frontier.size() > 0) {
            Point current = frontier.remove();

            // Visiting current point
            if(current.equals(goal)){
                return new Maze(buildPath(visited, start, goal), true);
            }

            List<Point> p = this.getNeighbors(current);
            for(Point next : p){
                if(!visited.containsKey(next)) {
                    frontier.add(next);
                    visited.put(next, current);
                }
            }
        }
        return new Maze(false);
    }


    public Set<Point> buildPath(Map<Point, Point> visited, Point start, Point goal){
        Set<Point> path = new HashSet<>();
        Point current = new Point(goal.getX(), goal.getY());

        while(!current.equals(start)){
            path.add(current);
            current = visited.get(current);
        }
        return path;
    }
}
