package View;

import javax.swing.*;
import java.sql.PreparedStatement;

public class FlyThread extends Thread{
    private ThreadJPanel threadJPanel;
    private boolean keepGoing;

    public FlyThread(ThreadJPanel threadJPanel) {
        this.threadJPanel = threadJPanel;
    }

    @Override
    public void run() {
       while (true) {
            try {
                Thread.sleep(10);
                //threadJPanel.fly(750,427,225,505);
                threadJPanel.move();
                threadJPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
      }
    }
}
