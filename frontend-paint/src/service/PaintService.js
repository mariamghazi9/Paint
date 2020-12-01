import axios from "axios";

const API_URL = "http://localhost:9000";
class PaintService {
  /**
   * @param shape to be created
   * @returns id of the new created shape
   */
  addShape(shape) {
    return axios.post(API_URL + "/addShape", shape);
  }

  /**
   * @param id of shape to be deleted
   * @returns nothing
   */
  deleteShape(id) {
    return axios.delete(API_URL + "/deleteShape", {
      params: {
        id: id
      }
    });
  }

  /**
   * @param shape to be edited
   * @returns nothing
   */
  editShape(shape) {
    console.log(shape.x, shape.y);
    return axios.post(API_URL + "/editShape", shape);
  }

  /**
   *
   * @param id of shape to be copied
   * @returns the new created shape
   */
  copyShape(id) {
    return axios.get(API_URL + "/copyShape", {
      params: {
        id: id
      }
    });
  }

  /**
   * @returns Action object
   */
  undoRedo(isUndo) {
    if (isUndo) {
      return axios.get(API_URL + "/undo");
    } else {
      return axios.get(API_URL + "/redo");
    }
  }

  /**
   * overwrites the current canvas in back-end. It is better to prompt the user if they have any unsaved changes.
   * @returns nothing
   */
  createCanvas() {
    return axios.post(API_URL + "/createCanvas");
  }

  /**
   * @param name of canvas
   * @returns nothing
   */
  setCanvasName(name) {
    return axios.post(API_URL + "/setName", null, {
      params: {
        canvasName: name
      }
    });
  }

  /**
   * @param type of file to be saved: xml or json
   * @returns The File
   */
  save(type) {
    let i = document.createElement("iframe");
    i.style.display = "none";
    i.onload = function() {
      i.parentNode.removeChild(i);
    };
    i.src = "http://localhost:9000/save?type=" + type;
    let a = document.createElement("a");
    a.href = i.src;
    a.download = "file";
    a.appendChild(i);
    document.body.appendChild(a);
  }
  //TODO load
  /**
   * @param file to be deserialized
   * @returns List of Shapes
   */
  load(formData) {
    return axios.post(API_URL + "/load", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    });
  }
}

export default new PaintService();
