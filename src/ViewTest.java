import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
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
	void createButtonsTest() {
		Controller c = new Controller();
		View v = c.getView();
		HashMap<String,JButton> buttons = v.getButtons();
		assertTrue(buttons.containsKey("OButton"));
		assertTrue(buttons.containsKey("NHButton"));
		assertTrue(buttons.containsKey("OPlanButton"));
		assertTrue(buttons.containsKey("NHPlanButton"));
		assertTrue(buttons.containsKey("doneAnimationButton"));
		assertTrue(buttons.containsKey("saveGameButtonO"));
		assertTrue(buttons.containsKey("saveGameButtonNH"));
		assertTrue(buttons.containsKey("reloadGameButton"));
		assertTrue(buttons.containsKey("endTutorial"));
		assertTrue(buttons.containsKey("tutorial"));
	}
	
	@Test
	void setUpFrameTest() {
		Controller c = new Controller();
		View v = c.getView();
		v.setUpFrame();
		
		assertTrue(v.getFrame().isFocusable());
		assertEquals(v.getFrame().getBackground(), Color.gray);
		assertEquals(v.getFrame().getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
		assertFalse(v.getFrame().isResizable());
		assertTrue(v.getFrame().isVisible());
		assertTrue(v.getFrame().isDisplayable());
	}
	
	@Test
	void setUpOPanelTest() {
		Controller c = new Controller();
		View v = c.getView();
		v.setUpOPanel();
		JButton save = v.getButtons().get("saveGameButtonO");
		JButton noStam = v.getButtons().get("noStaminaO");
		
		assertTrue(v.getOPanel().getBackground().equals(Color.gray));
		assertTrue(save.getBounds().equals(new Rectangle(910,0,100,30)));
		assertTrue(noStam.getFont().getName().equals("Verdana"));
		assertEquals(noStam.getFont().getStyle(), Font.BOLD);
		assertEquals(noStam.getFont().getSize(), 50);
		assertTrue(noStam.getBounds().equals(new Rectangle(400,500,1120,80)));
	}
	
	@Test
	void setUpNHPanelTest() {
		Controller c = new Controller();
		View v = c.getView();
		v.setUpNHPanel();
		JButton save = v.getButtons().get("saveGameButtonNH");
		JButton noStam = v.getButtons().get("noStaminaNH");
		
		assertTrue(v.getOPanel().getBackground().equals(Color.gray));
		assertTrue(save.getBounds().equals(new Rectangle(910,0,100,30)));
		assertTrue(noStam.getFont().getName().equals("Verdana"));
		assertEquals(noStam.getFont().getStyle(), Font.BOLD);
		assertEquals(noStam.getFont().getSize(), 50);
		assertTrue(noStam.getBounds().equals(new Rectangle(400,500,1120,80)));
	}

	@Test
	void updateViewTest() {
		Controller c = new Controller();
		View v = c.getView();
		Bird b = new Bird(0, 0, 0 ,0, "");
		List<GameElement> e = new ArrayList<GameElement>();
		GameElement g = new Obstacle(0, 0, 0, 0, "", Images.FOX);
		e.add(g);
		MiniMap m = new MiniMap(0,0,0,0,Images.OSPREY_MINIMAP, Images.OSPREY_IMG_FOR_MINIMAP, 0, 0);
		Background bg = new Background(100);
		v.setScore(new JLabel("score"));
		v.updateView(b, e, m, bg);
		
		assertTrue(v.getBird().getImage().equals(Images.BIRD));
		assertTrue(v.getBird().getStaminaPics()[0].equals(Images.HEALTH_0));
		assertTrue(v.getBird().getPoweredUpPics().equals(Images.POWERUP));
		assertTrue(v.getScore().getFont().getName().equals("Verdana"));
		assertTrue(v.getScore().getBounds().equals(new Rectangle(0,0,1300,100)));
	}

	@Test
	void createImageTest() {
		//Controller c = new Controller();
		View view = new View();
		BufferedImage i = view.createImage("images/test-image.jpg");
		assertTrue(i != null);
	}

	@Test
	void drawImageTest() {
		//Controller c = new Controller();
		View v = new View();
		v.drawImage();
		fail("Stub method");
	}

	@Test
	void displayQuizTest() {
		// before method call
		//Controller c1 = new Controller();
		View v1 = new View();
		List<JButton> l1 = new ArrayList<JButton>();
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		l1.add(b1);
		l1.add(b2);
		List<String> answers = new ArrayList<String>();
		answers.add("a");
		answers.add("b");
		QuizQuestion q1 = new QuizQuestion("What?", answers, "a", "");

		// after method call
		//Controller c2 = new Controller();
		View v2 = new View();
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
		//Controller c = new Controller();
		View v = new View();
		NestAnimation nestAnimation = new NestAnimation();
		v.nestAnimationUpdate(nestAnimation);
		fail("Stub method");
	}

	@Test
	void updateBirdTest() {
		//Controller c = new Controller();
		View v = new View();
		v.updateBird();
		fail("Stub method");
	}

	@Test
	void updateCollidablesTest() {
		//Controller c = new Controller();
		Model m = new Model(1000, 1000);
		View v = new View();
		v.updateCollidables(m.getBird());
		fail("Stub method");
	}

	@Test
	void updateMiniMapTest() {
		//Controller c = new Controller();
		View v = new View();
		Model m = new Model(1000, 1000);
		v.updateMiniMap(m.getMiniMap());
		fail("Stub method");
	}

	@Test
	void updateBackgroundTest() {
		//Controller c = new Controller();
		View v = new View();
		v.updateBackground();
		fail("Stub method");
	}

	@Test
	void setPanelTest() {
		//Controller c = new Controller();
		View v = new View();
		v.setPanel("O");
		assertEquals(v.getCurrentPanel(), v.getOPanel());
		v.setPanel("NH");
		assertEquals(v.getCurrentPanel(), v.getNHPanel());
	}

	@Test
	void frameTest() {
		//Controller c = new Controller();
		View v = new View();
		JFrame f = new JFrame();
		v.setFrame(f);
		assertEquals(v.getFrame(), f);
	}
}
