import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	void setPanelTest() {
		//Controller c = new Controller();
		View v = new View();
		v.setPanel("O");
		assertEquals(v.getCurrentPanel(), v.getOPanel());
		v.setPanel("NH");
		assertEquals(v.getCurrentPanel(), v.getNHPanel());
		v.setPanel("NA");
		assertEquals(v.getCurrentPanel(), v.getNestAnimationPanel());
		v.setPanel("OP");
		assertEquals(v.getCurrentPanel(), v.getOspreyPlan());
		v.setPanel("NHP");
		assertEquals(v.getCurrentPanel(), v.getNHPlan());
		v.setPanel("B");
		assertEquals(v.getCurrentPanel(), v.getbuttonPanel());
		v.setPanel("TP");
		assertEquals(v.getCurrentPanel(), v.getTutorialPanel());
		v.setPanel("WP");
		assertEquals(v.getCurrentPanel(), v.getWelcomePanel());
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
