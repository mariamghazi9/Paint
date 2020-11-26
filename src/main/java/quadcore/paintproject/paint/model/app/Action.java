package quadcore.paintproject.paint.model.app;

public class Action {

    protected enum Type {
        ADD,
        DELETE,
        EDIT
    }

    private Type type;
    private Shape shapeBeforeAction;

    protected Action(Type type, Shape shapeBeforeAction) {
        this.type = type;
        this.shapeBeforeAction = shapeBeforeAction;
    }
}
