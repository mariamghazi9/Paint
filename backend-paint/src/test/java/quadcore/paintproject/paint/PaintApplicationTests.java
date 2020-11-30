package quadcore.paintproject.paint;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class PaintApplicationTests {
    App app;
    Canvas canvas;

    @Test
    void contextLoads() {
        app = App.getInstance();
        canvas = app.createCanvas();
        Shape shape = canvas.addShape("circle");
        shape.setColor("#506070");
        ((Circle) shape).setLocation(6,6);
        ((Circle) shape).setRadius(5);
        shape = canvas.addShape("square");
        shape.setColor("#050607");
        canvas.addShape("rectangle");
        File file;
        try {
            file = app.save("json");
            canvas = null;
            canvas = app.load(file);
            assert (canvas.getShapeForEditing(1).getColor().equals("#506070"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
