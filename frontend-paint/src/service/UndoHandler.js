import Circle from "../models/Circle";
import Rectangle from "../models/Rectangle";
import Triangle from "../models/Triangle";
import Ellipse from "../models/Ellipse";
import Line from "../models/Line";
import Square from "../models/Square";

class UndoHandler {
    /**
     * @param id of the shape to be deleted
     * @param currentApp instance of the current component
     */
    undoByDelete(id, currentApp) {
        var l = currentApp.shapes.length;
        // ID to be deleted
        for (var i = l - 1; i >= 0; i--) {
            if (currentApp.shapes[i].id == id) {
                currentApp.shapes.splice(i, i+1);
                break;
            }
        }
    }
    undoByAdding(type, shape, currentApp) {
        var newShape = null
        switch (type) {
            case "circle":
                newShape = new Circle();
                newShape.setAttributes(shape);
                break;
            case "rectangle":
                newShape = new Rectangle();
                newShape.setAttributes(shape);
                break;
            case "line":
                newShape = new Line();
                newShape.setAttributes(shape);
                break;
            case "square":
                newShape = new Square();
                newShape.setAttributes(shape);
                break;
            case "triangle":
                newShape = new Triangle();
                newShape.setAttributes(shape);
                break;
            case "ellipse":
                newShape = new Ellipse();
                newShape.setAttributes(shape);
                break;
          }
          if (newShape != null) {
              currentApp.shapes.push(newShape);
          }
    }
    undoByEditing(shape, currentApp) {
        var l = currentApp.shapes.length;
        var id = shape["id"];
        // ID to be deleted
        for (var i = l - 1; i >= 0; i--) {
            if (currentApp.shapes[i].id == id) {
                console.log("Got it");
                currentApp.shapes[i].setAttributes(shape);
                console.log(currentApp.shapes[i]);
                break;
            }
        }
    }

}

export default new UndoHandler;