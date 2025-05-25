package main.controller;

import main.model.CellState;
import main.model.Bug;
import main.model.GridModel;
import main.view.GridPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class BugController {
    private final GridModel model;
    private final Bug bug;
    private final GridPanel panel;
    private Timer timer;

    public BugController(GridModel model, Bug bug, GridPanel panel) {
        this.model = model;
        this.bug = bug;
        this.panel = panel;
    }

    public void startColoring() {
        if (model.getShapePoints().isEmpty()) return;

        // Очищаем посещенные клетки
        model.resetVisited();

        Point start = model.getShapePoints().get(0);
        bug.setPosition(new Point(start.x, start.y));

        Stack<Point> stack = new Stack<>();
        stack.push(start);
        model.setVisited(start.x, start.y, true);

        timer = new Timer(50, e -> {
            if (stack.isEmpty()) {
                timer.stop();
                JOptionPane.showMessageDialog(panel, "Закрашивание завершено!");
                return;
            }

            Point current = stack.pop();
            bug.setPosition(current);
            model.setCellState(current.x, current.y, CellState.PAINTED);

            addNeighbors(stack, current.x, current.y);
            panel.repaint(); // Явно запрашиваем перерисовку
        });
        timer.setRepeats(true); // Важно: таймер должен повторяться
        timer.start();
    }

    private void addNeighbors(Stack<Point> stack, int x, int y) {
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < model.getGridSize() &&
                    ny >= 0 && ny < model.getGridSize() &&
                    !model.isVisited(nx, ny) &&
                    model.getCellState(nx, ny) == CellState.SHAPE) {

                stack.push(new Point(nx, ny));
                model.setVisited(nx, ny, true);
            }
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }
}