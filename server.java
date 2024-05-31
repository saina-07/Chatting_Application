// import java.awt.Color;
// import javax.swing.*;

// public class server extends JFrame {

//     server() {
//         // Set the layout manager to null
//         setLayout(null);

//         // Create the panel
//         JPanel p1 = new JPanel();
//         p1.setBackground(new Color(7, 94, 84));
//         p1.setBounds(0, 0, 450, 60); // Set bounds for the panel

//         // Add the panel to the frame
//         add(p1);

//         // Set the frame size and other properties
//         setSize(450, 600);
//         getContentPane().setBackground(Color.WHITE); // Set background color to white
//         setLocation(200, 30);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     public static void main(String[] args) {
//         new server();
//     }
// }



// -----------------------------------------------------------------------------------------------------------------------------

// package chatting_application;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.io.*;


public class server  implements ActionListener{

    JTextField text;
    JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f= new JFrame();
    static DataOutputStream dout;

    server(){
        f.setLayout(null);
        JPanel p1= new JPanel(); //Panel gets created on top of the frame created
        p1.setBackground(new Color(0,0,0));
        p1.setBounds(0,0, 450, 60);
        p1.setLayout(null);
        f.add(p1);
        

         //Making Back arrow 
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel back= new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

         //Making Profile pic icon
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/6.png"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon i6= new ImageIcon(i5);
        JLabel profile= new JLabel(i6);
        profile.setBounds(40, 5, 50, 50);
        p1.add(profile);
        
         //Making Video icon
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon i9= new ImageIcon(i8);
        JLabel video= new JLabel(i9);
        video.setBounds(310, 20, 30, 30);
        p1.add(video);

         //Making Phone icon
        ImageIcon ia=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image ib=ia.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon ic= new ImageIcon(ib);
        JLabel phone= new JLabel(ic);
        phone.setBounds(360, 20, 30, 30);
        p1.add(phone);

         //Making 3 dots or button-morevert icon
        ImageIcon id=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image ie=id.getImage().getScaledInstance(10,20,Image.SCALE_SMOOTH);
        ImageIcon ig= new ImageIcon(ie);
        JLabel morevert= new JLabel(ig);
        morevert.setBounds(400, 25, 10, 20);
        p1.add(morevert);

         //Making Name label
        JLabel name= new JLabel("Rachel");
        name.setBounds(100, 1, 100, 50);
        name.setFont(new Font("SAN_SERIFF",Font.BOLD, 18));
        name.setForeground(Color.white);
        p1.add(name);
        
        //Making Active Now label
        JLabel status= new JLabel("Active Now");
        status.setBounds(100, 17, 100, 50);
        status.setFont(new Font("SAN_SERIFF", Font.PLAIN, 13));
        status.setForeground(Color.lightGray);
        p1.add(status);

        a1= new JPanel();
        a1.setBounds(5, 67, 425, 450);
        a1.setLayout(new BorderLayout());
        f.add(a1);

        text = new JTextField();
        text.setBounds(5, 525, 330, 30);
        text.setFont(new Font("SAN_SERIFF", Font.PLAIN, 16));
        f.add(text);

        JButton send = new JButton("Send");
        send.addActionListener(this);
        send.setBounds(338, 525, 90, 30);
        send.setFont(new Font("SAN_SERIFF", Font.BOLD, 13));
        send.setForeground(Color.WHITE);
        send.setBackground(Color.BLACK);
        f.add(send);


        f.setSize(450, 600);
        f.getContentPane().setBackground(Color.pink);
        f.setLocation(200, 30);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        try {
        String out = text.getText();

        JPanel p2 = formatLabel(out);

        JPanel right= new JPanel( new BorderLayout());
        right.add(p2, BorderLayout.LINE_END); // here instead of "p2" we couldnt directly add "out" because "out" is a string and cant be directly added so we first added/passed it to/through Jlabel and then added the jlabel on the Jpanel we created. Also "LINE_END"  is used so that we can whatever message we pass it gets entered at the end of that line.This ensure all the texts to appear to the right side.

        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15)); // Here the "15" is the vertical gap between the two texts. These two lines are to put all the texts texted and sent one after other downward in vertical direction for the sender's pov  

        a1.add(vertical,BorderLayout.PAGE_START);

        text.setText(""); //To clear the message from the text field after sending the message.
        
