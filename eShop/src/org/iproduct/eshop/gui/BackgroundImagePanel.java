package org.iproduct.eshop.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel {
	private BufferedImage background;

	public BackgroundImagePanel(String imageFileName) {
		try {
			background = ImageIO.read(new File(imageFileName));
		} catch (IOException e) {
			System.err.println("Picture not available.");
		}

	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		int maxWidth = getWidth();
		int maxHeight = getHeight();
		
		// show background picture
		RescaleOp op = new RescaleOp(4f, 2f, new RenderingHints(
				RenderingHints.KEY_ANTIALIASING, true));
		
		float ninth = 1.0f / 100.0f;
		float[] blurKernel = new float[100];
		Arrays.fill(blurKernel, ninth);
		BufferedImageOp blur = new ConvolveOp(new Kernel(10, 10, blurKernel));
		
		float[] edgeKernel = { 0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f,
				-1.0f, 0.0f };
		BufferedImageOp edge = new ConvolveOp(new Kernel(3, 3, edgeKernel));
		AffineTransformOp aop = new AffineTransformOp(new AffineTransform(
				((float) maxWidth) / background.getWidth(), 0, 0,
				((float) maxHeight) / background.getHeight(), 0, 0),
				AffineTransformOp.TYPE_BICUBIC);
		
//		g2.drawImage(op.filter(blur.filter(background, null),null), aop, 0, 0);
		g2.drawImage(background, aop, 0, 0);
	}

}
