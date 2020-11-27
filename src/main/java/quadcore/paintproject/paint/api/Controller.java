package quadcore.paintproject.paint.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    @CrossOrigin(origins = "http://localhost:8081")
    @RequestMapping("/calculator/{expression}")
    public void index(@PathVariable String expression) {
        //TODO
    }
}
