package vn.DA_KNNN.Components;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class BackgroundPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            // Tải hình ảnh từ file
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vẽ hình ảnh lên background panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Vẽ hình ảnh cho toàn bộ kích thước panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

