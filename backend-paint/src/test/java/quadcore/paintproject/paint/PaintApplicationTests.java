package quadcore.paintproject.paint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import quadcore.paintproject.paint.model.app.App;
import quadcore.paintproject.paint.model.app.Canvas;
import quadcore.paintproject.paint.model.app.ClosedShape;
import quadcore.paintproject.paint.model.app.Shape;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootTest
class PaintApplicationTests {
    App app;
    Canvas canvas;

    @Test
    void contextLoads() {
        app = App.getInstance();
        canvas = app.createCanvas();
        Shape shape = canvas.addShape("closedShape-circle");
        ((ClosedShape) shape).setFillColor(50, 60, 70);
        shape = canvas.addShape("closedShape-square");
        File file;
        try {
            file = app.save("json");
            canvas = null;
            canvas = app.load(file);
            int[] arr = {50, 60, 70};
            assert (Arrays.equals(canvas.getShapeForEditing(1).toClosedShape().getFillColor(), arr));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
