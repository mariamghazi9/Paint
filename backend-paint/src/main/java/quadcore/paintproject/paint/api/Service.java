package quadcore.paintproject.paint.api;

import quadcore.paintproject.paint.model.app.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Service {

    Canvas canvas = App.getInstance().getCanvas();

    protected int add(Map<String, Object> shape) {
        String name = (String) shape.get("type");
        Shape myShape = canvas.addShape(name);
        Class<? extends Shape> type = myShape.getClass();

        if (type == Circle.class) {
            Circle circle = (Circle) myShape;
            circle.setLocation((Integer) shape.get("x"), (Integer) shape.get("y"));
            circle.setRadius((Integer) shape.get("r"));
            circle.setColor((String) shape.get("fill"));
            return circle.getId();
        }
        return 0;
    }

    protected void delete(int id) {
        canvas.removeShape(id);
    }

    protected Action undo() {
        return canvas.undo();
    }

    protected Action redo() {
        return canvas.redo();
    }

    protected void  editShape(Map<String, Object> shape) {
        //TODO same logic as add
    }

    protected Shape copyShape(int id) {
        return canvas.copyShape(id);
    }

    protected void createCanvas() {
        App.getInstance().createCanvas();
    }

    protected File save(String type) {
        try {
            return App.getInstance().save(type);
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
    }
}
