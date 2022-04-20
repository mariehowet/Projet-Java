package View;

import javax.swing.*;
import java.sql.PreparedStatement;

public class FlyThread extends Thread{
    private ThreadJPanel threadJPanel;

    public FlyThread(ThreadJPanel threadJPanel) {
        this.threadJPanel = threadJPanel;
    }

    @Override
    public void run() {
       while (true) {
            try {
                Thread.sleep(100);
                threadJPanel.move();
                threadJPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
      }
    }
}
