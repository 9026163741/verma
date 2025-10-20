import hospital.management.system.conn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    public Login() {
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40, 20, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(namelabel);

        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(password);

        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        add(jPasswordField);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(400, 250);
        setLocation(400, 270);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn cm = new conn();
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());

                String q = "SELECT * FROM login WHERE ID=? AND pw=?";
                PreparedStatement ps = cm.connection.prepareStatement(q);
                ps.setString(1, user);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    new Reception();
                    setVisible(false);
                    //JOptionPane.showMessageDialog(null, "Login Successful!");
                    //setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}











