package main;

import main.model.*;
import main.view.*;
import main.controller.*;

import javax.swing.*;
import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        int gridSize = 20;
        GridModel model = new GridModel(gridSize);
        Bug bug = new Bug(1, 1);

        MainFrame frame = new MainFrame();
        GridPanel panel = new GridPanel(model, bug);
        frame.add(panel, BorderLayout.CENTER);

        // Создаем контроллеры
        DrawingController drawingController = new DrawingController(model, panel);
        BugController bugController = new BugController(model, bug, panel);

        // Панель с кнопками
        JPanel controlPanel = new JPanel(new FlowLayout());

        // Кнопка "Режим рисования"
        JButton drawButton = new JButton("Режим рисования");
        drawButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Режим рисования активирован. Кликайте и перетаскивайте мышь для создания фигуры.");
            drawingController.setDrawingMode(true);
        });

        // Кнопка "Закрасить область"
        JButton paintButton = new JButton("Закрасить область");
        paintButton.addActionListener(e -> {
            if (model.getShapePoints().isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Сначала нарисуйте фигуру в режиме рисования!");
                return;
            }
            bugController.startColoring();
        });

        // Кнопка "Очистить"
        JButton clearButton = new JButton("Очистить");
        clearButton.addActionListener(e -> {
            model.initializeGrid();
            bug.setPosition(new Point(1, 1));
            panel.repaint();
        });

        // Кнопка "Задать фигуру"
        JButton shapeButton = new JButton("Задать фигуру");
        shapeButton.addActionListener(e -> {
            model.initializeGrid(); // Очищаем перед созданием новой фигуры

            // Пример: рисуем прямоугольник 5x5 в центре
            int center = gridSize / 2;
            for (int i = center - 2; i <= center + 2; i++) {
                for (int j = center - 2; j <= center + 2; j++) {
                    if (i >= 0 && i < gridSize && j >= 0 && j < gridSize) {
                        model.addToShape(i, j);
                    }
                }
            }
            panel.repaint();
        });

        // Добавляем все кнопки на панель
        controlPanel.add(drawButton);
        controlPanel.add(paintButton);
        controlPanel.add(clearButton);
        controlPanel.add(shapeButton);

        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}