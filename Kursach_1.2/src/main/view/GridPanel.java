package main.view;

import main.model.CellState;
import main.model.GridModel;
import main.model.Bug;
import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    public static final int CELL_SIZE = 30;
    private final GridModel model;
    private final Bug bug;

    public GridPanel(GridModel model, Bug bug) {
        this.model = model;
        this.bug = bug;
        setPreferredSize(new Dimension(model.getGridSize() * CELL_SIZE,
                model.getGridSize() * CELL_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawBug(g);
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < model.getGridSize(); i++) {
            for (int j = 0; j < model.getGridSize(); j++) {
                g.setColor(Color.WHITE);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                if (model.getCellState(i, j) == CellState.SHAPE) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (model.getCellState(i, j) == CellState.PAINTED) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }

                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawBug(Graphics g) {
        g.setColor(Color.RED);
        Point pos = bug.getPosition();
        int x = pos.y * CELL_SIZE + CELL_SIZE / 4;
        int y = pos.x * CELL_SIZE + CELL_SIZE / 4;
        g.fillOval(x, y, CELL_SIZE / 2, CELL_SIZE / 2);
    }

    public int getCellSize() {
        return CELL_SIZE;
    }
}