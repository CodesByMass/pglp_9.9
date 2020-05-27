package formes;

/**
 * Position sur une espace à deux dimensions x et y.
 *
 * @author Mass'
 *
 */
public class Position2D {

  int x;
  int y;


  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }


  public int getX() {
    return x;
  }


  public void setX(int x) {
    this.x = x;
  }


  public int getY() {
    return y;
  }


  public void setY(int y) {
    this.y = y;
  }


  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  /**
   * Utilisé pour la méthode equals.
   * 
   * @see Position2D#equals(Object)
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  /**
   * Comparaison utile en cas de formes sur la même posiiton.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Position2D other = (Position2D) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }


}
