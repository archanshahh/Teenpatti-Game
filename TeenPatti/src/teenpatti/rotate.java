import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import teenpatti.Game;

public class rotate {

    public static void main(String args[]) throws Exception {
        Game frame = new Game();
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final BufferedImage bi = ImageIO.read(new File("ace4.png"));
        
        frame.add(new JLabel(){

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(bi.getWidth(), bi.getHeight());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.rotate(-60, bi.getWidth() / 2, bi.getHeight() / 2);
                g2.drawImage(bi, 0, 0, null);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}


