import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import formes.Carre;
import formes.Forme;
import formes.Position2D;

public class FormeTest {

  Forme f = null;

  @Before
  public void setUp() throws Exception {
    f = new Carre("carre", new Position2D(1, 1), 5);
  }

  @Test
  public void testGetX() {
    assertTrue(((Carre) f).getPosition().getX() == 1);
  }

  @Test
  public void testGetY() {
    assertTrue(((Carre) f).getPosition().getY() == 1);
  }

  @Test
  public void testGetName() {
    assertTrue(f.getName() == "carre");
  }

  @Test
  public void testMove() {
    f.move(2, 2);
    assertTrue(((Carre) f).getPosition().equals(new Position2D(3, 3)));
  }

}
