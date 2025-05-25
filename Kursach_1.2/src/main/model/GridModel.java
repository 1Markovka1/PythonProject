package main.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GridModel {
    private final CellState[][] grid;
    private final boolean[][] visited;
    private final List<Point> shapePoints;
    private final int gridSize;

    public GridModel(int gridSize) {
        this.gridSize = gridSize;
        this.grid = new CellState[gridSize][gridSize];
        this.visited = new boolean[gridSize][gridSize];
        this.shapePoints = new ArrayList<>();
        initializeGrid();
    }

    public void initializeGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = CellState.EMPTY;
                visited[i][j] = false;
            }
        }
        shapePoints.clear();
    }

    public void addToShape(int x, int y) {
        if (grid[x][y] != CellState.SHAPE) {
            grid[x][y] = CellState.SHAPE;
            shapePoints.add(new Point(x, y));
        }
    }

    // Геттеры и сеттеры...
    public CellState getCellState(int x, int y) {
        return grid[x][y];
    }

    public void setCellState(int x, int y, CellState state) {
        grid[x][y] = state;
    }

    public List<Point> getShapePoints() {
        return shapePoints;
    }

    public int getGridSize() {
        return gridSize;
    }

    public boolean isVisited(int x, int y) {
        return visited[x][y];
    }

    public void setVisited(int x, int y, boolean visited) {
        this.visited[x][y] = visited;
    }

    public void resetVisited() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                visited[i][j] = false;
            }
        }
    }
}