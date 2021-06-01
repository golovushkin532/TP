import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class HostOnlineGame extends JFrame {
    JButton sendButton = new JButton("Отправить");
    JTextField chat = new JTextField();
    JTextField yesOrNo = new JTextField();

    public HostOnlineGame() {
        super("Данетки");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 600);
        try {
            setContentPane(panel());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public JPanel panel() throws URISyntaxException {
        JPanel menu = new JPanel();
        menu.setLayout(null);

        chat.setLocation(50, 20);
        chat.setSize(200, 100);
        chat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = chat.getText();
                try {
                    FileWriter writer = new FileWriter("saved\\question.txt");
                    writer.write(str);
                    writer.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        yesOrNo.setLocation(50, 120);
        yesOrNo.setSize(100, 80);
        yesOrNo.setEnabled(false);
        yesOrNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendButton.setLocation(50, 300);
        sendButton.setSize(200, 50);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        chat.setActionCommand("Open");
        menu.add(chat);
        yesOrNo.setActionCommand("Open");
        menu.add(yesOrNo);
        sendButton.setActionCommand("Open");
        menu.add(sendButton);

        menu.setOpaque(true);
        return menu;
    }
}
