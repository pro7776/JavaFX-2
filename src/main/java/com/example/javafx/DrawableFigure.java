package com.example.javafx;

import geometry2d.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableFigure {
    private final Figure figure;
    private double x, y;
    private Color color;

    DrawableFigure(Figure figure, double x, double y, Color color) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(GraphicsContext graphicsContext) {
        figure.draw(graphicsContext, x, y, color);
    }

    public boolean contains(double x, double y) {
        if (figure instanceof Circle) {
            double radius = ((Circle) figure).getRadius();
            return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(radius, 2);
        } else if (figure instanceof Rectangle) {
            double width = ((Rectangle) figure).getWidth();
            double height = ((Rectangle) figure).getHeight();
            return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
        }
        return false;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
