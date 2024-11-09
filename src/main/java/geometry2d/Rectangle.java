package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class Rectangle implements Figure {
    private final double width;
    private final double height;

    @Override
    public void draw(GraphicsContext graphicsContext, double x, double y, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, width, height);
    }
}
