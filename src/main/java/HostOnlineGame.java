import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

public class HostOnlineGame extends JFrame {
    JButton updateButton = new JButton("Обновить");
    JTextField chat = new JTextField();
    JTextField yesOrNo = new JTextField();
    public String port = "1234";
    public String ip = "192.168.0.102";

    public HostOnlineGame() {
        super("Данетки");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
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

        chat.setLocation(50, 50);
        chat.setSize(400, 50);
        chat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = chat.getText();
                try {
                    FileWriter writer = new FileWriter("saves\\question.txt");
                    writer.write(str);
                    writer.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        yesOrNo.setLocation(50, 120);
        yesOrNo.setSize(100, 50);
        yesOrNo.setEnabled(false);
        yesOrNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateButton.setLocation(200, 120);
        updateButton.setSize(100, 50);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = new URL("http://" + ip + ":" + port + "/answer.txt");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

                    File f1 = new File("saves\\answer.txt");
                    FileOutputStream fw = new FileOutputStream(f1);

                    byte[] b = new byte[1024];
                    int count = 0;

                    while ((count = bis.read(b)) != -1)
                        fw.write(b, 0, count);

                    fw.close();
                } catch (IOException ex) {
                }
                String answer;
                try (FileReader fr = new FileReader("saves\\answer.txt")) {
                    BufferedReader reader = new BufferedReader(fr);
                    answer = reader.readLine();
                    yesOrNo.setText(answer);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });

        chat.setActionCommand("Open");
        menu.add(chat);
        yesOrNo.setActionCommand("Open");
        menu.add(yesOrNo);
        updateButton.setActionCommand("Open");
        menu.add(updateButton);

        menu.setOpaque(true);
        return menu;
    }
}
