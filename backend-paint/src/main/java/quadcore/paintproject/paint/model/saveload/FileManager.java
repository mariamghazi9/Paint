package quadcore.paintproject.paint.model.saveload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import quadcore.paintproject.paint.model.app.Canvas;

import java.io.*;

public class FileManager {

    public File saveAsXML(Canvas canvas) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        File file = new File(canvas.getName() + ".xml");
        objectMapper.writeValue(file, canvas);
        return file;
    }

    public Canvas loadXML(String file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(file, Canvas.class);
    }

    public File saveAsJson(Canvas canvas) throws IOException {
        ObjectMapper objectMapper = new JsonMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        File file = new File(canvas.getName() + ".json");
        objectMapper.writeValue(file, canvas);
        return file;
    }

    public Canvas loadJson(String file) throws IOException {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.readValue(file, Canvas.class);
    }

}
