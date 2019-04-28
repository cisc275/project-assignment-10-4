import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import com.sun.prism.paint.Color;

/**
 * Handles all unit tests of View class
 * @author jhdavis
 *
 */
class ViewTest {

	@Test
	void updateViewTest() {
		BufferedImage b1 = null;
		try {
			b1 = ImageIO.read(new File("images/bird.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("images/big_bird_animate.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//to be updated
		Controller c = new Controller();
		View v = c.getView();
		v.setGameBackground(new Background(1000));
		Bird b = new Bird(0, 0, 1, 1, "images/big_bird_animate.png");
		v.setBird(b);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 =  new Obstacle(1,100,100,1,0,"");
		g1.setImagePath("images/big_bird_animate.png");
		GameElement g2 =  new Obstacle(1,500,100,1,0,"");
		g2.setImage(bi);
		list.add(g1);
		list.add(g2);
		v.setElements(list);
		//what it should look like
		Controller c1 = new Controller();
		View v1 = c.getView();
		v.setGameBackground(new Background(1500));
		Bird ba = new Bird(0, 0, 1, 1, "images/big_bird_animate.png");
		ba.setImage(bi);
		v1.setBird(ba);
		List<GameElement> list1 = new ArrayList<GameElement>();
		GameElement g1a =  new Obstacle(1,100,100,1,0,"");
		g1a.setImage(bi);
		g1a.setImagePath("images/big_bird_animate.png");
		GameElement g2a =  new Obstacle(1,500,100,1,0,"");
		g2a.setImage(bi);
		list1.add(g1a);
		list1.add(g2a);
		v.setElements(list1);
		
		v.updateView(b, list, new MiniMap(5,5,5,5,"images/big_bird_animate.png"), new Background(1500));
		
		assertEquals(v.getBird().getImage(), v1.getBird().getImage());
		assertEquals(v.getBackground(), v1.getBackground());
		assertEquals(v.getElements().get(0).getImage(),v1.getElements().get(0).getImage());

	}

	@Test
	void createImageTest() {
		Controller c = new Controller();
		View view = new View(c);
		BufferedImage i = view.createImage("images/test-image.jpg");
		assertTrue(i != null);
	}

	@Test
	void drawImageTest() {
		fail("Not yet implemented");
	}

	@Test
	void displayQuizTest() {
		fail("Not yet implemented");
	}

	@Test
	void nestAnimationTest() {
		fail("Not yet implemented");
	}

	@Test
	void selectBirdTest() {
		View view = new View(new Controller());
		assertTrue(view.selectBirdType() == 1 || view.selectBirdType() == 0);
	}

	@Test
	void updateBirdTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateCollidablesTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateMiniMapTest() {
		fail("Not yet implemented");
	}

	@Test
	void updateBackgroundTest() {
		fail("Not yet implemented");
	}

}
