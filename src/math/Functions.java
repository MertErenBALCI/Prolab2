package math;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Functions {
	public static double map(double val, double start1, double stop1, double start2, double stop2) {
		double value = val - start1;
		
		double d1 = stop1 - start1;
		double d2 = stop2 - start2;
		
		value /= d1;
		value *= d2;
		
		return value + start2;
	}
	
	public static BufferedImage rotatedImage(BufferedImage image, double angle) {
		angle = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(angle, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		return op.filter(image, null);
	}
}
