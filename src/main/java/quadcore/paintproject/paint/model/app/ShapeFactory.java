package quadcore.paintproject.paint.model.app;

import java.util.HashMap;

public abstract class ShapeFactory {

    private static int id = 0;

    public abstract Shape getShape(String type);

    protected static int createID() {
        return ++id;
    }

    protected static void setIdInitialValue(HashMap<Integer, Shape> map) {
        map.forEach((key, value) -> {
            int currentID = (value).getId();
            if (currentID > id) id = currentID;
        });
    }
}
