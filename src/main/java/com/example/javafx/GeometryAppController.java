package com.example.javafx;

import geometry2d.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeometryAppController {
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;

    @FXML
    private Canvas canvas;
    @FXML
    private Button circleButton;
    @FXML
    private Button rectangleButton;

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        circleButton.setOnAction(e -> addRandomFigure(new Circle(randomSizeForCircle()), gc));
        rectangleButton.setOnAction(e -> addRandomFigure(new Rectangle(randomSizeForRectangle(), randomSizeForRectangle()), gc));
        canvas.setOnMousePressed(e -> handleMousePressed(e.getX(), e.getY(), e.getButton()));
        canvas.setOnMouseDragged(e -> handleMouseDragged(e.getX(), e.getY()));
        canvas.setOnMouseReleased(e -> selectedFigure = null);
    }


    private void addRandomFigure(Figure figure, GraphicsContext gc) {
        double x = new Random().nextDouble(1024);
        double y = new Random().nextDouble(768);
        Color color = randomColor();
        DrawableFigure drawable = new DrawableFigure(figure, x, y, color);
        figures.add(drawable);
        redrawCanvas(gc);
    }

    @FXML
    private void drawCircle(){
        addRandomFigure(new Circle(randomSizeForCircle()), canvas.getGraphicsContext2D());
    }

    @FXML
    private void drawRectangle(){
        addRandomFigure(new Rectangle(randomSizeForRectangle(), randomSizeForRectangle()), canvas.getGraphicsContext2D());
    }

    private void handleMousePressed(double x, double y, MouseButton button) {
        for (int i = figures.size() - 1; i >= 0; i--) {
            DrawableFigure drawable = figures.get(i);
            if (drawable.contains(x, y)) {
                if (button == MouseButton.PRIMARY) {
                    selectedFigure = drawable;
                    figures.remove(i);
                    figures.add(drawable);
                    offsetX = x - drawable.getX();
                    offsetY = y - drawable.getY();
                } else if (button == MouseButton.SECONDARY) {
                    drawable.setColor(randomColor());
                }
                break;
            }
        }
        redrawCanvas(canvas.getGraphicsContext2D());
    }

    private void handleMouseDragged(double x, double y) {
        if (selectedFigure != null) {
            selectedFigure.setX(x - offsetX);
            selectedFigure.setY(y - offsetY);
            redrawCanvas(canvas.getGraphicsContext2D());
        }
    }

    private void redrawCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableFigure drawable : figures) {
            drawable.draw(gc);
        }
    }


    private double randomSizeForRectangle() {
        return 30 + new Random().nextInt(200);
    }

    private double randomSizeForCircle() {
        return 20 + new Random().nextInt(90);
    }

    private Color randomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }
}
