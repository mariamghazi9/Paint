package quadcore.paintproject.paint.model.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import quadcore.paintproject.paint.model.saveload.FileManager;

import java.io.File;
import java.io.IOException;


public class App {
    static private App instance;
    private Canvas canvas;
    private FileManager fileManager = new FileManager();

    private App() {
    }

    public static App getInstance() {
        if (instance == null) instance = new App();
        return instance;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Canvas load() {
        //TODO
        this.canvas = new Canvas();
        return this.canvas;
    }

    /**
     * do not forget to delete file from server after sending to front-end
     * @param type either xml or json; the format in which the canvas is to be saved
     * @return file created in specified format
     */
    public File save(String type) throws IOException {
        if (type.equalsIgnoreCase("xml")) {
            return fileManager.saveAsXML(this.canvas);
        } else {
            return null;
        }
    }
}
