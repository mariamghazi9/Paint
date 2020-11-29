package quadcore.paintproject.paint.api;

import org.springframework.web.bind.annotation.*;
import quadcore.paintproject.paint.model.app.Action;
import quadcore.paintproject.paint.model.app.Shape;

import java.util.Map;


@CrossOrigin
@RestController
public class Controller {

    Service service = new Service();
    @RequestMapping(value = "/addShape", method = RequestMethod.POST)
    public void add(@RequestBody Map<String, Object> shape) {
        service.add(shape);
    }

    @RequestMapping(value = "/deleteShape/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @RequestMapping(value = "/undo", method = RequestMethod.GET)
    public Action undo() {
        return service.undo();
    }

    @RequestMapping(value = "/redo", method = RequestMethod.GET)
    public Action redo() {
        return service.redo();
    }
}
