package com.imagetriplets.components;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class TImage extends JLabel {
	static int w = 360;
	static int h = 360;

	private String filename = null;
	
	public TImage(String filename) {
		super();
		setFilename(filename);
        setMinimumSize(new Dimension(w, h));
        setPreferredSize(new Dimension(w, h));
        setMaximumSize(new Dimension(w, h));
        setIcon(null);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		if (this.filename != null) {
			setIcon(scaleImage(loadImage().getImage(), w, h));
		} else {
			setIcon(null);
		}
	}

    private ImageIcon loadImage() {
        if (filename != null) {
        	Path filePath = Paths.get("src", "images", filename);
        	return new ImageIcon(filePath.toString());
        }
        return null;
    }
    
    private ImageIcon scaleImage(Image image, int newWidth, int newHeight) {
    	// Make sure the aspect ratio is maintained, so the image is not distorted
    	double thumbRatio = (double) newWidth / (double) newHeight;
    	int imageWidth = image.getWidth(null);
    	int imageHeight = image.getHeight(null);
    	double aspectRatio = (double) imageWidth / (double) imageHeight;

    	if (thumbRatio < aspectRatio) {
    		newHeight = (int) (newWidth / aspectRatio);
    	} else {
    		newWidth = (int) (newHeight * aspectRatio);
    	}
    	BufferedImage newImage = new BufferedImage(newWidth, newHeight,
    			BufferedImage.TYPE_INT_RGB);
    	Graphics2D graphics2D = newImage.createGraphics();
    	graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    			RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
    	graphics2D.setStroke(new BasicStroke(3F));    	

    	graphics2D.dispose();    	
    	
    	return new ImageIcon(newImage);
    }
    
    public boolean isSet() {
    	if ((filename == null) || (filename.equals(""))) {
    		return false;
    	}
    	return true;
    }
   
}
