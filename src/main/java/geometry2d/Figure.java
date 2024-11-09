package geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Figure {
    void draw(GraphicsContext graphicsContext, double x, double y, Color color);
}
