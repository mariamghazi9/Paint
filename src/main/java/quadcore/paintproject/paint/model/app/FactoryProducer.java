package quadcore.paintproject.paint.model.app;

public class FactoryProducer {
    public ShapeFactory getFactory(String factoryName) {
        if (factoryName.equalsIgnoreCase("closedShape") ||
                factoryName.equalsIgnoreCase("shape")) return new ClosedShapeFactory();
        if (factoryName.equalsIgnoreCase("line")) return new LineFactory();
        else throw new RuntimeException("Invalid Factory Type");
    }
}
