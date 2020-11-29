package quadcore.paintproject.paint.api;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class Controller {
    
    @RequestMapping(value = "/addShape", method = RequestMethod.POST)
    public String index(@RequestBody Map<String, Object> data) {
        System.out.println(data.size());
        System.out.println(data.get("type"));
        System.out.println(data.get("shape"));
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> map = mapper.readValue(data.get("shape").toString(), Map.class);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
