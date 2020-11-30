package quadcore.paintproject.paint;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


@SpringBootTest
class PaintApplicationTests {
    static App app;
    static Canvas canvas;

    @BeforeAll
    static void setup() {
        app = App.getInstance();
        canvas = app.createCanvas();
        Shape shape = canvas.addShape("circle");
        int id = shape.getId();
        shape.setColor("#506070");
        ((Circle) shape).setLocation(6,6);
        ((Circle) shape).setRadius(5);
        shape = canvas.getShapeForEditing(id);
        ((Circle) shape).setLocation(7,7);
        System.out.println(Arrays.toString(canvas.undo().getShape().getPointAsArr()));
    }
    @Test
    void contextLoads() {

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
