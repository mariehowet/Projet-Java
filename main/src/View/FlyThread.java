package View;


public class FlyThread extends Thread{
    private ThreadJPanel threadJPanel;

    public FlyThread(ThreadJPanel threadJPanel) {
        this.threadJPanel = threadJPanel;
    }

    @Override
    public void run() {
       while (true) {
            try {
                Thread.sleep(1000);
                threadJPanel.move();
                threadJPanel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
      }
    }
}
