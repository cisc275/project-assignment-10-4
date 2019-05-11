import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

/**
 * Handles all unit tests of View class
 * 
 * @author jhdavis
 */
class ViewTest {

	@Test
	void updateViewTest() {
		// TODO check that this still works with the new images system
		Controller c = new Controller();
		View v = c.getView();
		v.setGameBackground(new Background(1000));
		Bird b = new Bird(0, 0, 1, 1, "images/big_bird_animate.png");
		v.setBird(b);
		List<GameElement> list = new ArrayList<GameElement>();
		GameElement g1 = new Obstacle(100, 100, 1, 0, "", Images.EAGLE);
		g1.setImagePath("images/big_bird_animate.png");
		GameElement g2 = new Obstacle(500, 100, 1, 0, "", Images.FISH);
		g2.setImage(Images.BIRD);
		list.add(g1);
		list.add(g2);
		v.setElements(list);
		// what it should look like
		// Controller c1 = new Controller();
		View v1 = c.getView();
		v.setGameBackground(new Background(1500));
		Bird ba = new Bird(0, 0, 1, 1, "images/big_bird_animate.png");
		ba.setImage(Images.BIRD);
		v1.setBird(ba);
		List<GameElement> list1 = new ArrayList<GameElement>();
		GameElement g1a = new Obstacle(100, 100, 1, 0, "", Images.EAGLE);
		g1a.setImage(Images.BIRD);
		g1a.setImagePath("images/big_bird_animate.png");
		GameElement g2a = new Obstacle(500, 100, 1, 0, "", Images.FISH);
		g2a.setImage(Images.BIRD);
		list1.add(g1a);
		list1.add(g2a);
		v.setElements(list1);

		v.updateView(b, list, new MiniMap(5, 5, 5, 5, Images.BIRD, Images.OSPREY_MINIMAP, 0, 0), 
					new Background(1500));
		assertEquals(v.getBird().getImage(), v1.getBird().getImage());
		assertEquals(v.getBackground(), v1.getBackground());
		assertEquals(v.getElements().get(0).getImage(), v1.getElements().get(0).getImage());

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
		Controller c = new Controller();
		View v = new View(c);
		v.drawImage();
		fail("Stub method");
	}

	@Test
	void displayQuizTest() {
		// before method call
		Controller c1 = new Controller();
		View v1 = new View(c1);
		List<JButton> l1 = new ArrayList<JButton>();
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		l1.add(b1);
		l1.add(b2);
		List<String> answers = new ArrayList<String>();
		answers.add("a");
		answers.add("b");
		QuizQuestion q1 = new QuizQuestion("What?", answers, "a");

		// after method call
		Controller c2 = new Controller();
		View v2 = new View(c2);
		JPanel cd2 = new JPanel(new CardLayout());
		View.QuizPanel p2 = v2.getQuizPanel();
		p2.setBackground(Color.gray);
		Font f = new Font("Verdana", Font.BOLD, v2.getFrameHeight() / 50);
		Dimension d = new Dimension(v2.getFrameWidth() / 5, v2.getFrameHeight() / 5);
		JLabel t2 = new JLabel("What?");
		t2.setFont(f);
		t2.setPreferredSize(d);
		p2.add(t2);
		JButton b3 = new JButton();
		b3.setFont(f);
		b3.setPreferredSize(d);
		JButton b4 = new JButton();
		b4.setFont(f);
		b4.setPreferredSize(d);
		p2.add(b3);
		p2.add(b4);
		cd2.add(p2, "Q");
		v2.setQuizPanel(p2);
		v2.setCards(cd2);
		v2.setPanel("Q");

		v1.displayQuiz(q1, l1);

		assertEquals(v1.getQuizPanel().getComponent(1).getFont(), v2.getQuizPanel().getComponent(1).getFont());
		assertEquals(v1.getQuizPanel().getComponent(1).getName(), v2.getQuizPanel().getComponent(1).getName());

	}

	@Test
	void nestAnimationTest() {
		Controller c = new Controller();
		View v = new View(c);
		NestAnimation nestAnimation = new NestAnimation();
		v.nestAnimationUpdate(nestAnimation);
		fail("Stub method");
	}

	@Test
	void updateBirdTest() {
		Controller c = new Controller();
		View v = new View(c);
		v.updateBird();
		fail("Stub method");
	}

	@Test
	void updateCollidablesTest() {
		Controller c = new Controller();
		Model m = new Model(1000, 1000);
		View v = new View(c);
		v.updateCollidables(m.getBird());
		fail("Stub method");
	}

	@Test
	void updateMiniMapTest() {
		Controller c = new Controller();
		View v = new View(c);
		Model m = new Model(1000, 1000);
		v.updateMiniMap(m.getMiniMap());
		fail("Stub method");
	}

	@Test
	void updateBackgroundTest() {
		Controller c = new Controller();
		View v = new View(c);
		v.updateBackground();
		fail("Stub method");
	}

	@Test
	void setPanelTest() {
		Controller c = new Controller();
		View v = new View(c);
		v.setPanel("O");
		assertEquals(v.getCurrentPanel(), v.getOPanel());
		v.setPanel("NH");
		assertEquals(v.getCurrentPanel(), v.getNHPanel());
	}

	@Test
	void frameTest() {
		Controller c = new Controller();
		View v = new View(c);
		JFrame f = new JFrame();
		v.setFrame(f);
		assertEquals(v.getFrame(), f);
	}
}
