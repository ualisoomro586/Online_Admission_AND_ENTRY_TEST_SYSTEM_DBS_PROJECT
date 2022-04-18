import javax.crypto.spec.PSource;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Stack;

public class Registration extends JPanel implements FocusListener, ActionListener {
    String name, fatherName, Cnic, surname, District, phone, eMail;
    JTextField username, fathername, cnic, castt, districtt, contactNo,emaill;
    JPasswordField password;
    JButton submitButton, addButton, clearButton;
    Border line;
    Border empty;
    CompoundBorder border;
    static String user="";
    static String pass = "";
    String dd="1",mm="1",yyyy="1";
    ImageIcon image;
    JLabel img;
    File file;
    String imgPath;
    JComboBox<String> year, month, day;
    CPN cpn = new CPN();

    Registration() {
        setBounds(300, 90, 550, 490);
        setBackground(new Color(2, 0, 0, 200));
        setLayout(null);

        JLabel heading = new JLabel("Registration Form");
        heading.setForeground(new Color(220, 220, 220));
        heading.setBounds(120, 5, 300, 50);
        heading.setFocusable(true);
        heading.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 25));
        add(heading);

        img = new JLabel("");
        img.setForeground(Color.WHITE);
        img.setBackground(Color.red);
        img.setOpaque(true);
        img.setIcon(new ImageIcon(new ImageIcon("dp.jpg").getImage().getScaledInstance(140,170,Image.SCALE_DEFAULT)));
        img.setBounds(370, 80, 140, 170);
        add(img);

        addButton = new JButton("Select Image");
        addButton.setBounds(370, 255, 140, 20);
        addButton.setFocusable(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.addActionListener(this);
        addButton.setBackground(new Color(51, 153, 255));
        add(addButton);

        JLabel userName = new JLabel("Name");
        userName.setBounds(30, 40, 300, 20);
        userName.setForeground(new Color(220, 220, 220));
        userName.setFont(new Font("Arial", Font.BOLD, 16));
        add(userName);

        line = BorderFactory.createLineBorder(Color.GRAY, 2);
        empty = BorderFactory.createEmptyBorder(0, 5, 00, 10);
        border = new CompoundBorder(line, empty);

        username = new JTextField();
        username.setBounds(30, 70, 300, 25);
        username.setFont(new Font("Arial", Font.PLAIN, 16));
        username.setText("Enter Name");
        username.setForeground(new Color(150, 150, 150));
        username.setBackground(new Color(220, 220, 220));
        username.setBorder(border);
        username.addFocusListener(this);
        add(username);

        JLabel fatherName = new JLabel("Father's Name");
        fatherName.setBounds(30, 100, 300, 20);
        fatherName.setForeground(new Color(220, 220, 220));
        fatherName.setFont(new Font("Arial", Font.BOLD, 16));
        add(fatherName);

        fathername = new JTextField();
        fathername.setBounds(30, 130, 300, 25);
        fathername.setFont(new Font("Arial", Font.PLAIN, 16));
        fathername.setText("Enter Your Father's Name");
        fathername.setForeground(new Color(150, 150, 150));
        fathername.setBackground(new Color(220, 220, 220));
        fathername.setBorder(border);
        fathername.addFocusListener(this);
        add(fathername);

        JLabel cNIc = new JLabel("CNIC");
        cNIc.setBounds(30, 160, 300, 20);
        cNIc.setForeground(new Color(220, 220, 220));
        cNIc.setFont(new Font("Arial", Font.BOLD, 16));
        add(cNIc);

        cnic = new JTextField();
        cnic.setBounds(30, 190, 300, 25);
        cnic.setFont(new Font("Arial", Font.PLAIN, 16));
        cnic.setText("Format: xxxxx-xxxxxxxx-x");
        cnic.setForeground(new Color(150, 150, 150));
        cnic.setBackground(new Color(220, 220, 220));
        cnic.setBorder(border);
        cnic.addFocusListener(this);
        add(cnic);

        JLabel DOB = new JLabel("D.O.B");
        DOB.setBounds(30, 230, 300, 20);
        DOB.setForeground(new Color(220, 220, 220));
        DOB.setFont(new Font("Arial", Font.BOLD, 16));
        add(DOB);

        String[] days = new String[31];
        for(int i=1; i<32; i++)
            days[i-1] = ""+i;
        day = new JComboBox<>(days);
        day.setBounds(100,230,50,20);
        day.setSelectedItem("1");
        day.addActionListener(this);
        add(day);

        String[] months = new String[12];
        for(int i=1; i<13; i++)
            months[i-1] = ""+i;
        month = new JComboBox<>(months);
        month.setSelectedItem("1");
        month.setBounds(160,230,70,20);
        month.addActionListener(this);
        add(month);

        String[] years = new String[15];
        for(int i=0, j=1990; i<15; i++, j++)
            years[i] = ""+j;
        year = new JComboBox<>(years);
        year.setSelectedItem("1990");
        year.setBounds(240,230,90,20);
        year.addActionListener(this);
        add(year);


        JLabel email = new JLabel("Email");
        email.setBounds(30, 260, 300, 20);
        email.setForeground(new Color(220, 220, 220));
        email.setFont(new Font("Arial", Font.BOLD, 16));
        add(email);

        emaill = new JTextField();
        emaill.setBounds(30, 285, 300, 25);
        emaill.setFont(new Font("Arial", Font.PLAIN, 16));
        emaill.setText("Enter Your Email");
        emaill.setForeground(new Color(150, 150, 150));
        emaill.setBackground(new Color(220, 220, 220));
        emaill.setBorder(border);
        emaill.addFocusListener(this);
        add(emaill);


        JLabel cast = new JLabel("Surname");
        cast.setBounds(30, 310, 300, 20);
        cast.setForeground(new Color(220, 220, 220));
        cast.setFont(new Font("Arial", Font.BOLD, 16));
        add(cast);

        castt = new JTextField();
        castt.setBounds(30, 340, 300, 25);
        castt.setFont(new Font("Arial", Font.PLAIN, 16));
        castt.setText("Enter Your Surname");
        castt.setForeground(new Color(150, 150, 150));
        castt.setBackground(new Color(220, 220, 220));
        castt.setBorder(border);
        castt.addFocusListener(this);
        add(castt);

        JLabel district = new JLabel("District");
        district.setBounds(30, 370, 300, 20);
        district.setForeground(new Color(220, 220, 220));
        district.setFont(new Font("Arial", Font.BOLD, 16));
        add(district);

        districtt = new JTextField();
        districtt.setBounds(30, 400, 300, 25);
        districtt.setFont(new Font("Arial", Font.PLAIN, 16));
        districtt.setText("Enter Your District");
        districtt.setForeground(new Color(150, 150, 150));
        districtt.setBackground(new Color(220, 220, 220));
        districtt.setBorder(border);
        districtt.addFocusListener(this);
        add(districtt);

        JLabel contact = new JLabel("Contact");
        contact.setBounds(30, 430, 300, 20);
        contact.setForeground(new Color(220, 220, 220));
        contact.setFont(new Font("Arial", Font.BOLD, 16));
        add(contact);

        contactNo = new JTextField();
        contactNo.setBounds(30, 460, 300, 25);
        contactNo.setFont(new Font("Arial", Font.PLAIN, 16));
        contactNo.setText("Format: 92xxxxxxxxxx");
        contactNo.setForeground(new Color(150, 150, 150));
        contactNo.setBackground(new Color(220, 220, 220));
        contactNo.setBorder(border);
        contactNo.addFocusListener(this);
        add(contactNo);

        submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setFont(new Font("Arial", Font.BOLD, 12));
        submitButton.setBounds(440, 450, 80, 30);
        submitButton.setBackground(new Color(51, 153, 255));
        submitButton.addActionListener(this);
        add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.setFocusable(false);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setBounds(350, 450, 80, 30);
        clearButton.setBackground(new Color(51, 153, 255));
        clearButton.addActionListener(this);
        add(clearButton);

        JScrollPane bar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(bar);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == username || e.getSource() == fathername
                || e.getSource() == cnic || e.getSource() == contactNo
                || e.getSource() == districtt || e.getSource() == castt|| e.getSource()==emaill) {
            line = BorderFactory.createLineBorder(Color.BLACK, 2);
            empty = BorderFactory.createEmptyBorder(0, 5, 00, 10);
            border = new CompoundBorder(line, empty);
        }
        if (e.getSource() == username) {
            username.setForeground(Color.BLACK);
            username.setBorder(border);
            if (username.getText().equals("Enter Name")) {
                username.setText("");
            }
        }

        if (e.getSource() == emaill) {
            emaill.setForeground(Color.BLACK);
            emaill.setBorder(border);
            if (emaill.getText().equals("Enter Your Email")) {
                emaill.setText("");
            }
        }

        if (e.getSource() == fathername) {
            fathername.setForeground(Color.BLACK);
            fathername.setBorder(border);
            if(fathername.getText().equals("Enter Your Father's Name"))
                fathername.setText("");
        }
        if (e.getSource() == cnic) {
            cnic.setForeground(Color.BLACK);
            cnic.setBorder(border);
            if(cnic.getText().equals("Format: xxxxx-xxxxxxxx-x")){
            cnic.setText("");
            }
        }
        if (e.getSource() == castt) {
            castt.setForeground(Color.BLACK);
            castt.setBorder(border);
            if(castt.getText().equals("Enter Your Surname")){
            castt.setText("");
            }
        }
        if (e.getSource() == districtt) {
            districtt.setForeground(Color.BLACK);
            districtt.setBorder(border);
            if(districtt.getText().equals("Enter Your District"))
            districtt.setText("");
        }
        if (e.getSource() == contactNo) {
            contactNo.setForeground(Color.BLACK);
            contactNo.setBorder(border);
            if(contactNo.getText().equals("Format: 92xxxxxxxxxx")) {
                contactNo.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == username || e.getSource() == fathername
                || e.getSource() == cnic || e.getSource() == contactNo
                || e.getSource() == districtt || e.getSource() == castt) {
            line = BorderFactory.createLineBorder(Color.GRAY, 2);
            empty = BorderFactory.createEmptyBorder(0, 5, 00, 10);
            border = new CompoundBorder(line, empty);
        }
        if (e.getSource() == username) {
            username.setBorder(border);
        }
        if (e.getSource() == fathername) {
            fathername.setBorder(border);
        }
        if (e.getSource() == cnic) {
            cnic.setBorder(border);
        }
        if (e.getSource() == castt) {
            castt.setBorder(border);
        }
        if (e.getSource() == districtt) {
            districtt.setBorder(border);
        }
        if (e.getSource() == contactNo) {
            contactNo.setBorder(border);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == day){
            dd = day.getSelectedItem().toString();
            System.out.println(dd);
        }
        if(e.getSource() == month){
            mm = month.getSelectedItem().toString();
            System.out.println(mm);
        }
        if(e.getSource() == year){
            yyyy = year.getSelectedItem().toString();
            System.out.println(yyyy);
        }
        if (e.getSource()== addButton){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));// this will save current path
            // in current project
            int response= chooser.showOpenDialog(null);
            if(response==JFileChooser.APPROVE_OPTION){
                file= new File(chooser.getSelectedFile().getAbsolutePath());
                image = new ImageIcon(new ImageIcon(file.toString()).getImage().getScaledInstance(140,170,Image.SCALE_DEFAULT));
                img.setIcon(image);
                imgPath = chooser.getSelectedFile().getAbsolutePath();
                imgPath=imgPath.replace("\\","\\\\");

            }
        }
        if (e.getSource() == submitButton) {
            name = username.getText();
            name = name.trim();
            fatherName = fathername.getText();
            Cnic = cnic.getText();
            eMail = emaill.getText();
            surname = castt.getText();
            District = districtt.getText();
            phone = contactNo.getText();
            String DataBirth=yyyy+"-"+mm+"-"+dd;

            boolean isCnicCorrect = false;
            boolean isPhoneCorrect = false;
            boolean isProfileSelected = true;
            boolean isNameCorrect = true;
            boolean isFNameCorrect = true;
            boolean isEmailCorrect = false;
            boolean isSurnameCorrect = true;
            boolean isDistrictCorrect = true;
            boolean isDateCorrect = false;
            for(char i: name.toCharArray()){
                if((int)i<65 || (int)i>122){
                    isNameCorrect = false;
                    break;
                }
            }
            for(char i: District.toCharArray()){
                if((int)i<65 || (int)i>122){
                    isDistrictCorrect = false;
                    break;
                }
            }
            for(char i: fatherName.toCharArray()){
                if((int)i<65 || (int)i>122){
                    isFNameCorrect = false;
                    break;
                }
            }
            for(char i: surname.toCharArray()){
                if((int)i<65 || (int)i>122){
                    isSurnameCorrect = false;
                    break;
                }
            }
            if(eMail.contains("@")){
                String subString = eMail.substring((eMail.indexOf('@')+1));
                System.out.println(subString);
                if(subString.contains(".com"))
                    isEmailCorrect = true;
            }
            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            try{
                date.parse(yyyy+"-"+mm+"-"+dd);
                isDateCorrect = true;
            }
            catch (Exception f){
                isDateCorrect = false;
            }
            if(file==null){
                isProfileSelected = false;
            }

            if(Cnic.charAt(5) == '-' && Cnic.charAt(13) == '-' && Cnic.length()<=15) {
                isCnicCorrect = true;
            }
            if(phone.charAt(0) == '9' && phone.charAt(1) == '2' && phone.length()==12){
                isPhoneCorrect = true;
            }
            if (name.equals("") || name.equals("Enter Name")||fatherName.equals("")||fatherName.equals("Enter Your Father's Name")
                    ||Cnic.equals("")||Cnic.equals("Format: xxxxx-xxxxxxxx-x")||surname.equals("Enter Your Surname")
                     ||surname.equals("")||District.equals("")||District.equals("Enter Your District")||District.equals("")
                         ||phone.equals("")||phone.equals("Format: 92xxxxxxxxxx")) {
                JOptionPane.showMessageDialog(null, "Please Fill All Fields ","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (!isCnicCorrect){
                JOptionPane.showMessageDialog(null, "Invalid CNIC","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (!isEmailCorrect){
                JOptionPane.showMessageDialog(null, "Invalid Email","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(!isNameCorrect){
                JOptionPane.showMessageDialog(null, "Invalid Name","Warning",JOptionPane.WARNING_MESSAGE);
            }else if(!isFNameCorrect){
                JOptionPane.showMessageDialog(null, "Invalid Father Name","Warning",JOptionPane.WARNING_MESSAGE);
            }else if(!isSurnameCorrect){
                JOptionPane.showMessageDialog(null, "Invalid Surname","Warning",JOptionPane.WARNING_MESSAGE);
            }else if(!isDistrictCorrect){
                JOptionPane.showMessageDialog(null, "Invalid District","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if (!isPhoneCorrect){
                JOptionPane.showMessageDialog(null, "Invalid Phone Number","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(!isProfileSelected){
                JOptionPane.showMessageDialog(null, "Please Select Image","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else if(!isDateCorrect){
                JOptionPane.showMessageDialog(null, "Please Select Valid Date","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else{

                try {
                    user=Cnic;
                    user = user.toLowerCase(Locale.ROOT);
                    pass="";
                    pass+=name.charAt(0);
                    pass+=name.charAt(1);
                    pass+=name.charAt(2);
                    pass+=Cnic.charAt(0);
                    pass+=Cnic.charAt(1);
                    pass+=Cnic.charAt(2);
                    pass+=Cnic.charAt(3);
                    pass+=Cnic.charAt(4);
                    pass = pass.toLowerCase(Locale.ROOT);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                            "root", "root586");
                    Statement st = con.createStatement();
                    st.execute("insert into Candidates(Name,FatherName,Cnic,Surname,District,Contact,username,password,Image,DOB,Email) values('" + name + "','" + fatherName + "','" + Cnic + "','" + surname + "','" + District + "','" + phone + "','" + user + "','" + pass + "','"+imgPath+"','"+DataBirth+"','"+eMail+"')");
                    st.close();

                        Main_Frame.label.removeAll();
                        Main_Frame.label.validate();
                        Main_Frame.label.add(cpn.CPN_panel);
                        Main_Frame.label.repaint();

                } catch (Exception a) {
                    String exept= " Cnic Already registered";
                    JOptionPane.showMessageDialog(null, exept, "error", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        else if (e.getSource()==clearButton){
            username.setText("");
            fathername.setText("");
            castt.setText("");
            cnic.setText("");
            districtt.setText("");
            contactNo.setText("");
            emaill.setText("");
        }
    }
}
