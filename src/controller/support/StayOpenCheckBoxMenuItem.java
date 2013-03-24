package controller.support;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * An extension of JCheckBoxMenuItem that doesn't close the menu when selected.
 *
 * @author Darryl (Source from internet), Henrique Moraes
 */
public class StayOpenCheckBoxMenuItem extends JCheckBoxMenuItem {

  private static MenuElement[] path;

  {
    getModel().addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent e) {
        if (getModel().isArmed() && isShowing()) {
          path = MenuSelectionManager.defaultManager().getSelectedPath();
        }
      }
    });
  }

  /**
   * @see JCheckBoxMenuItem#JCheckBoxMenuItem()
   */
  public StayOpenCheckBoxMenuItem() {
    super();
  } 

  /**
   * @see JCheckBoxMenuItem#JCheckBoxMenuItem(String)
   */
  public StayOpenCheckBoxMenuItem(String text) {
    super(text);
  }

  /**
   * @see JCheckBoxMenuItem#JCheckBoxMenuItem(String, boolean)
   */
  public StayOpenCheckBoxMenuItem(String text, boolean selected) {
    super(text, selected);
  }

  /**
   * Overridden to reopen the menu.
   *
   * @param pressTime the time to "hold down" the button, in milliseconds
   */
  @Override
  public void doClick(int pressTime) {
    super.doClick(pressTime);
    MenuSelectionManager.defaultManager().setSelectedPath(path);
  }
}
