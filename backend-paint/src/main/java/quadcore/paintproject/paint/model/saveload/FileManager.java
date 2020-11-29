package quadcore.paintproject.paint.model.saveload;


import com.fasterxml.jackson.core.JsonProcessingException;
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

    public Canvas loadXML(File file) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        return xmlMapper.readValue(xml, Canvas.class);
    }

    public File saveAsJson(Canvas canvas) throws IOException {
        ObjectMapper objectMapper = new JsonMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        File file = new File(canvas.getName() + ".json");
        objectMapper.writeValue(file, canvas);
        return file;
    }

    public Canvas loadJson(File file) throws IOException {
        JsonMapper jsonMapper = new JsonMapper();
        String json = inputStreamToString(new FileInputStream(file));
        return jsonMapper.readValue(json, Canvas.class);
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
