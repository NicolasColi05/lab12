package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final Logics logics;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JButton push = new JButton(">");
        final JPanel canvas = new JPanel(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(width, width));
        canvas.add(panel, BorderLayout.CENTER);
        canvas.add(push, BorderLayout.SOUTH);
        this.getContentPane().add(canvas);
        push.addActionListener(v -> {
            for (final Integer i : logics.getNewStatus()) {
                cells.get(i).setText("*");
            }
            if (logics.toQuit()) {
                this.dispose();
            }
        });
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton button = new JButton();
                this.cells.add(button);
                panel.add(button);
            }
        }
        pack();
        this.setVisible(true);
    }
}
