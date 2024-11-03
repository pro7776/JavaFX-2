package com.example.javafx;

import exceptions.InvalidArgumentException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;


public class GeometryApp extends Application {
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX, offsetY;
    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(800, 600);
        VBox controlPane = getvBox();

        BorderPane root = new BorderPane();
        root.setTop(controlPane);
        root.setCenter(canvas);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Рисование кружков и прямоугольников");
        primaryStage.show();

        canvas.setOnMousePressed(e -> handleMousePressed(e.getX(), e.getY(), e.getButton()));
        canvas.setOnMouseDragged(e -> handleMouseDragged(e.getX(), e.getY()));
        canvas.setOnMouseReleased(e -> selectedFigure = null);
    }

    private VBox getvBox() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Button circleButton = new Button("Нарисовать круг");
        Button rectButton = new Button("Нарисовать прямоугольник");


        circleButton.setOnAction(e -> {
            try {
                addRandomFigure(new Circle(randomSizeForCircle()), graphicsContext);
            } catch (InvalidArgumentException ex) {
                throw new RuntimeException(ex);
            }
        });
        rectButton.setOnAction(e -> {
            try {
                addRandomFigure(new Rectangle(randomSizeForRectangle(), randomSizeForRectangle()), graphicsContext);
            } catch (InvalidArgumentException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox buttonBox = new HBox(10, circleButton, rectButton);
        return new VBox(10, buttonBox);
    }

    private void addRandomFigure(Figure figure, GraphicsContext graphicsContext) {
        double x = randomCoordinate(800);
        double y = randomCoordinate(600);
        Color color = randomColor();
        DrawableFigure drawable = new DrawableFigure(figure, x, y, color);
        figures.add(drawable);
        redrawCanvas(graphicsContext);
    }

    private void redrawCanvas(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, 800, 600);
        for (DrawableFigure drawable : figures) {
            drawable.draw(graphicsContext);
        }
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

    private double randomSizeForRectangle() {
        return 30 + new Random().nextInt(200);
    }

    private double randomSizeForCircle() {
        return 20 + new Random().nextInt(90);
    }

    private double randomCoordinate(double max) {
        return new Random().nextDouble() * max;
    }

    private Color randomColor() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }
}