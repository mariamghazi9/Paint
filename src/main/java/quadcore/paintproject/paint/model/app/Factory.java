package quadcore.paintproject.paint.model.app;

import java.util.HashMap;
import java.util.Map;

public abstract class Factory {

    private static int id = 0;

    public abstract Shape getShape(String type);

    protected int createID(){
        return ++id;
    }

    protected static void setID(HashMap<Integer, Shape> map) {
        for (Map.Entry<Integer, Shape> entry: map.entrySet()) {
            int currentID = (entry.getValue()).getId();
            if (currentID > id) id = currentID;
        }
    }
}
