import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

public class ClientOnlineGame extends JFrame {
    JButton yesButton = new JButton("Да");
    JButton noButton = new JButton("Нет");
    JButton update = new JButton("Обновить");
    JTextField question = new JTextField();
    public String port = "4321";
    public String ip = "192.168.0.102";

    public ClientOnlineGame() {
        super("Данетки");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo (null);
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

        question.setLocation(50, 50);
        question.setSize(400, 50);
        question.setEnabled(false);
        question.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        yesButton.setLocation(50, 130);
        yesButton.setSize(80, 50);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "Да";
                try {
                    FileWriter writer = new FileWriter("saved\\answer.txt");
                    writer.write(str);
                    writer.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        noButton.setLocation(370,130);
        noButton.setSize(80, 50);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "Нет";
                try {
                    FileWriter writer = new FileWriter("saved\\answer.txt");
                    writer.write(str);
                    writer.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        update.setLocation(190, 200);
        update.setSize(100, 50);
        update.addActionListener(ActionEvent -> {
            try {
                URL url = new URL("http://" + ip + ":" + port + "/question.txt");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

                File f1 = new File("saved\\question.txt");
                FileOutputStream fw = new FileOutputStream(f1);

                byte[] b = new byte[1024];
                int count = 0;

                while ((count = bis.read(b)) != -1)
                    fw.write(b, 0, count);

                fw.close();
            } catch (IOException ex) {
            }
            String q;
            try (FileReader fr = new FileReader("saved\\question.txt")) {
                BufferedReader reader = new BufferedReader(fr);
                q = reader.readLine();
                question.setText(q);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        question.setActionCommand("Open");
        menu.add(question);
        yesButton.setActionCommand("Open");
        menu.add(yesButton);
        noButton.setActionCommand("Open");
        menu.add(noButton);
        update.setActionCommand("Open");
        menu.add(update);

        menu.setOpaque(true);
        return menu;
    }
}
