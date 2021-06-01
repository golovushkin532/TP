import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class OnlineChoice extends JFrame {

    private final JButton hostButton = new JButton("ХОСТ");
    private final JButton clientButton = new JButton("КЛИЕНТ");

    public OnlineChoice() {
        super("Данетки");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(375, 200);
        setVisible(true);
        try {
            setContentPane(panelChoice());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public JPanel panelChoice() throws URISyntaxException {
        JPanel menu = new JPanel();
        menu.setLayout(null);
        hostButton.setLocation(50, 50);
        hostButton.setSize(100, 50);
        hostButton.setBackground(new Color(0x6DC911));
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HostOnlineGame();
                OnlineChoice.super.setVisible(false);
            }
        });

        hostButton.setActionCommand("Open");
        menu.add(hostButton);
        clientButton.setLocation(200, 50);
        clientButton.setSize(100, 50);
        clientButton.setBackground(new Color(0x157AA1));
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientOnlineGame();
                OnlineChoice.super.setVisible(false);

            }
        });

        clientButton.setActionCommand("Open");
        menu.add(clientButton);

        menu.setOpaque(true);
        return menu;
    }

}