package app.frames;

import javax.swing.JFrame;

/**
 * Class for options with selecting fractals.
 */
public class SelectFractalFrame extends JFrame {

    public SelectFractalFrame() {
        super( "Select Fractal" );
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        setSize(500, 200);
        setLocation(50,50);
        setResizable(false);
    }
}
