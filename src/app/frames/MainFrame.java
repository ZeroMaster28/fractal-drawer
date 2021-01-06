package app.frames;

import app.PropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class MainFrame extends JFrame {

    private static final String APP_NAME = "Fractal-Drawer 1.0";

    private static final String PROP_KEY_FRAME_X = "frame_size_x";
    private static final String PROP_KEY_FRAME_Y = "frame_size_y";
    private static final String PROP_KEY_BOARD_X = "board_size_x";
    private static final String PROP_KEY_BOARD_Y = "board_size_y";
    private static final String PROP_KEY_LOC_X = "location_x";
    private static final String PROP_KEY_LOC_Y = "location_y";

    private final Map<String, String> frameProperties = new PropertiesLoader().load("frame.properties");

    public MainFrame() {
        super(APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JLabel(), BorderLayout.CENTER);

        // menu bar and its items
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.add(new JMenuItem(new AbstractAction("Select Fractal") {
            public void actionPerformed(ActionEvent e) {
                new SelectFractalFrame().setVisible(true);
            }
        }));
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // board for fractals drawing
        Board board = new Board(Integer.parseInt(frameProperties.get(PROP_KEY_BOARD_X)),
                Integer.parseInt(frameProperties.get(PROP_KEY_BOARD_Y)));
        board.setCoordinateX(Integer.parseInt(frameProperties.get(PROP_KEY_LOC_X)));
        board.setCoordinateY(Integer.parseInt(frameProperties.get(PROP_KEY_LOC_Y)));
        board.fillCanvas();
        add(board);

        // other options
        pack();
        setResizable(false);
        setSize(new Dimension(Integer.parseInt(frameProperties.get(PROP_KEY_FRAME_X)),
                Integer.parseInt(frameProperties.get(PROP_KEY_FRAME_Y))));
    }
}
