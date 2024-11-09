package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class Circle implements Figure {
    private final double radius;

    @Override
    public void draw(GraphicsContext graphicsContext, double x, double y, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
