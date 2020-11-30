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
  undo() {
    return axios.get(API_URL + "/undo");
  }

  /**
   * @returns Action object
   */
  redo() {
    return axios.get(API_URL + "/redo");
  }

  /**
   * overwrites the current canvas in back-end. It is better to prompt the user if they have any unsaved changes.
   * @returns nothing
   */
  createCanvas() {
    return axios.post(API_URL + "/createCanvas");
  }

  /**
   * calls createCanvas();
   * @returns nothing
   */
  deleteCanvas() {
    return this.createCanvas();
  }

  /**
   * @param type of file to be saved: xml or json
   * @returns The File
   */
  save(type) {
    return axios.get(API_URL + "/save", {
      params: {
        type: type
      }
    });
  }
  //TODO load
  /**
   * @param file to be deserialized
   * @returns List of Shapes
   */
  load(file) {
    return axios.get(API_URL + "/load", {
      params: {
        file: file
      }
    });
  }
}

export default new PaintService();