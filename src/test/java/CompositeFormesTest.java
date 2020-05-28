import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import formes.Carre;
import formes.CompositeFormes;
import formes.Forme;
import formes.Position2D;
import org.junit.Before;
import org.junit.Test;

public class CompositeFormesTest {
  CompositeFormes cf = null;
  Forme f1;
  Forme f2;

  @Before
  public void setUp() throws Exception {
    cf = new CompositeFormes("groupe");
    f1 = new Carre("carre1", new Position2D(1, 1), 5);
  }

  @Test
  public void testGetNom() {
    assertTrue(cf.getName() == "groupe");
  }

  @Test
  public void testAdd() {
    cf.add(f1);
    assertTrue(cf.getChildsFormes().contains(f1));
  }

  @Test
  public void testDelete() {
    cf.add(f1);
    cf.delete(f1);
    assertFalse(cf.getChildsFormes().contains(f1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddException() {
    cf.add(f2);
  }


}
