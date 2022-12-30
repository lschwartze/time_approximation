package screensaver;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;	

@SuppressWarnings("serial")
public class SeedPlot extends JPanel{
	//object variables, values to plot, margin on the panel, maximum value
	int[] values;
	int marg;
	int max;
	
	//constructor
	public SeedPlot(int[] values, int marg) {
		this.values = values;
		this.marg = marg;
		this.max = this.getMax();
	}
	
	//method draws plot
	protected void paintComponent(Graphics grf) {
		super.paintComponents(grf);
		Graphics2D graph = (Graphics2D) grf;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width = getWidth();
		int height = getHeight();
		
		//draw two lines as coordinate axis
		graph.draw(new Line2D.Double(width/2, 1.5*marg, width/2, height-marg));
		graph.draw(new Line2D.Double(marg, height-marg, width-marg, height-marg));
		//scale the values of the object array to x and y axis
		double x = (double) (width - 2*marg)/(values.length-1);
		double scale;
		if(this.max == 0) {
			scale = (double) 0;
		}
		else {
			scale = (double) (height-2.5*marg)/(this.max);
		}
		
		//points coloured red
		graph.setPaint(Color.RED);
		
		//draw points in coordinate system
		for(int i = 0; i<this.values.length; i++) {
			double x1 = marg + i*x;
			double y1 = height - marg - scale*this.values[i];
			graph.fill(new Ellipse2D.Double(x1-2,y1-2,4,4));
		}
	}
	
	//get maximum, since points are not negative, this simple implementation is fine
	int getMax() {
		int[] arr = this.values;
		int max = arr[0];
		for(int i = 1; i<arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
}
