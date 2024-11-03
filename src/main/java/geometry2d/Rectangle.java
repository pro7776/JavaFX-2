package geometry2d;

import exceptions.InvalidArgumentException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) throws InvalidArgumentException {
        if (width <= 0 || height <= 0) {
            throw new InvalidArgumentException("Side must be greater than 0");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext graphicsContext, double x, double y, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle with width = " + width + " and height = " + height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
