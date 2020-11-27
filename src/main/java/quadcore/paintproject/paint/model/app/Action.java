package quadcore.paintproject.paint.model.app;

public class Action {

    protected enum Type {
        ADD,
        DELETE,
        EDIT
    }

    private Type type;
    private Shape shape;

    protected Action(Type type, Shape shape) {
        this.type = type;
        this.shape = shape.copyWithSameID();
    }

    protected Type reverseType() {
        if (this.type == Type.EDIT) return Type.EDIT;
        if (this.type == Type.ADD) return Type.DELETE;
        if (this.type == Type.DELETE) return Type.ADD;
        else return null;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
