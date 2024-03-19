package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

	private GamePanel gamepanel;
	
	public KeyboardInputs(GamePanel gp) {
		gamepanel = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
			case KeyEvent.VK_W:

				break;
			case KeyEvent.VK_S:

				break;
				
			case KeyEvent.VK_UP:

				break;
			case KeyEvent.VK_DOWN:

				break;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_W:
		case KeyEvent.VK_S:

			break;

		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:

			break;
		
		}
		
	}

}
