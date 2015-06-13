import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ball {

	private int ballIndex;
	private BufferedImage img = null;

	Ball() {

		Random rand = new Random();
		ballIndex = rand.nextInt(4) + 1;
		switch (ballIndex) {
		case 1:
			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/reddot.jpg"));
			} catch (IOException e) {
			}
			break;
		case 2:

			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/greendot.jpg"));
			} catch (IOException e) {
			}
			break;
		case 3:
			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/yellowdot.jpg"));
			} catch (IOException e) {
			}
			break;
		case 4:
			try {
				img = ImageIO.read(new File(
						"C:/Users/Lukasz/workspace/proze1/bluedot.jpg"));
			} catch (IOException e) {
			}
		}

	}

	public BufferedImage getImg() {
		return img;
	}

	public int getBallIndex() {
		return ballIndex;
	}

}
