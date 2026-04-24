package fcu.remote.ui;

import fcu.remote.AirConditioner;
import fcu.remote.ControllableDevice;
import fcu.remote.RemoteController;
import fcu.remote.Tv;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class RemoteControllerFrame extends JFrame {
    private final ControllableDevice tv = new Tv();
    private final ControllableDevice airConditioner = new AirConditioner();
    private final RemoteController remoteController = new RemoteController(tv);

    private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private final JComboBox<String> deviceComboBox = new JComboBox<>(new String[]{"TV", "AirConditioner"});

    public RemoteControllerFrame() {
        super("Remote Controller");
        initUi();
        refreshStatus();
    }

    private void initUi() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        topPanel.add(new JLabel("Device:"));
        topPanel.add(deviceComboBox);

        deviceComboBox.addActionListener(e -> {
            String selected = (String) deviceComboBox.getSelectedItem();
            if ("AirConditioner".equals(selected)) {
                remoteController.setDevice(airConditioner);
            } else {
                remoteController.setDevice(tv);
            }
            refreshStatus();
        });

        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusLabel.setFont(statusLabel.getFont().deriveFont(Font.BOLD, 16f));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));

        JButton onButton = new JButton("ON");
        JButton offButton = new JButton("OFF");
        JButton upButton = new JButton("UP");
        JButton downButton = new JButton("DOWN");

        Dimension buttonSize = new Dimension(90, 36);
        onButton.setPreferredSize(buttonSize);
        offButton.setPreferredSize(buttonSize);
        upButton.setPreferredSize(buttonSize);
        downButton.setPreferredSize(buttonSize);

        onButton.addActionListener(e -> {
            remoteController.pressOn();
            refreshStatus();
        });
        offButton.addActionListener(e -> {
            remoteController.pressOff();
            refreshStatus();
        });
        upButton.addActionListener(e -> {
            remoteController.pressUp();
            refreshStatus();
        });
        downButton.addActionListener(e -> {
            remoteController.pressDown();
            refreshStatus();
        });

        buttonsPanel.add(onButton);
        buttonsPanel.add(offButton);
        buttonsPanel.add(upButton);
        buttonsPanel.add(downButton);

        add(topPanel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(420, 220));
        setLocationRelativeTo(null);
    }

    private void refreshStatus() {
        statusLabel.setText(remoteController.getStatusText());
    }

    public static void launch() {
        SwingUtilities.invokeLater(() -> new RemoteControllerFrame().setVisible(true));
    }
}
