

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class squareChoose extends AbstractAction {

	private int x, y;
	
	public squareChoose(int X, int Y) {
		
		x = X;
		y = Y;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		UI.coordX = x;
		UI.coordY = y;
		UI.clicked = true;
	}

	
	
}
