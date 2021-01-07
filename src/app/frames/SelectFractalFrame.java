package app.frames;

import app.fractals.JuliaSet;
import app.fractals.MandelbrotSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for options with selecting fractals.
 */
public class SelectFractalFrame extends JFrame {

    /** Used texts */
    private static final String FRAME_NAME = "Settings";
    private static final String TEXT_FRACTAL = "Fractal:";

    private final JComboBox<String> fractalsToChoose = new JComboBox();

    public SelectFractalFrame() {
        // frame main properties
        super( "Select Fractal" );
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        setSize(500, 200);
        setLocation(50,50);
        setResizable(false);

        // frame layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Select fractal info
        JLabel rotorIdentifier = new JLabel(TEXT_FRACTAL);
        rotorIdentifier.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy=0; gbc.gridx=20;
        add(rotorIdentifier, gbc);

        // Fractals options
        Dimension d = fractalsToChoose.getPreferredSize();
        fractalsToChoose.setPreferredSize(new Dimension(100, d.height));
        fractalsToChoose.addItem("Julia Set");
        fractalsToChoose.addItem("Mandelbrot");
        fractalsToChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox jcombobx = (JComboBox) actionEvent.getSource();
                int n = jcombobx.getSelectedIndex();
                Board board = Board.getInstance();
                switch(n) {
                    case 1: board.setFractal(new JuliaSet());
                    case 2: board.setFractal(new MandelbrotSet());
                    default:
                }
            }
        });
        gbc.gridx = 40;
        add(fractalsToChoose, gbc);
        /*JLabel label = new JLabel();
        label.setText("<html> The default fractal is <b>Julia Set</b>.</br> " +
                "It contains all complex numbers <number>p</number> that satisfy the equation:<br/>" +
                "<code>z_{n+1} = z_{n}^{2} + c, z_{0} = p and {zn} has its limit.<br/></code>" +
                "Variable <number>c<number> is an arbitrary number you can play with.</html>");
        add(label);*/
    }
}
