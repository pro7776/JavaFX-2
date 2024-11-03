package geometry2d;

import exceptions.InvalidArgumentException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Figure {
    private final double radius;

    public Circle(double radius) throws InvalidArgumentException {
        if (radius <= 0) {
            throw new InvalidArgumentException("Radius must be greater than 0");
        }
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, double x, double y, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public String toString() {
        return "Circle with radius = " + radius;
    }

    public double getRadius() {
        return radius;
    }
}
