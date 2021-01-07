package app.frames;

import app.PropertiesLoader;
import app.fractals.ComplexNumber;
import app.fractals.Fractal;
import app.fractals.JuliaSet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/** Singleton class representing board that is responsible for drawing fractals */
public class Board extends JPanel {

    /** Properties keys for board size */
    private static final String PROP_KEY_BOARD_X = "board_size_x";
    private static final String PROP_KEY_BOARD_Y = "board_size_y";

    /** Board instance */
    private static Board instance;

    /** Whether point colorization should be based on how well this point belongs
     * to the given fractal set */
    private final boolean USE_WITH_ACCEPTANCE = true;

    private final BufferedImage canvas;
    private Fractal fractal = new JuliaSet();
    private int coordinateX;
    private int coordinateY;

    public static Board getInstance() {
        if(instance == null) {
            Map<String, String> frameProperties = new PropertiesLoader().load("board.properties");
            instance = new Board(Integer.parseInt(frameProperties.get(PROP_KEY_BOARD_X)),
                    Integer.parseInt(frameProperties.get(PROP_KEY_BOARD_Y)));
        }
        return instance;
    }

    private Board(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void setFractal(Fractal fractal) {
        this.fractal = fractal;
        fillCanvas();
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public void fillCanvas() {
        for (int x = coordinateX; x < canvas.getWidth(); x++) {
            for (int y = coordinateY; y < canvas.getHeight(); y++) {
                // transforming pixels to coordinates
                int dimx = canvas.getWidth()/2;
                int dimy = canvas.getWidth()/2;
                double dx = 1.5*(x - dimx)/ (double) dimx;
                double dy = 1.5*(dimy - y)/ (double) dimy;
                ComplexNumber cn = new ComplexNumber(dx, dy);
                // colorizing point if it belongs to the fractal or even how 'well' it belongs to it
                Color color;
                if(USE_WITH_ACCEPTANCE) {
                    color = new Color((int) (255*(1-fractal.getPointAcceptanceRatio(cn))), 0, 0);
                } else {
                    color = fractal.hasPoint(cn)? Color.BLACK: Color.RED;
                }
                canvas.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }
}
