package app.frames;

import app.fractals.JuliaSet;
import app.fractals.MandelbrotSet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for options with selecting fractals.
 */
public class SelectFractalFrame extends JFrame {

    /** Used texts */
    private static final String FRAME_NAME = "Settings";
    private static final String TEXT_SCALE = "Image scale:";
    private static final String TEXT_FRACTAL = "Fractal:";
    private static final String TEXT_PROCESS = "Processes number:";

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

        // Scale image
        JLabel scaleInfo = new JLabel(TEXT_SCALE);
        scaleInfo.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy = 0; gbc.gridx = 20;
        add(scaleInfo, gbc);

        // Scale slider
        JSlider scale = new JSlider(JSlider.HORIZONTAL, 0, 20, 2);
        scale.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider slider = (JSlider) changeEvent.getSource();
                Board.getInstance().setImageScale(slider.getValue());
            }
        });
        scale.setMajorTickSpacing(10);
        scale.setMinorTickSpacing(1);
        scale.setPaintTicks(true);
        scale.setPaintLabels(true);
        gbc.gridy += 10;
        add(scale, gbc);

        // Select process number
        JLabel procNumLabel = new JLabel(TEXT_PROCESS);
        procNumLabel.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy += 20;
        add(procNumLabel, gbc);

        // Processes number options
        JComboBox<Integer> processesNumber = new JComboBox<>();
        Dimension dim = processesNumber.getPreferredSize();
        processesNumber.setPreferredSize(new Dimension(100, dim.height));
        for(int i=1; i<11; i++) {
             processesNumber.addItem(i);
        }
        processesNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox jcombobx = (JComboBox) actionEvent.getSource();
                int n = jcombobx.getSelectedIndex();
                Board.getInstance().setDrawingProcNumber(n+1);
            }
        });
        gbc.gridy += 20;
        add(processesNumber, gbc);

        // Select fractal info
        JLabel fractalName = new JLabel(TEXT_FRACTAL);
        fractalName.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy += 20;
        add(fractalName, gbc);

        // Fractals options
        Dimension d = fractalsToChoose.getPreferredSize();
        fractalsToChoose.setPreferredSize(new Dimension(100, d.height));
        fractalsToChoose.addItem("Julia Set");
        fractalsToChoose.addItem("Mandelbrot Set");
        fractalsToChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JComboBox jcombobx = (JComboBox) actionEvent.getSource();
                int n = jcombobx.getSelectedIndex();
                Board board = Board.getInstance();
                switch(n) {
                    case 0: board.setFractal(new JuliaSet()); break;
                    case 1: board.setFractal(new MandelbrotSet()); break;
                    default:
                }
            }
        });
        gbc.gridy += 20;
        add(fractalsToChoose, gbc);
        /*JLabel label = new JLabel();
        label.setText("<html> The default fractal is <b>Julia Set</b>.</br> " +
                "It contains all complex numbers <number>p</number> that satisfy the equation:<br/>" +
                "<code>z_{n+1} = z_{n}^{2} + c, z_{0} = p and {zn} has its limit.<br/></code>" +
                "Variable <number>c<number> is an arbitrary number you can play with.</html>");
        add(label);*/
    }
}
