import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.lang.Math;
import java.sql.*;

public class CPN implements ActionListener {

    JPanel logo_panel,CPN_panel;
    JLabel Logo_image,ssc,fsc,totssc,totfsc,cpn,ssc1,fsc1,test,staric;
    JTextField ssc1_text, fsc1_text,totssc_text,totfsc_text;
    JButton next;
    double ssc_obtmarks,ssc_totmarks,fsc_obtmarks,fsc_totmarks,score;
    double ssc_percentage, fsc_percentage, tot_cpn;

    Border border= BorderFactory.createLineBorder(Color.black,2);
    CPN(){
        CPN_panel = new JPanel();
        CPN_panel.setBounds(200,80,750,450);
        CPN_panel.setBackground(new Color(237,237,238));
        CPN_panel.setLayout(null);


        ssc= new JLabel();
        ssc.setText("<html>Enter Secondary School<br/>Certificate (Matriculation)<br/Obtained Marks:</html>");
        ssc.setFont(new Font("Arial",Font.PLAIN,15));
        ssc.setForeground(Color.BLACK);
        ssc.setBounds(30,20,200,200);

        ssc1_text= new JTextField();
        ssc1_text.setBounds(200,100,180,36);
        ssc1_text.setBorder(border);
        ssc1_text.setFont(new Font("Arial",Font.PLAIN,15));

        totssc= new JLabel();
        totssc.setText("<html>Enter Secondary School<br/>Certificate (Matriculation)<br/Total Marks:</html>");
        totssc.setFont(new Font("Arial",Font.PLAIN,15));
        totssc.setForeground(Color.BLACK);
        totssc.setBounds(30,90,200,200);

        totssc_text= new JTextField();
        totssc_text.setBounds(200,170,180,36);
        totssc_text.setBorder(border);
        totssc_text.setFont(new Font("Arial",Font.PLAIN,15));

        fsc= new JLabel();
        fsc.setText("<html>Enter Higher Secondary School Certificate <br/Obtained Marks:</html>");
        fsc.setFont(new Font("Arial",Font.PLAIN,15));
        fsc.setForeground(Color.BLACK);
        fsc.setBounds(30,180,200,200);

        fsc1_text= new JTextField();
        fsc1_text.setBounds(200,250,180,36);
        fsc1_text.setBorder(border);
        fsc1_text.setFont(new Font("Arial",Font.PLAIN,15));

        totfsc= new JLabel();
        totfsc.setText("<html>Enter Higher Secondary School Certificate <br/Total Marks:</html>");
        totfsc.setFont(new Font("Arial",Font.PLAIN,15));
        totfsc.setForeground(Color.BLACK);
        totfsc.setBounds(30,250,200,200);


        totfsc_text= new JTextField();
        totfsc_text.setBounds(200,325,180,36);
        totfsc_text.setBorder(border);
        totfsc_text.setFont(new Font("Arial",Font.PLAIN,15));

        staric= new JLabel("* Please Enter SSC and FSC Marks");
        staric.setBounds(30,410,400,20);
        staric.setForeground(Color.RED);
        staric.setFont(new Font("Arial",Font.BOLD,20));

        cpn=  new JLabel("CPN WEIGHTAGE");
        cpn.setFont(new Font("Arial",Font.BOLD,30));
        cpn.setBounds(420,120,300,40);
        cpn.setForeground(new Color(0,102,68));

        ssc1=  new JLabel("SSC Percentage       0.10");
        ssc1.setFont(new Font("Arial",Font.BOLD,15));
        ssc1.setBounds(420,180,200,30);
        ssc1.setForeground(Color.BLACK);

        fsc1=  new JLabel("FSC Percentage       0.30");
        fsc1.setFont(new Font("Arial",Font.BOLD,15));
        fsc1.setBounds(420,200,200,30);
        fsc1.setForeground(Color.BLACK);

        test=  new JLabel("Pre-Admission Test      0.60");
        test.setFont(new Font("Arial",Font.BOLD,15));
        test.setBounds(420,230,200,30);
        test.setForeground(Color.BLACK);

        next=  new JButton("NEXT");
        next.setFocusable(false);
        next.setFont(new Font("Arial",Font.BOLD,15));
        next.setBounds(450,410,170,30);
        next.setBackground(new Color(0,120,246));
        next.setForeground(Color.white);
        next.setHorizontalAlignment(JButton.CENTER);
        next.addActionListener(this);




        CPN_panel.add(ssc);
        CPN_panel.add(ssc1_text);
        CPN_panel.add(totssc);
        CPN_panel.add(totssc_text);
        CPN_panel.add(fsc);
        CPN_panel.add(fsc1_text);
        CPN_panel.add(totfsc);
        CPN_panel.add(totfsc_text);
        CPN_panel.add(staric);
        CPN_panel.add(cpn);
        CPN_panel.add(ssc1);
        CPN_panel.add(fsc1);
        CPN_panel.add(test);
        CPN_panel.add(next);

    }
    public static void main(String[] args) {
        new CPN();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==next){
                if (next.getText()=="Select Department"){
                   Main_Frame.label.add(new Department_Selection());
                   CPN_panel.setVisible(false);
                   Main_Frame.label.repaint();
                }
            String smarks= ssc1_text.getText();
            String stmarks= totssc_text.getText();
            String fmarks= fsc1_text.getText();
            String ftmarks= totfsc_text.getText();

        if (!smarks.matches("[0-9]+")||!fmarks.matches("[0-9]+")||!stmarks.matches("[0-9]+")||!ftmarks.matches("[0-9]+")) {
              if(smarks.equals("")||fmarks.equals("")||stmarks.equals("")||ftmarks.equals("")){
                  JOptionPane.showMessageDialog(null,"All fields must be filled","Error",JOptionPane.ERROR_MESSAGE);
              }
              else{
            JOptionPane.showMessageDialog(null,
                    "*marks must be in digits", "Error", JOptionPane.ERROR_MESSAGE);}
        }

        else {
            ssc_obtmarks= Double.parseDouble(smarks);
            ssc_totmarks=Double.parseDouble(stmarks);
            fsc_obtmarks=Double.parseDouble(fmarks);
            fsc_totmarks=Double.parseDouble(ftmarks);

            ssc_percentage= Math.round((double)((ssc_obtmarks*100)/ssc_totmarks)*100.0)/100.0;
            fsc_percentage=Math.round((double) ((fsc_obtmarks*100)/fsc_totmarks)*100.0)/100.0;
            if(ssc_percentage>100||fsc_percentage>100){
                JOptionPane.showMessageDialog(null,"Total Marks can't be greater than Obtained Marks" +
                        "\n Please input correct marks","warning",JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                            "root", "root586");
                    Statement st = con.createStatement();
                    ResultSet set =st.executeQuery("select *from candidates where Cnic='"+login_form.user+"'");
                    while (set.next()){
                        score=Integer.parseInt(set.getString("TestScore"));
                    }
                }
                catch (Exception a){
                    JOptionPane.showMessageDialog(null,a);
                }

                JOptionPane.showMessageDialog(null, "Registration Successful ");
                JOptionPane.showMessageDialog(null,"Your user name is: "+Registration.user+"\nYour password is :"+Registration.pass.toString());
                Main_Frame.label.removeAll();
                Main_Frame.label.validate();
                Main_Frame.label.add(new HomePage().home_panel);
                Main_Frame.label.add(HomePage.lin.panel);
                HomePage.lin.panel.setVisible(true);
                Main_Frame.label.add(Main_Frame.logoimage);
                CPN_panel.setVisible(false);
                Main_Frame.label.repaint();
                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                            "root", "root586");
                    Statement st = con.createStatement();
                    st.execute("update candidates set SSCPercentage="+ssc_percentage+",FSCPercentage="+fsc_percentage+" where Cnic='"+Registration.user+"'");
                    st.close();
                }
                catch(Exception a){
                    JOptionPane.showMessageDialog(null,a);
                }
            }
        }
            }

        }
    }