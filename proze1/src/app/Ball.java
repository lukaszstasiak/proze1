package app;
import info.BallType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ball {

	private BufferedImage img = null;
	private static BufferedImage red;
	private static BufferedImage green;
	private static BufferedImage blue;
	private static BufferedImage yellow;
	
	// Static init block
	static {
	try {
		red = ImageIO.read(new File("reddot.jpg"));
		green = ImageIO.read(new File("greendot.jpg"));
		blue = ImageIO.read(new File("yellowdot.jpg"));
		yellow = ImageIO.read(new File("bluedot.jpg"));
		}
		catch (IOException e) {
		}
	}
	Ball(BallType ballType) {

		switch (ballType) {
		case RED:
			img = red;
			break;
		case GREEN:
			img = green;
			break;
		case YELLOW:
			img = yellow;
			break;
		case BLUE:
			img = blue;
		case DESTROYED:
			break;
		}

	}

	public BufferedImage getImg() {
		return img;
	}

}
