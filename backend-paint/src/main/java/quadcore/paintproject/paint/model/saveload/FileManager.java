package quadcore.paintproject.paint.model.saveload;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import quadcore.paintproject.paint.model.app.Canvas;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public File saveAsXML(Canvas canvas) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        File file = new File(canvas.getName() + ".xml");
        objectMapper.writeValue(file, canvas);
        return file;
    }
}
