import java.awt.image.BufferedImage;

public class NestAnimation {
	private int birdx;
	private int birdy;
	private int xvel;
	private int yvel;
	private int endx;
	private int endy;
	private BufferedImage bird;
	private BufferedImage background;
	private boolean doneAnimation;
	
	NestAnimation(){
		birdx = 0;
		birdy = 0;
		endx = 1350;
		endy = 400;
		xvel = 9;
		yvel = 2;
		bird = null;
		setBackground(null);
		doneAnimation = false;
	}
	
	void animationUpdate() {
		if(bird!=null && !doneAnimation) {
			if(birdx<(endx-bird.getWidth()/2)) {
				birdx+=xvel;
			}
			if(birdy<(endy-bird.getHeight()/2)) {
				birdy+=yvel;
			}
			if(birdx>=(endx-bird.getWidth()/2) && birdy>=(endy-bird.getHeight()/2)){
				doneAnimation = true;
			}
		}
		
		
	}

	/**
	 * @return the birdx
	 */
	public int getBirdx() {
		return birdx;
	}

	/**
	 * @param birdx the birdx to set
	 */
	public void setBirdx(int birdx) {
		this.birdx = birdx;
	}

	/**
	 * @return the birdy
	 */
	public int getBirdy() {
		return birdy;
	}

	/**
	 * @param birdy the birdy to set
	 */
	public void setBirdy(int birdy) {
		this.birdy = birdy;
	}

	/**
	 * @return the xvel
	 */
	public int getXvel() {
		return xvel;
	}

	/**
	 * @param xvel the xvel to set
	 */
	public void setXvel(int xvel) {
		this.xvel = xvel;
	}

	/**
	 * @return the yvel
	 */
	public int getYvel() {
		return yvel;
	}

	/**
	 * @param yvel the yvel to set
	 */
	public void setYvel(int yvel) {
		this.yvel = yvel;
	}

	/**
	 * @return the bird
	 */
	public BufferedImage getBird() {
		return bird;
	}

	/**
	 * @param bird the bird to set
	 */
	public void setBird(BufferedImage bird) {
		this.bird = bird;
	}

	/**
	 * @return the background
	 */
	public BufferedImage getBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	/**
	 * @return the endx
	 */
	public int getEndx() {
		return endx;
	}

	/**
	 * @param endx the endx to set
	 */
	public void setEndx(int endx) {
		this.endx = endx;
	}

	/**
	 * @return the endy
	 */
	public int getEndy() {
		return endy;
	}

	/**
	 * @param endy the endy to set
	 */
	public void setEndy(int endy) {
		this.endy = endy;
	}

	/**
	 * @return the doneAnimation
	 */
	public boolean isDoneAnimation() {
		return doneAnimation;
	}

	/**
	 * @param doneAnimation the doneAnimation to set
	 */
	public void setDoneAnimation(boolean doneAnimation) {
		this.doneAnimation = doneAnimation;
	}
}
