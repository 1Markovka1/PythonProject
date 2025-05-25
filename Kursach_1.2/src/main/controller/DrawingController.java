package main.controller;

import main.model.GridModel;
import main.view.GridPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawingController {
    private final GridModel model;
    private final GridPanel panel;
    private boolean drawingMode = true;

    public void setDrawingMode(boolean enabled) {
        this.drawingMode = enabled;
    }

    public DrawingController(GridModel model, GridPanel panel) {
        this.model = model;
        this.panel = panel;
        setupMouseListeners();
    }

    private void setupMouseListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseEvent(e);
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseEvent(e);
            }
        });
    }

    private void handleMouseEvent(MouseEvent e) {
        if (!drawingMode) return; // Игнорируем события, если не в режиме рисования
        int cellSize = panel.getCellSize();
        int x = e.getY() / cellSize;
        int y = e.getX() / cellSize;
        if (x >= 0 && x < model.getGridSize() && y >= 0 && y < model.getGridSize()) {
            model.addToShape(x, y);
            panel.repaint();
        }
    }
}