            dout.writeUTF(out);
        f.repaint();
        f.revalidate();
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    public static JPanel formatLabel (String out){
        JPanel panel= new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");

        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(0, 0, 0));
        output.setForeground(Color.WHITE);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);

        //Adding time element to know the time at which the message was sent
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;

    }
    public static void main(String[]args){
        new server();

        try{
            ServerSocket skt = new ServerSocket(6001);
            while(true){
                Socket s= skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                while(true){
                    String msg=din.readUTF();
                    JPanel panel = formatLabel(msg);
                    JPanel left =new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();


                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
// -----------------------------------------------------------------------------------------------------------------------------

// import java.awt.Color;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.awt.RenderingHints;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.awt.image.BufferedImage;
// import javax.swing.*;

// public class server extends JFrame implements ActionListener {
//     server() {
//         setLayout(null);

//         // Create and configure the top panel
//         JPanel p1 = new JPanel();
//         p1.setBackground(new Color(7, 94, 84));
//         p1.setBounds(0, 0, 450, 60);
//         p1.setLayout(null);
//         add(p1);

//         // Add back icon
//         ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
//         JLabel back = new JLabel(new ImageIcon(getScaledImage(backIcon.getImage(), 25, 25)));
//         back.setBounds(5, 20, 25, 25);
//         p1.add(back);

//         back.addMouseListener(new MouseAdapter() {
//             public void mouseClicked(MouseEvent ae) {
//                 System.exit(0);
//             }
//         });

//         // Add profile picture
//         ImageIcon profileIcon = new ImageIcon(ClassLoader.getSystemResource("icons/4.png"));
//         JLabel profile = new JLabel(new ImageIcon(getScaledImage(profileIcon.getImage(), 50, 50)));
//         profile.setBounds(40, 5, 50, 50);
//         p1.add(profile);

//         // Configure the frame
//         setSize(450, 600);
//         getContentPane().setBackground(Color.PINK);
//         setLocation(200, 30);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     private Image getScaledImage(Image srcImg, int w, int h) {
//         BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//         Graphics2D g2 = resizedImg.createGraphics();

//         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//         g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//         g2.drawImage(srcImg, 0, 0, w, h, null);
//         g2.dispose();

//         return resizedImg;
//     }

//     public void actionPerformed(ActionEvent ae) {
//         // Implement any required action handling here
//     }

//     public static void main(String[] args) {
//         new server();
//     }
// }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// import javax.swing.*;
// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Image;
// import java.awt.event.*;

// public class server extends JFrame implements ActionListener {
//     JTextField text;
//     JPanel a1;

//     public server() {
//         setLayout(null);
//         JPanel p1 = new JPanel(); // Panel gets created on top of the frame created
//         p1.setBackground(new Color(0, 0, 0));
//         p1.setBounds(0, 0, 450, 60);
//         p1.setLayout(null);
//         add(p1);

//         // Making Back arrow
//         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
//         Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
//         ImageIcon i3 = new ImageIcon(i2);
//         JLabel back = new JLabel(i3);
//         back.setBounds(5, 20, 25, 25);
//         p1.add(back);

//         back.addMouseListener(new MouseAdapter() {
//             public void mouseClicked(MouseEvent ae) {
//                 System.exit(0);
//             }
//         });

//         // Making Profile pic icon
//         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/6.png"));
//         Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//         ImageIcon i6 = new ImageIcon(i5);
//         JLabel profile = new JLabel(i6);
//         profile.setBounds(40, 5, 50, 50);
//         p1.add(profile);

//         // Making Video icon
//         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
//         Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//         ImageIcon i9 = new ImageIcon(i8);
//         JLabel video = new JLabel(i9);
//         video.setBounds(310, 20, 30, 30);
//         p1.add(video);

//         // Making Phone icon
//         ImageIcon ia = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
//         Image ib = ia.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//         ImageIcon ic = new ImageIcon(ib);
//         JLabel phone = new JLabel(ic);
//         phone.setBounds(360, 20, 30, 30);
//         p1.add(phone);

//         // Making 3 dots or button-morevert icon
//         ImageIcon id = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
//         Image ie = id.getImage().getScaledInstance(10, 20, Image.SCALE_SMOOTH);
//         ImageIcon ig = new ImageIcon(ie);
//         JLabel morevert = new JLabel(ig);
//         morevert.setBounds(400, 25, 10, 20);
//         p1.add(morevert);

//         // Making Name label
//         JLabel name = new JLabel("Rachel");
//         name.setBounds(100, 1, 100, 50);
//         name.setFont(new Font("SansSerif", Font.BOLD, 18));
//         name.setForeground(Color.white);
//         p1.add(name);

//         // Making Active Now label
//         JLabel status = new JLabel("Active Now");
//         status.setBounds(100, 17, 100, 50);
//         status.setFont(new Font("SansSerif", Font.PLAIN, 13));
//         status.setForeground(Color.lightGray);
//         p1.add(status);

//         a1 = new JPanel();
//         a1.setBounds(5, 67, 425, 450);
//         add(a1);

//         text = new JTextField();
//         text.setBounds(5, 525, 330, 30);
//         text.setFont(new Font("SansSerif", Font.PLAIN, 16));
//         add(text);

//         JButton send = new JButton("Send");
//         send.addActionListener(this);
//         send.setBounds(338, 525, 90, 30);
//         send.setFont(new Font("SansSerif", Font.BOLD, 13));
//         send.setForeground(Color.WHITE);
//         send.setBackground(Color.BLACK);
//         add(send);

//         setSize(450, 600);
//         getContentPane().setBackground(Color.pink);
//         setLocation(200, 30);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     public void actionPerformed(ActionEvent ae) {
//         String out = text.getText();
//         System.out.println(out);
//     }

//     public static void main(String[] args) {
//         new server();
//     }
// }
///////////////////////////////////////////////////////------------------------///////////////////////////////////////////////////////////
// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import java.awt.*;
// import java.awt.event.*;

// public class server extends JFrame implements ActionListener {

//     JTextField text;
//     JPanel a1;
//     Box vertical = Box.createVerticalBox();

//     server() {
//         setLayout(null);

//         JPanel p1 = new JPanel(); // Panel gets created on top of the frame created
//         p1.setBackground(new Color(0, 0, 0));
//         p1.setBounds(0, 0, 450, 60);
//         p1.setLayout(null);
//         add(p1);

//         // Making Back arrow
//         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
//         Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
//         ImageIcon i3 = new ImageIcon(i2);
//         JLabel back = new JLabel(i3);
//         back.setBounds(5, 20, 25, 25);
//         p1.add(back);

//         back.addMouseListener(new MouseAdapter() {
//             public void mouseClicked(MouseEvent ae) {
//                 System.exit(0);
//             }
//         });

//         // Making Profile pic icon
//         ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/6.png"));
//         Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//         ImageIcon i6 = new ImageIcon(i5);
//         JLabel profile = new JLabel(i6);
//         profile.setBounds(40, 5, 50, 50);
//         p1.add(profile);

//         // Making Video icon
//         ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
//         Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//         ImageIcon i9 = new ImageIcon(i8);
//         JLabel video = new JLabel(i9);
//         video.setBounds(310, 20, 30, 30);
//         p1.add(video);

//         // Making Phone icon
//         ImageIcon ia = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
//         Image ib = ia.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
//         ImageIcon ic = new ImageIcon(ib);
//         JLabel phone = new JLabel(ic);
//         phone.setBounds(360, 20, 30, 30);
//         p1.add(phone);

//         // Making 3 dots or button-morevert icon
//         ImageIcon id = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
//         Image ie = id.getImage().getScaledInstance(10, 20, Image.SCALE_SMOOTH);
//         ImageIcon ig = new ImageIcon(ie);
//         JLabel morevert = new JLabel(ig);
//         morevert.setBounds(400, 25, 10, 20);
//         p1.add(morevert);

//         // Making Name label
//         JLabel name = new JLabel("Rachel");
//         name.setBounds(100, 1, 100, 50);
//         name.setFont(new Font("SansSerif", Font.BOLD, 18));
//         name.setForeground(Color.white);
//         p1.add(name);

//         // Making Active Now label
//         JLabel status = new JLabel("Active Now");
//         status.setBounds(100, 17, 100, 50);
//         status.setFont(new Font("SansSerif", Font.PLAIN, 13));
//         status.setForeground(Color.lightGray);
//         p1.add(status);

//         a1 = new JPanel();
//         a1.setBounds(5, 67, 425, 450);
//         a1.setLayout(new BorderLayout());
//         add(a1);

//         text = new JTextField();
//         text.setBounds(5, 525, 330, 30);
//         text.setFont(new Font("SansSerif", Font.PLAIN, 16));
//         add(text);

//         JButton send = new JButton("Send");
//         send.addActionListener(this);
//         send.setBounds(338, 525, 90, 30);
//         send.setFont(new Font("SansSerif", Font.BOLD, 13));
//         send.setForeground(Color.WHITE);
//         send.setBackground(Color.BLACK);
//         add(send);

//         setSize(450, 600);
//         getContentPane().setBackground(Color.pink);
//         setLocation(200, 30);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setVisible(true);
//     }

//     public void actionPerformed(ActionEvent ae) {
//         String out = text.getText();

//         JPanel p2 = formatLabel(out);

//         JPanel right = new JPanel(new BorderLayout());
//         right.add(p2, BorderLayout.LINE_END);

//         vertical.add(right);
//         vertical.add(Box.createVerticalStrut(15));

//         a1.add(vertical, BorderLayout.PAGE_START);

//         text.setText("");

//         repaint();
//         revalidate();
//     }

//     public static JPanel formatLabel(String out) {
//         JPanel panel = new JPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
//         output.setFont(new Font("Tahoma", Font.PLAIN, 16));
//         output.setBackground(new Color(0, 0, 0));
//         output.setForeground(Color.WHITE);
//         output.setOpaque(true);
//         output.setBorder(new EmptyBorder(15, 15, 15, 50));
//         panel.add(output);

//         return panel;
//     }

//     public static void main(String[] args) {
//         new server();
//     }
// }
