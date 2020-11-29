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
    public Shape add(@RequestBody Map<String, Object> shape) {
        return service.add(shape);
    }

    @RequestMapping(value = "/deleteShape", method = RequestMethod.DELETE)
    public void delete(@RequestBody Map<String, Object> shape) {
        service.delete(shape);
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
