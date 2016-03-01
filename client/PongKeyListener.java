/**
*@author Kier Lindsay
*The "OlympicRingViewer" class
**/

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PongKeyListener implements KeyListener {

  private ArrayList<String> trackedKeys = new ArrayList<String>();
  private ArrayList<Boolean> keyState = new ArrayList<Boolean>();
    
  public void addKey(String key) {
    trackedKeys.add(key);
    keyState.add(false);
  }
  
  public Boolean isPressed(String key) {
    for(int i = 0; i < trackedKeys.size(); i++) {
      if( trackedKeys.get(i).equals( key ) ) {
	return(keyState.get(i));
      }
    }
    return false;
  }
  
  @Override
    public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    for(int i = 0; i < trackedKeys.size(); i++) {
      if( trackedKeys.get(i).equals( KeyEvent.getKeyText(e.getKeyCode()) ) ) {
	keyState.set(i, true);
	break;
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    for(int i = 0; i < trackedKeys.size(); i++) {
      if( trackedKeys.get(i).equals( KeyEvent.getKeyText(e.getKeyCode()) ) ) {
	keyState.set(i, false);
	break;
      }
    }
  }
}