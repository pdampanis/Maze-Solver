package com.maze.gentrack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeChallenge {
    public static void main(String[] args) {
        String fileName = args[0];
        List<String> list = new ArrayList<>();

        int i = 0, j = 0, width = 0, height = 0;
        int startX = 0, startY = 0, endX = 0, endY = 0;
        List<String[]> pointList = new ArrayList<>();

        int[][] pointArray = new int[0][];
        int k = 0, l = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                switch (i) {
                    case 0: {
                        String[] s = line.split(" ");
                        width = Integer.parseInt(s[0]);
                        height = Integer.parseInt(s[1]);
                        pointArray = new int[height][width];
                        k = 0;
                        l = -1;
                        break;
                    }
                    case 1: {
                        String[] s = line.split(" ");
                        startX = Integer.parseInt(s[0]);
                        startY = Integer.parseInt(s[1]);
                        break;
                    }
                    case 2: {
                        String[] s = line.split(" ");
                        endX = Integer.parseInt(s[0]);
                        endY = Integer.parseInt(s[1]);
                        break;
                    }
                    default: {
                        for (String s : line.split(" ")) {
                            pointArray[k][++l] = Integer.parseInt(s);
                        }
                        k++;
                        l = -1;
                        pointList.add(line.split(" "));
                    }
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        Grid grid = new Grid(width, height);
        char[][] representationArray = new char[height][width];

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                if (pointArray[i][j] == 1) {
                    grid.walls.add(new Point(i, j));
                    representationArray[i][j] = '#';
                } else {
                    representationArray[i][j] = ' ';
                }
            }
        }

        Maze maze = grid.search(new Point(startY, startX), new Point(endY, endX));
        if (maze.hasSolution()) {
            System.out.println("Maze has a solution.");
            for (Point p : maze.getSolutionPath()) {
                representationArray[p.x][p.y] = 'X';
            }
        } else {
            System.out.println("Maze has not a solution.");
        }


        representationArray[startY][startX] = 'S';
        representationArray[endY][endX] = 'E';
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                System.out.print(representationArray[i][j]);
            }
            System.out.println();
        }
    }
}
