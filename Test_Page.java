import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test_Page implements ActionListener {
    JPanel panel1,panel2,panel3;
    JLabel logo,Question,time,Colon,remaining,Subject;
    JTextField t1,t2;
    JButton button;
    login_form login= new login_form();
    JRadioButton r1,r2,r3,r4,r5;
    ButtonGroup group;
    CPN cpn;
    ImageIcon image= new ImageIcon(new ImageIcon("muet_logo.png").getImage().getScaledInstance(400,100,Image.SCALE_DEFAULT));
    int current;
    static int score;
    String no=login_form.user;
    int nums;
    int lastSkipped;
    int timeElapse;
    int now;
    int[] ques;
    static int sec = 30;
    static int min = 1;
    Thread t;
    boolean timer = true;
    boolean lastReached;
    boolean checkSkipped;
    JFrame frame;
    int[] data = new int[20];
    Test_Page(){
        lastReached = false;
        current = 1;       score = 0;
        nums=0;
        now = 0;
        lastSkipped = 0;
        ques = new int[20];
        login.panel.setVisible(false);
        checkSkipped =  false;

        frame = new JFrame();
        frame.setSize(1100, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        cpn = new CPN();
        logo= new JLabel();
        logo.setBounds(0,0,400,100);
        logo.setIcon(image);

        time = new JLabel("Time:");
        time.setBounds(450,67,100,20);
        time.setFont(new Font("Arial",Font.BOLD,20));

        Colon = new JLabel("0"+min+":"+"0" +sec);
        Colon.setFont(new Font("Arial",Font.BOLD,16));
        Colon.setBounds(515,67,140,23);
        Colon.setBorder(BorderFactory.createLineBorder(Color.red,2));

        remaining= new JLabel("remaining");
        remaining.setFont(new Font("Arial",Font.BOLD,16));
        remaining.setBounds(570,68,100,20);


        panel1= new JPanel();
        panel1.setBounds(180,70,750,500);
        panel1.setBackground(new Color(238,237,238));
        panel1.setLayout(null);

        panel2=new JPanel();
        panel2.setBounds(0,0,700,100);
        panel2.setBackground(new Color(238,237,238));
        panel2.setLayout(null);
        panel2.add(logo);
        panel2.add(time);
        panel2.add(Colon);
        panel2.add(remaining);

        panel3= new JPanel();
        panel3.setBounds(0,100,200,400);
        panel3.setLayout(null);
        panel3.setBackground(new Color(0,120,246));

        Subject= new JLabel("English");
        Subject.setFont(new Font("Arial",Font.BOLD,30));
        Subject.setBounds(330,130,220,35);
        Subject.setHorizontalAlignment(JLabel.CENTER);
        Subject.setBorder(BorderFactory.createLineBorder(Color.blue,2));

        Question= new JLabel();
        Question.setFont(new Font("Arial",Font.BOLD,20));
        Question.setBounds(280,200,400,50);

        r1= new JRadioButton();
        r1.setBounds(300,260,150,20);
        r1.setOpaque(false);
        r1.addActionListener(this);
        r1.setFocusable(false);
        r1.setFont(new Font("Arial",Font.PLAIN,14));

        r2= new JRadioButton();
        r2.setBounds(450,260,150,20);
        r2.setOpaque(false);
        r2.setFocusable(false);
        r2.addActionListener(this);
        r2.setFont(new Font("Arial",Font.PLAIN,14));

        r3= new JRadioButton();
        r3.setBounds(300,310,160,20);
        r3.setOpaque(false);
        r3.setFocusable(false);
        r3.addActionListener(this);
        r3.setFont(new Font("Arial",Font.PLAIN,14));

        r4= new JRadioButton();
        r4.setBounds(450,310,160,20);
        r4.setOpaque(false);
        r4.setFocusable(false);
        r4.addActionListener(this);
        r4.setFont(new Font("Arial",Font.PLAIN,14));

        r5= new JRadioButton();

        group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);
        group.add(r4);
        group.add(r5);

        button= new JButton("SKIP");
        button.setBounds(480,370,80,30);
        button.addActionListener(this);
        button.setBackground(new Color(0,120,246));
        set();
        t= new Thread(){
            public void run(){
                while (timer){
                    try{
                        sec--;
                        sleep(1000);
                        if(sec<0){
                            sec = 59;
                            min--;
                        }
                        if(sec<=9)
                            Colon.setText("0"+min+":0"+sec);
                        else
                            Colon.setText("0"+min+":"+sec);
                        if(min==0  && sec == 0) {
                            double ssc = 0;
                            double fsc= 0;
                            double total_cpn = 0;
                            Connection conn;
                            try{
                                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root586");
                                Statement stm= conn.createStatement();
                                stm.execute("update candidates set TestScore='"+String.valueOf(score)+"'WHERE Cnic='"+no+"'");
                                String query = "update questions set Q1 ="+data[0]+",Q2="+data[1]+",Q3="+data[2]+",Q4="+data[3]+",Q5="+data[4]+",Q6="+data[5]+",Q7="+data[6]+",Q8="+data[7]+",Q9="+data[8]+",Q10="+data[9]+",Q11="+data[10]+",Q12="+data[11]+",Q13="+data[12]+",Q14="+data[13]+",Q15="+data[14]+",Q16="+data[15]+",Q17="+data[16]+",Q18="+data[17]+",Q19="+data[18]+",Q20="+data[19]+" WHERE Cnic='"+no+"'";
                                ResultSet set = stm.executeQuery("select SSCPercentage, FSCPercentage from candidates where Cnic='"+no+"'");
                                while (set.next()){
                                    ssc = set.getDouble("SSCPercentage");
                                    fsc = set.getDouble("FSCPercentage");
                                }
                                total_cpn =Math.round ((double)( (ssc * 0.1) + (fsc * 0.3) + (score * 5 * 0.6))*100.0)/100.0;
                                stm.execute("update candidates set CPN="+total_cpn+"WHERE Cnic='"+no+"'");
                                stm.execute(query);
                                System.out.println("Done");
                                stm.close();
                            }
                            catch (Exception c){
                                JOptionPane.showMessageDialog(null,c);
                            }
                            JOptionPane.showMessageDialog(null, "Score: " + score);
                            JOptionPane.showMessageDialog(null, "CPN: " + total_cpn);
                            Main_Frame.label.add(new last_page().panel);
                            panel1.setVisible(false);
                            Main_Frame.label.repaint();
                            timer = false;
                        }
                    }
                    catch (Exception e){
                        e.getStackTrace();
                    }
                }
            }
        };
        t.start();


        panel1.add(panel2);
        panel1.add(panel3);
        panel1.add(Subject);
        panel1.add(Question);
        panel1.add(r1);
        panel1.add(r2);
        panel1.add(r3);
        panel1.add(r4);
        panel1.add(button);

       // frame.add(panel1);
       // frame.setVisible(true);

    }
    public void set(){
        button.setText("SKIP");
        button.setFocusable(false);
        r5.setSelected(true);
        if(current==1){
            Subject.setText("English");
            Question.setText("Doer of action is known as ");
            r1.setText("Subject");
            r2.setText("verb");
            r3.setText("Adjective");
            r4.setText("N.O.T");
        }
        if(current==2){
            Subject.setText("English");
            Question.setText("I _______ cricket");
            r1.setText("play");
            r2.setText("plays");
            r3.setText("Am Playing");
            r4.setText("Both a and c");
        }
        if(current==3){
            Subject.setText("English");
            Question.setText("He is ______ Gentle man.");
            r1.setText("An");
            r2.setText("A");
            r3.setText("Both a and b");
            r4.setText("N.O.T");
        }
        if(current==4){
            Subject.setText("English");
            Question.setText("We _______ won the Match.");
            r1.setText("has");
            r2.setText("Had");
            r3.setText("have");
            r4.setText("Both b and c ");
        }
        if(current==5){
            Subject.setText("English");
            Question.setText("Good  is _________");
            r1.setText("Noun");
            r2.setText("Adjective");
            r3.setText("Pronoun");
            r4.setText("Verb");
        }
        if(current==6){
            Subject.setText("Physics");
            Question.setText("_____ is unit of current.");
            r1.setText("m/sec");
            r2.setText("Watt");
            r3.setText("Ampere");
            r4.setText("N.O.T");
        }
        if(current==7){
            Subject.setText("Physics");
            Question.setText("speed is a _________");
            r1.setText("Scalar");
            r2.setText("Vector");
            r3.setText("Tensor");
            r4.setText("N.O.T");
        }
        if(current==8){
            Subject.setText("Physics");
            Question.setText("________ is unit of power.");
            r1.setText("joule");
            r2.setText("kg");
            r3.setText("Ampere");
            r4.setText("Watt");
        }
        if(current==9){
            Subject.setText("Physics");
            Question.setText("Value of gravity on earth.");
            r1.setText("9.6 m/sec");
            r2.setText("9.8 m/sec^2");
            r3.setText("9.8 m/sec");
            r4.setText("Both a and c");
        }
        if(current==10){
            Subject.setText("Physics");
            Question.setText("Displacement is a ________ Quantity.");
            r1.setText("Vector");
            r2.setText("Scalar");
            r3.setText("Tensor");
            r4.setText("N.O.T");
        }
        if(current==11){
            Subject.setText("Chemistry");
            Question.setText("_________is the formula of water.");
            r1.setText("Ho2");
            r2.setText("H2o2");
            r3.setText("H2o");
            r4.setText("Both a and c");
        }
        if(current==12){
            Subject.setText("Chemistry");
            Question.setText("PH of Water is _______.");
            r1.setText("7.1");
            r2.setText("7.4");
            r3.setText("7.0");
            r4.setText("N.O.T");
        }
        if(current==13){
            Subject.setText("Chemistry");
            Question.setText("Nacl is the formula of _____.");
            r1.setText("Sodium bi chloride");
            r2.setText("Sodium chloride");
            r3.setText("Sodium chromate");
            r4.setText("N.O.T");
        }
        if(current==14){
            Subject.setText("Chemistry");
            Question.setText("Atomic number of Tin.");
            r1.setText("50");
            r2.setText("55");
            r3.setText("60");
            r4.setText("65");
        }
        if(current==15){
            Subject.setText("Chemistry");
            Question.setText("Atomic Mass of Nitrogen.");
            r1.setText("7");
            r2.setText("14");
            r3.setText("21");
            r4.setText("28");
        }
        if(current==16){
            Subject.setText("G.Knowledge");
            Question.setText("Population of Pakistan");
            r1.setText("20 Crore");
            r2.setText("21 Crore");
            r3.setText("22 Crore");
            r4.setText("N.O.T");
        }
        if(current==17){
            Subject.setText("G.Knowledge");
            Question.setText("Capital of Turkey.");
            r1.setText("Istanbul");
            r2.setText("Newyork");
            r3.setText("Yen");
            r4.setText("N.O.T");
        }
        if(current==18){
            Subject.setText("G.Knowledge");
            Question.setText("<html>How many continents are there <br/>in the world.</html>");
            r1.setText("4");
            r2.setText("5");
            r3.setText("6");
            r4.setText("7");
        }
        if(current==19){
            Subject.setText("G.Knowledge");
            Question.setText("Largest river in Asia");
            r1.setText("Mohan River");
            r2.setText("Indus River");
            r3.setText("Both a and b");
            r4.setText("N.O.T");
        }
        if(current==20){
            Subject.setText("G.Knowledge");
            Question.setText("<html>_______ has Largest Population <br/>in the wolrd.</html>");
            r1.setText("Pakistan");
            r2.setText("India");
            r3.setText("China");
            r4.setText("USA");
            lastReached = true;
            button.setText("SUBMIT");
        }
    }
    public boolean check(){
        if (current == 1) {
            if (r1.isSelected()) {
                data[0] = 1;
                return true;
            }
            else {
                data[0] = 0;
                return  false;
            }
        }
        if (current == 2) {
            if (r4.isSelected()) {
                data[1] = 1;
                return true;
            }
            else {
                data[1] = 0;
                return  false;
            }
        }
        if (current == 3){
            if (r3.isSelected()) {
                data[2] = 1;
                return true;
            }
            else {
                data[2] = 0;
                return  false;
            }
        }
        if (current == 4) {
            if (r4.isSelected()) {
                data[3] = 1;
                return true;
            }
            else {
                data[3] = 0;
                return  false;
            }
        }
        if (current == 5) {
            if (r2.isSelected()) {
                data[4] = 1;
                return true;
            }
            else {
                data[4] = 0;
                return  false;
            }
        }
        if (current == 6) {
            if (r3.isSelected()) {
                data[5] = 1;
                return true;
            }
            else {
                data[5] = 0;
                return  false;
            }
        }
        if (current == 7) {
            if (r1.isSelected()) {
                data[6] = 1;
                return true;
            }
            else {
                data[6] = 0;
                return  false;
            }
        }
        if (current == 8) {
            if (r4.isSelected()) {
                data[7] = 1;
                return true;
            }
            else {
                data[7] = 0;
                return  false;
            }
        }
        if (current == 9) {
            if (r2.isSelected()) {
                data[8] = 1;
                return true;
            }
            else {
                data[8] = 0;
                return  false;
            }
        }
        if (current == 10) {
            if (r3.isSelected()) {
                data[9] = 1;
                return true;
            }
            else {
                data[9] = 0;
                return  false;
            }
        }
        if (current == 11) {
            if (r3.isSelected()) {
                data[10] = 1;
                return true;
            }
            else {
                data[10] = 0;
                return  false;
            }
        }
        if (current == 12) {
            if (r3.isSelected()) {
                data[11] = 1;
                return true;
            }
            else {
                data[11] = 0;
                return  false;
            }
        }
        if (current == 13) {
            if (r2.isSelected()) {
                data[12] = 1;
                return true;
            }
            else {
                data[12] = 0;
                return  false;
            }
        }
        if (current == 14) {
            if (r2.isSelected()) {
                data[13] = 1;
                return true;
            }
            else {
                data[13] = 0;
                return  false;
            }
        }
        if (current == 15) {
            if (r2.isSelected()) {
                data[14] = 1;
                return true;
            }
            else {
                data[14] = 0;
                return  false;
            }
        }
        if (current == 16) {
            if (r3.isSelected()) {
                data[15] = 1;
                return true;
            }
            else {
                data[15] = 0;
                return  false;
            }
        }
        if (current == 17) {
            if (r4.isSelected()) {
                data[16] = 1;
                return true;
            }
            else {
                data[16] = 0;
                return  false;
            }
        }
        if (current == 18) {
            if (r4.isSelected()) {
                data[17] = 1;
                return true;
            }
            else {
                data[17] = 0;
                return  false;
            }
        }
        if (current == 19) {
            if (r2.isSelected()) {
                data[18] = 1;
                return true;
            }
            else {
                data[18] = 0;
                return  false;
            }
        }
        if (current == 20) {
            if (r3.isSelected()) {
                data[19] = 1;
                return true;
            }
            else {
                data[19] = 0;
                return  false;
            }
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(r1.isSelected() || r2.isSelected() || r3.isSelected() || r4.isSelected()){
            if(current<20)
                button.setText("NEXT");
        }
        if(e.getSource()==button){
            if((r1.isSelected() || r2.isSelected() || r3.isSelected() || r4.isSelected()) && button.getText().equals("NEXT")){
                if (check())
                    score++;
                if(checkSkipped)
                    current = now;
                if(!lastReached){
                    current++;
                }
                checkSkipped = false;
                set();
            }
            else if(button.getText().equals("SKIP")){
                JButton skipped = new JButton("Question NO. "+current);
                skipped.setBounds(0,(20*nums),200,20);
                skipped.addActionListener(this);
                ques[nums] = current;
                current++;
                nums++;
                lastSkipped = current;
                panel3.add(skipped);
                set();
                panel3.repaint();
            }
            else if(button.getText().equals("SUBMIT")){
                boolean areAllQuestionCompleted = (nums==0);
                if(!areAllQuestionCompleted){
                    int ans = JOptionPane.showConfirmDialog(null,"You have "+nums+" question remaining. Do you want to continue??","Connfirmation",JOptionPane.YES_NO_CANCEL_OPTION);
                    if (ans == 0){
                        timer = false;
                        JOptionPane.showMessageDialog(null, "Score: " + score);
                            Main_Frame.label.add(new last_page().panel);
                            panel1.setVisible(false);
                            Main_Frame.label.repaint();
                            try{
                                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root586");
                                Statement stm= conn.createStatement();
                                stm.execute("update candidates set TestScore='"+String.valueOf(score)+"'WHERE Cnic='"+no+"'");
                                String query = "update questions set Q1 ="+data[0]+",Q2="+data[1]+",Q3="+data[2]+",Q4="+data[3]+",Q5="+data[4]+",Q6="+data[5]+",Q7="+data[6]+",Q8="+data[7]+",Q9="+data[8]+",Q10="+data[9]+",Q11="+data[10]+",Q12="+data[11]+",Q13="+data[12]+",Q14="+data[13]+",Q15="+data[14]+",Q16="+data[15]+",Q17="+data[16]+",Q18="+data[17]+",Q19="+data[18]+",Q20="+data[19]+" WHERE Cnic='"+no+"'";
                                stm.execute(query);
                                System.out.println("Done");
                                //  JOptionPane.showMessageDialog(null,"Score inserted");
                                stm.close();
                            }
                            catch (Exception c){
                                JOptionPane.showMessageDialog(null,c);
                            }
                    }
                }
                else if(areAllQuestionCompleted){
                    timer = false;
                    double ssc = 0;
                    double fsc= 0;
                    double total_cpn = 0;
                    Connection conn;
                    try{
                        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root586");
                        Statement stm= conn.createStatement();
                        stm.execute("update candidates set TestScore='"+String.valueOf(score)+"'WHERE Cnic='"+no+"'");
                        String query = "update questions set Q1 ="+data[0]+",Q2="+data[1]+",Q3="+data[2]+",Q4="+data[3]+",Q5="+data[4]+",Q6="+data[5]+",Q7="+data[6]+",Q8="+data[7]+",Q9="+data[8]+",Q10="+data[9]+",Q11="+data[10]+",Q12="+data[11]+",Q13="+data[12]+",Q14="+data[13]+",Q15="+data[14]+",Q16="+data[15]+",Q17="+data[16]+",Q18="+data[17]+",Q19="+data[18]+",Q20="+data[19]+" WHERE Cnic='"+no+"'";
                        ResultSet set = stm.executeQuery("select SSCPercentage, FSCPercentage from candidates where Cnic='"+no+"'");
                        while (set.next()){
                            ssc = set.getDouble("SSCPercentage");
                            fsc = set.getDouble("FSCPercentage");
                        }
                        total_cpn =Math.round ((double)( (ssc * 0.1) + (fsc * 0.3) + (score * 5 * 0.6))*100.0)/100.0;
                        stm.execute("update candidates set CPN="+total_cpn+"WHERE Cnic='"+no+"'");
                        stm.execute(query);
                        System.out.println("Done");
                        stm.close();
                    }
                    catch (Exception c){
                        JOptionPane.showMessageDialog(null,c);
                    }
                    JOptionPane.showMessageDialog(null, "Score: " + score);
                    JOptionPane.showMessageDialog(null, "CPN: " + total_cpn);
                    Main_Frame.label.add(new last_page().panel);
                    panel1.setVisible(false);
                    Main_Frame.label.repaint();
                }
            }
            else {
                int ans = JOptionPane.showConfirmDialog(null,"You won't be able to answer it again, do you want to continue?","Warning",JOptionPane.YES_NO_CANCEL_OPTION);
                if(ans==0){
                    current = now;
                    set();
                }
            }
        }
        for(int i=0, y=1; i<lastSkipped; i++, y++){
            if(e.getActionCommand().equals("Question NO. " + y)){
                if(check()){
                    score++;
                }
                now = current;
                current = y;
                ((JButton) e.getSource()).setEnabled(false);
                nums--;
                set();
                button.setText("NEXT");
                checkSkipped = true;
            }
        }
    }
    public static void main(String[] args) {
        new Test_Page();
    }
}
