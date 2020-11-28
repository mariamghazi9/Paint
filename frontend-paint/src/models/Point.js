class Point {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

  isInRange(contextWidth, contextHeight) {
    if (this.x > contextWidth || this.y > contextHeight) return false;
    if (this.x < 0 || this.y < 0) return false;
    return true;
  }
}
export default Point;
