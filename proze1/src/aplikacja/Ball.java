package aplikacja;
import info.BallType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ball {

	private BufferedImage img = null;

	Ball(BallType ballType) {

		switch (ballType) {
		case RED:
			try {
				img = ImageIO.read(new File(
						"reddot.jpg"));
			} catch (IOException e) {
			}
			break;
		case GREEN:

			try {
				img = ImageIO.read(new File(
						"greendot.jpg"));
			} catch (IOException e) {
			}
			break;
		case YELLOW:
			try {
				img = ImageIO.read(new File(
						"yellowdot.jpg"));
			} catch (IOException e) {
			}
			break;
		case BLUE:
			try {
				img = ImageIO.read(new File(
						"bluedot.jpg"));
			} catch (IOException e) {
			}
		case DESTROYED:
			break;
		}

	}

	public BufferedImage getImg() {
		return img;
	}

}
