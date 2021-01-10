package app.frames;

import app.PropertiesLoader;
import app.fractals.ComplexNumber;
import app.fractals.Fractal;
import app.fractals.JuliaSet;
import app.threads.ForkDrawFractal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

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

    /** Image variables */
    private final BufferedImage canvas;
    private Fractal fractal = new JuliaSet();
    private int coordinateX;
    private int coordinateY;
    private double imageScale = 1.5;

    /** Performance-like variables */
    private int drawingProcNumber = 1;

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

    public void setImageScale(double imageScale) {
        this.imageScale = imageScale;
        fillCanvas();
    }

    public void setDrawingProcNumber(int drawingProcNumber) {
        this.drawingProcNumber = drawingProcNumber;
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
        int rows = canvas.getWidth()-coordinateX, columns = canvas.getHeight()-coordinateY;
        ComplexNumber[][] complexPlane = new ComplexNumber[rows][columns];
        // creating complex plane for the input
        for (int x = coordinateX; x < canvas.getWidth(); x++) {
            for (int y = coordinateY; y < canvas.getHeight(); y++) {
                // transforming pixels to coordinates
                int dimx = canvas.getWidth()/2;
                int dimy = canvas.getWidth()/2;
                double dx = imageScale*(x - dimx)/ (double) dimx;
                double dy = imageScale*(dimy - y)/ (double) dimy;
                complexPlane[x][y] = new ComplexNumber(dx, dy);
            }
        }
        double[][] resultImage = new double[rows][columns];
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ForkDrawFractal(complexPlane, fractal, drawingProcNumber, resultImage));
        // drawing results
        for (int x = coordinateX; x < canvas.getWidth(); x++) {
            for (int y = coordinateY; y < canvas.getHeight(); y++) {
                Color color;
                if(USE_WITH_ACCEPTANCE) {
                    color = new Color((int) (255*(1-resultImage[x-coordinateX][y-coordinateY])), 0, 0);
                } else {
                    color = resultImage[x-coordinateX][y-coordinateY] >= 0.99? Color.BLACK: Color.RED;
                }
                canvas.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }
}
