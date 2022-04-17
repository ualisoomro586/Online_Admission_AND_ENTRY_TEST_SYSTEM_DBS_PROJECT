import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timer implements ActionListener {
    JFrame frame;
    JLabel label;
    int elapsedtime = 0;
    int minute = 0;
    int sec = 0;
    boolean started = false;
    String minute_string = String.format("%02d", minute);
    String second_string = String.format("%02d", sec);
    JButton button;

    Timer time = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedtime = elapsedtime + 1000;
            minute = (elapsedtime / 60000) % 60;
            sec = (elapsedtime / 1000) % 60;
            String minute_string = String.format("%02d", minute);
            String second_string = String.format("%02d", sec);
            label.setText(minute_string + ":" + second_string);
        }


    });

    timer() {
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        label = new JLabel();
        label.setBounds(100, 100, 200, 50);
        label.setText(minute_string + ":" + second_string);

        button = new JButton("Start");
        button.setBounds(100, 200, 50, 100);
        button.addActionListener(this);
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new timer();
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == button) {


        }
    }
}

