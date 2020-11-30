package quadcore.paintproject.paint.api;

import org.springframework.web.bind.annotation.*;
import quadcore.paintproject.paint.model.app.Action;
import quadcore.paintproject.paint.model.app.Shape;

import java.io.File;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class Controller {

    Service service = new Service();

    @RequestMapping(value = "/addShape", method = RequestMethod.POST)
    public int add(@RequestBody Map<String, Object> shape) {
        System.out.println("=============================");
        System.out.println(shape);
        return service.add(shape);
    }

    @RequestMapping(value = "/deleteShape", method = RequestMethod.DELETE)
    public void delete(@RequestParam int id) {
        service.deleteShape(id);
    }

    @RequestMapping(value = "/editShape", method = RequestMethod.POST)
    public void editShape(@RequestBody Map<String, Object> shape) {
        System.out.println("=============================");
        System.out.println(shape);
        service.editShape(shape);
    }

    @RequestMapping(value = "/copyShape", method = RequestMethod.GET)
    public Shape copyShape(@RequestParam int id) {
        return service.copyShape(id);
    }

    @RequestMapping(value = "/undo", method = RequestMethod.GET)
    public Action undo() {
        return service.undo();
    }

    @RequestMapping(value = "/redo", method = RequestMethod.GET)
    public Action redo() {
        return service.redo();
    }

    @RequestMapping(value = "/createCanvas", method = RequestMethod.POST)
    public void createCanvas() {
        service.createCanvas();
    }

    //TODO check save is working
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public File save(@RequestParam String type) {
        return service.save(type);
    }

    //TODO load
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public List<Shape> load(@RequestBody File file) {
        return service.load(file);
    }

    @RequestMapping(value = "/setName", method = RequestMethod.POST)
    public void setName(@RequestParam String canvasName) {
        service.setCanvasName(canvasName);
    }
}
