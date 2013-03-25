package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Paintable;
import util.CompositeStroke;
import view.components.Strokes;


/**
 * Canvas in which the interface gets painted. It displays the turtle
 * and its respective lines
 * 
 * @author Henrique Moraes, Ziqiang Huang
 * 
 */
public class Canvas extends JComponent implements Observer {
    private final static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public final static Dimension DEFAULT_CANVAS_DIMENSION = new Dimension(830, 575);
    public static final Color DEFAULT_BACKGROUND = Color.white;
    public static final Stroke GRID_STROKE =
            new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1,
                            new float[] { 5 }, 0);
    private static final int MARGIN_OFFSET = 13;
    private static final int GRIDLINE_OFFSET = 3;

    private Paintable myPaintingResource;
    private Color myBackgroundColor;
    private Image myBackgroundImage;
    private int myGridSpacing = 100;
    private boolean gridEnabled = false;

    /**
     * Constructor for this class, automatically creates a workspace
     * and a turtle
     */
    public Canvas (Paintable workspace) {
        setPreferredSize(DEFAULT_CANVAS_DIMENSION);
        myBackgroundColor = DEFAULT_BACKGROUND;
        myPaintingResource = workspace;
        setBackground(DEFAULT_BACKGROUND_COLOR);
        myBackgroundImage = null;
    }

    /**
     * Paints the Canvas with its Turtle and lines
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (myBackgroundImage != null) {
            g.drawImage(myBackgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        else {
            g.setColor(myBackgroundColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        Graphics2D pen = (Graphics2D) g;
        drawGrid(pen);

        myPaintingResource.paint(pen);
    }

    /**
     * Draws the grid of the Canvas.
     * Notice: the two for loops are significantly similar, but the only solution
     * I found was to break up into even more methods and use booleans
     * 
     * @param pen Graphics used to paint the grid on this canvas
     */
    private void drawGrid (Graphics2D pen) {
        if (!gridEnabled) return;
        pen.setStroke(GRID_STROKE);
        pen.setColor(Color.black);

        for (int i = 0; i < getWidth() / 2; i += myGridSpacing) {
            pen.drawLine(getWidth() / 2 + i, 0, getWidth() / 2 + i, getHeight());
            pen.drawString(Integer.toString(i), getWidth() / 2 + i + GRIDLINE_OFFSET, MARGIN_OFFSET);

            pen.drawLine(getWidth() / 2 - i, 0, getWidth() / 2 - i, getHeight());
            pen.drawString(Integer.toString(-i), getWidth() / 2 - i + GRIDLINE_OFFSET,
                           MARGIN_OFFSET);
        }

        for (int i = 0; i < getHeight() / 2; i += myGridSpacing) {
            pen.drawLine(0, getHeight() / 2 + i, getWidth(), getHeight() / 2 + i);
            pen.drawString(Integer.toString(i), GRIDLINE_OFFSET, getHeight() / 2 + i +
                                                                 MARGIN_OFFSET);

            pen.drawLine(0, getHeight() / 2 - i, getWidth(), getHeight() / 2 - i);
            pen.drawString(Integer.toString(-i), GRIDLINE_OFFSET, getHeight() / 2 - i +
                                                                  MARGIN_OFFSET);
        }
    }

    /**
     * Repaints all the components associated to this view
     */
    @Override
    public void update (Observable object, Object arg) {
        repaint();
    }

    /**
     * @return the base informational components to paint on this canvas
     *         such as the workspace, as a Paintable
     */
    public Paintable getPaintableResource () {
        return myPaintingResource;
    }

    /**
     * @param space determines the spacing for the grid of this Canvas
     */
    public void setGridSpacing (int space) {
        myGridSpacing = space;
    }

    /**
     * @param space determines the spacing for the grid of this Canvas
     */
    public void setGrid (boolean enable) {
        gridEnabled = enable;
    }

    /**
     * @return visibility of the grid of this Canvas
     */
    public boolean isGridEnabled () {
        return gridEnabled;
    }

    /**
     * @return grid spacing of this Canvas
     */
    public int getGridSpacing () {
        return myGridSpacing;
    }

    /**
     * @param c Background color to be set on this canvas
     */
    public void setBackgroundColor (Color c) {
        myBackgroundColor = c;

        myBackgroundImage = null;
        repaint();
    }

    public void setBackgroundImage (BufferedImage read) {
        myBackgroundImage = read;
        repaint();
    }

    /**
     * Creates a stroke according to given parameters
     * 
     * @param type type of stroke
     * @param thickness thickness of the stroke
     * @return A stroke based on the parameters given
     */
    public Stroke createStroke (Strokes type, float thickness) {
        switch (type) {
            case SOLID:
                return new BasicStroke(thickness);

            case DASHED:
                return new BasicStroke(thickness, BasicStroke.CAP_SQUARE,
                                       BasicStroke.JOIN_BEVEL, 1, new float[] { 5f }, 0);
            case DOTTED:
                return new BasicStroke(thickness, BasicStroke.CAP_SQUARE,
                                       BasicStroke.JOIN_BEVEL, 1, new float[] { .5f, 10f }, 0);
            case DASH_AND_DOT:
                return new BasicStroke(thickness, BasicStroke.CAP_SQUARE,
                                       BasicStroke.JOIN_BEVEL, 1, new float[] { .5f, 10, 7, 10 }, 0);
            case DOUBLE_LINE:
                return new CompositeStroke(4, 1);
        }
        return null;
    }
}
