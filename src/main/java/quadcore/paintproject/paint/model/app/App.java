package quadcore.paintproject.paint.model.app;

import quadcore.paintproject.paint.model.saveload.FileManager;

import java.io.File;


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

    public Canvas load(File file) {
        //TODO
        return new Canvas();
    }

    /**
     * do not forget to delete file from server after sending to front-end
     * @param name file name
     * @return file created in specified format
     */
    public File save(String name) {
        //TODO
        return new File("");
    }
}
