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
						"C:/Users/Lukasz/workspace/proze1/reddot.jpg"));
			} catch (IOException e) {
			}
			break;
		case GREEN:

			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/greendot.jpg"));
			} catch (IOException e) {
			}
			break;
		case YELLOW:
			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/yellowdot.jpg"));
			} catch (IOException e) {
			}
			break;
		case BLUE:
			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/bluedot.jpg"));
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
