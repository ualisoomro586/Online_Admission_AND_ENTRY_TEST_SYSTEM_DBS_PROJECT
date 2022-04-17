import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Student extends JPanel {
    Connection con;
    String row = "";
    String name = "", father = "", cnic = "", surname = "", district = "", contact = "", depart = "";
    String Score;
    String cpn;
    int number=1;
    JFrame frame;
    JLabel design,data1,data2,data3,data4;

    Student() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 650);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        design = new JLabel();
        design.setText("<html><pre>+-----------+--------------+-------------------------------+--------------+------------+--------------+-------------------------+<br/>" +
                "Name\t\tFather Name \t Cnic\t\t\t\tDistrict \tScore\t\tCpn\t\tDepartment<br/>+----------------------------------------------------" +
                "---------------------------------------------------------------------------+</pre></html>");
        design.setBounds(0, 0, 800, 50);
        design.setFont(new Font("Arial", Font.BOLD, 10));
        design.setForeground(Color.white);

        data1= new JLabel();
        data2= new JLabel();
        data3= new JLabel();
        data4= new JLabel();

        setSize(800, 490);
        setBounds(280, 90, 780, 450);
        setBackground(new Color(0, 0, 0, 200));
        setLayout(null);


        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root586");
            Statement stm = con.createStatement();
            ResultSet set = stm.executeQuery("select* from candidates");

            while (set.next()) {
                name = set.getString("Name");
                father = set.getString("FatherName");
                cnic = set.getString("Cnic");
                surname = set.getString("Surname");
                district = set.getString("District");
                contact = set.getString("Contact");
                Score = String.valueOf(set.getInt("TestScore"));
                cpn = String.valueOf(set.getDouble("Cpn"));
                depart = set.getString("DisciplineAwarded");

                if(number==1){
                data1.setBounds(0,50,800,50);
                data1.setFont(new Font("Arial",Font.BOLD,10));
                data1.setForeground(Color.white);
                data1.setText("<html><pre>"+name+"\t\t"+father+"\t"+cnic+"\t\t\t"+district+"\t\t"+Score+"\t\t"+cpn+"\t\t"+depart+"<br/><br/></pre></html>");
                add(data1);
                }
                if(number==2){
                    data2.setBounds(0,110,800,50);
                    data2.setFont(new Font("Arial",Font.BOLD,10));
                    data2.setForeground(Color.white);
                    data2.setText("<html><pre>"+name+"\t\t"+father+"\t"+cnic+"\t\t\t"+district+"\t\t"+Score+"\t\t"+cpn+"\t\t"+depart+"<br/><br/></pre></html>");
                    add(data2);
                }
                    if(number==3){
                        data3.setBounds(0,170,800,50);
                        data3.setFont(new Font("Arial",Font.BOLD,10));
                        data3.setForeground(Color.white);
                        data3.setText("<html><pre>"+name+"\t\t"+father+"\t"+cnic+"\t\t\t"+district+"\t\t"+Score+"\t\t"+cpn+"\t\t"+depart+"<br/><br/></pre></html>");
                        add(data3);
                    }
                number++;
               // System.out.println(row + name + father + cnic + surname + district + contact + Score + cpn);//JOptionPane.showMessageDialog(null, "connection Succesfull");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        add(design);
    }

}