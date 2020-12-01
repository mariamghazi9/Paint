package quadcore.paintproject.paint.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import quadcore.paintproject.paint.model.app.Action;
import quadcore.paintproject.paint.model.app.Shape;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@CrossOrigin
@RestController
public class Controller {

    Service service = new Service();

    @RequestMapping(value = "/addShape", method = RequestMethod.POST)
    public int add(@RequestBody Map<String, Object> shape) {
        return service.addShape(shape);
    }

    @RequestMapping(value = "/deleteShape", method = RequestMethod.DELETE)
    public void deleteShape(@RequestParam int id) {
        service.deleteShape(id);
    }

    @RequestMapping(value = "/editShape", method = RequestMethod.POST)
    public void editShape(@RequestBody Map<String, Object> shape) {
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

    // TODO check save is working
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ResponseEntity<byte[]> save(@RequestParam String type) {
        File file = service.save(type);

        byte[] arr;
        try {
            arr = Files.readAllBytes(Paths.get(String.valueOf(file.toPath())));
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }

        if (!file.delete())
            System.out.println("Could not delete file");

        return ResponseEntity.ok().contentLength(arr.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName()).body(arr);
    }

    // TODO load
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public List<Shape> load(@RequestPart(name = "file") MultipartFile multipartFile, @RequestPart(name = "ext") String ext) {
        System.out.println(ext);
        InputStream initialStream;
        File targetFile;
        try {
            initialStream = multipartFile.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            if(initialStream.read(buffer) == -1) throw new RuntimeException("Empty File");
            System.out.println(initialStream);
            targetFile = new File("targetFile.tmp");
            if (!targetFile.createNewFile()) throw new RuntimeException("Target File Cannot Be Created");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.close();
            Scanner myReader = new Scanner(targetFile);
            StringBuilder sb = new StringBuilder();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
              sb.append(data);
            }
            myReader.close();
            if(!targetFile.delete()) System.out.println("Could not delete file");
            return service.load(sb.toString(), ext);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/setName", method = RequestMethod.POST)
    public void setName(@RequestParam String canvasName) {
        service.setCanvasName(canvasName);
    }
}
