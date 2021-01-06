package app.frames;

import app.fractals.ComplexNumber;
import app.fractals.JuliaSet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board extends JPanel {

    /** Whether point colorization should based on how well this point belongs
     * to the given fractal set */
    private final boolean USE_WITH_ACCEPTANCE = true;

    private final BufferedImage canvas;
    private int coordinateX;
    private int coordinateY;

    public Board(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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
        JuliaSet juliaSet = new JuliaSet(new ComplexNumber(-0.8, 0.156));
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
                    color = new Color((int) (255*(1-juliaSet.getPointAcceptanceRatio(cn))), 0, 0);
                } else {
                    color = juliaSet.hasPoint(cn)? Color.BLACK: Color.RED;
                }
                canvas.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }
}
