package app.frames;

import javax.swing.*;

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
        JLabel label = new JLabel();
        label.setText("<html> The default fractal is <b>Julia Set</b>.</br> " +
                "It contains all complex numbers <number>p</number> that satisfy the equation:<br/>" +
                "<code>z_{n+1} = z_{n}^{2} + c, z_{0} = p and {zn} has its limit.<br/></code>" +
                "Variable <number>c<number> is an arbitrary number you can play with.</html>");
        add(label);
    }
}
