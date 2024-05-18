package componentesPersonalizados;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class RoundedBorder extends JButton {
    private int borderRadius;
    private GradientPaint colorGradiant;

    public RoundedBorder(int borderRadius) {
    	super();
        this.borderRadius = borderRadius;
        setOpaque(false);
    }
    
	public GradientPaint getColorGradiant() {
		return colorGradiant;
	}
	public void setColorGradiant(GradientPaint colorGradiant) {
		this.colorGradiant = colorGradiant;
	}

	protected void paintComponent(Graphics g) {
		int height = getHeight();
		colorGradiant = new GradientPaint(0.0f, 0.0f, new Color(0,87,255), 0, height, new Color(33, 147, 246), true);
		((Graphics2D) g).setPaint(colorGradiant);
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getBackground());
        g.drawRoundRect(0, 0, getWidth() - 1, height - 1, height, 100);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        
    }
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, borderRadius, borderRadius);
        return shape.contains(x, y);
    }
}