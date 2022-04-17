import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AboutUs extends JPanel implements FocusListener, ActionListener {
    JLabel heading;
    JLabel username, oldPassword, newPassword, confirmPassword;
    JTextField input, old, New, confirm;
    Border border= BorderFactory.createLineBorder(Color.black,2);
    Border border1=  BorderFactory.createLineBorder(Color.blue,2);
    Border line;
    Border empty;
    Border borderr;
    JButton button;
    String nic;
    String password;
    AboutUs(){
        setBounds(280,100,600,400);
        setBackground(new Color(2, 0, 0));
        setLayout(null);

        line = BorderFactory.createLineBorder(Color.GRAY,2);
        empty = BorderFactory.createEmptyBorder(0,5,00,10);
        borderr = new CompoundBorder(line, empty);

        heading= new JLabel("Change Password");
        heading.setBounds(170,40,300,50);
        heading.setFocusable(true);
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(Color.white);
        add(heading);

        username= new JLabel("Username");
        username.setBounds(180,160,120,30);
        username.setForeground(new Color(220, 220, 220));
        username.setFont(new Font("Arial", Font.BOLD, 16));
        add(username);

        oldPassword= new JLabel("Old Password");
        oldPassword.setBounds(180,90,120,30);
        oldPassword.setForeground(new Color(220, 220, 220));
        oldPassword.setFont(new Font("Arial", Font.BOLD, 16));

        newPassword= new JLabel("New Password");
        newPassword.setBounds(180,170,120,30);
        newPassword.setForeground(new Color(220, 220, 220));
        newPassword.setFont(new Font("Arial", Font.BOLD, 16));

        confirmPassword= new JLabel("Confirm Password");
        confirmPassword.setBounds(180,250,150,30);
        confirmPassword.setForeground(new Color(220, 220, 220));
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 16));

        input= new JTextField();
        input.setBounds(180,190,220,30);
        input.setFont(new Font("Arial", Font.PLAIN, 16));
        input.setText("Enter Username");
        input.setForeground(new Color(150, 150, 150));
        input.setBackground(new Color(220, 220, 220));
        input.setBorder(borderr);
        input.addFocusListener(this);
        add(input);

        old= new JTextField();
        old.setBounds(180,120,220,30);
        old.setFont(new Font("Arial", Font.PLAIN, 16));
        old.setText("Enter Old password ");
        old.setForeground(new Color(150, 150, 150));
        old.setBackground(new Color(220, 220, 220));
        old.setBorder(borderr);
        old.addFocusListener(this);

        New= new JTextField();
        New.setBounds(180,200,220,30);
        New.setFont(new Font("Arial", Font.PLAIN, 16));
        New.setText("Enter New password");
        New.setForeground(new Color(150, 150, 150));
        New.setBackground(new Color(220, 220, 220));
        New.setBorder(borderr);
        New.addFocusListener(this);

        confirm= new JTextField();
        confirm.setBounds(180,280,220,30);
        confirm.setFont(new Font("Arial", Font.PLAIN, 16));
        confirm.setText("Enter Password");
        confirm.setForeground(new Color(150, 150, 150));
        confirm.setBackground(new Color(220, 220, 220));
        confirm.setBorder(borderr);
        confirm.addFocusListener(this);

        button= new JButton("NEXT");
        button.setBounds(300,230,100,25);
        button.setFocusable(false);
        button.setFont(new Font("Arial",Font.BOLD,20));
        button.setForeground(Color.white);
        button.addActionListener(this);
        button.setBackground(Color.GRAY);
        add(button);
    }


    public static void main(String[] args) {
        JFrame frame =new JFrame();
        frame.setSize(1100,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.add(new AboutUs());
        frame.setVisible(true);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==input){
            line = BorderFactory.createLineBorder(Color.BLACK,2);
            empty = BorderFactory.createEmptyBorder(0,5,00,10);
            borderr = new CompoundBorder(line, empty);
            input.setForeground(Color.BLACK);
            if(input.getText().equals("Enter Username"))
                input.setText("");
            input.setBorder(borderr);
        }
        if(e.getSource()==old){
            line = BorderFactory.createLineBorder(Color.BLACK,2);
            empty = BorderFactory.createEmptyBorder(0,5,00,10);
            borderr = new CompoundBorder(line, empty);
            old.setForeground(Color.BLACK);
            if(old.getText().equals("Enter Old password "))
                old.setText("");
            old.setBorder(borderr);
        }

        if(e.getSource()==New){
            line = BorderFactory.createLineBorder(Color.BLACK,2);
            empty = BorderFactory.createEmptyBorder(0,5,00,10);
            borderr = new CompoundBorder(line, empty);
            New.setForeground(Color.BLACK);
            if(New.getText().equals("Enter New password"))
                New.setText("");
            New.setBorder(borderr);
        }
        if(e.getSource()==confirm){
            line = BorderFactory.createLineBorder(Color.BLACK,2);
            empty = BorderFactory.createEmptyBorder(0,5,00,10);
            borderr = new CompoundBorder(line, empty);
            confirm.setForeground(Color.BLACK);
            if(confirm.getText().equals("Enter Password"))
                confirm.setText("");
            confirm.setBorder(borderr);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            if(button.getText().equals("Save Changes")){
                String getOld = old.getText();
                String getNew = New.getText();
                String getConfirm = confirm.getText();

                if(getOld.equals(password)){
                    if(getNew.equals(getConfirm) && !getNew.trim().equals("")){
                        try{
                            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root586");
                            Statement stm = conn.createStatement();
                            stm.executeUpdate("update candidates set password='" + getNew + "'");
                            JOptionPane.showMessageDialog(null,"Changed Successfully");
                            Main_Frame.label.removeAll();
                            Main_Frame.label.validate();
                            Main_Frame.label.add(new HomePage().home_panel);
                            Main_Frame.label.add(HomePage.lin.panel);
                            HomePage.lin.panel.setVisible(true);
                            Main_Frame.label.add(Main_Frame.logoimage);
                            this.setVisible(false);
                            Main_Frame.label.repaint();
                        }
                        catch (Exception exc){

                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid New Password");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect Old Password");
                }
            }
            else
            {
            nic = input.getText();
            if(nic.equals("Enter Username"))
                JOptionPane.showMessageDialog(null, "Please enter username");
            else {

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root586");
                    Statement stm = conn.createStatement();
                    ResultSet set = stm.executeQuery("select username, password from candidates where Cnic='" + nic + "'");
                    String getUser = "";
                    while (set.next()) {
                        getUser = set.getString("username");
                        password = set.getString("password");
                    }
                    if (nic.equals(getUser)) {
                        System.out.println("Done");
                        username.setVisible(false);
                        input.setVisible(false);
                        repaint();
                        add(oldPassword);
                        add(old);
                        add(newPassword);
                        add(New);
                        add(confirmPassword);
                        add(confirm);
                        button.setBounds(250, 320, 150, 25);
                        button.setText("Save Changes");
                        button.setFont(new Font("Arial", Font.BOLD, 15));

                        //add(newPassword);
                        //add(confirmPassword);
                    } else
                        System.out.println("Invalid");
                } catch (Exception exc) {

                    System.out.println(exc);
                }
            }
            }

        }
    }
}
