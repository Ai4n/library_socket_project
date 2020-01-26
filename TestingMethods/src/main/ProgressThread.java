package main;

import javax.swing.*;

public class ProgressThread extends Thread {
    JProgressBar progressBar;
    private static Object sharedObj = new Object();

    ProgressThread () {
        progressBar = new JProgressBar();
        progressBar.setString(this.getName());
        progressBar.setStringPainted(true);
    }

    JComponent getProgressComponent () {
        return progressBar;
    }

    @Override
    public void run () {

        int c = 0;
        while (true) {
            synchronized (sharedObj) {
                if (c == 100) {
                    c = 0;
                }
                progressBar.setValue(++c);
                try {
                    //sleep the thread to simulate long running task
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}