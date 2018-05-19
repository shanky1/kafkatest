package kafka.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitDialog extends JDialog {

    private JDialog dialog = new JDialog();

    public ExitDialog() {
        JFrame frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/kafka/images/kafka.png")));
        dialog = new JDialog(frame, "Quit?");
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dialog.setVisible(false);
            }
        });
        dialog.setSize(350, 110);

        pmtMsgPanel = new JPanel();

        JPanel buttonPanel = new JPanel();
        JButton quit = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        FlowLayout buttonPanellayout  = new FlowLayout(FlowLayout.CENTER, 10, 10);

        pmtMsgPanel.add(new JLabel("Are you sure you want to quit?"), BorderLayout.CENTER);

        buttonPanel.setLayout(buttonPanellayout);
        buttonPanel.add(quit);
        buttonPanel.add(cancel);

        dialog.add(pmtMsgPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int screenWidth = dim.width;
        int screenHeight = dim.height;
        int frameWidth = screenWidth/3;
        int frameHeight = screenHeight/3;
        dialog.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

        dialog.setVisible(true);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                System.exit(0);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
    }
    private JPanel pmtMsgPanel;
}
