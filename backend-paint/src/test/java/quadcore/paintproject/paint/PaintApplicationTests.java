package quadcore.paintproject.paint;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import quadcore.paintproject.paint.model.app.*;
import quadcore.paintproject.paint.model.saveload.FileManager;

import java.io.IOException;

@SpringBootTest
class PaintApplicationTests {
    App app;
    Canvas canvas;

    @Test
    void contextLoads() {
        app = App.getInstance();
        canvas = app.load();
        Shape shape = canvas.addShape("closedShape-circle");
        ((ClosedShape)shape).setFillColor(50, 60, 70);
        try {
            app.save("xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
