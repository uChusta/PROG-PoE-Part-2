package cloudchat2.Gui;

import javax.swing.JOptionPane;

public class NewRegistration extends javax.swing.JPanel {

    private Main main;

    public NewRegistration(Main main) {
        this.main = main;
        initComponents();

        jButton2.addActionListener(evt -> main.setLogin());
        jButton1.addActionListener(evt -> {
            String username = jTextField1.getText();
            String nameInput = jTextField2.getText(); // This now captures the "Name"
            String phone = jTextField3.getText();
            String password = jTextField4.getText();
            
            // Pass nameInput as firstName, and a default for lastName
            String result = main.loginSystem.registerUser(username, password, phone, nameInput, "User");
            JOptionPane.showMessageDialog(this, result);
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel(); // Changed to "Name"
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register New User");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        jLabel2.setText("Username");

        jTextField1.setFont(new java.awt.Font("Segoe UI Black", 1, 18));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jLabel3.setText("Name"); // Changed from "Email" to "Name"

        jTextField2.setFont(new java.awt.Font("Segoe UI Black", 1, 18));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jLabel4.setText("Cellphone Number");

        jTextField3.setFont(new java.awt.Font("Segoe UI Black", 1, 18));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jLabel5.setText("Password");

        jTextField4.setFont(new java.awt.Font("Segoe UI Black", 1, 18));

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jButton1.setText("Register");

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jButton2.setText("Have an account? Log in here");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(20, 20, 20)
                .addComponent(jButton2)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        this.setLayout(new java.awt.BorderLayout());
        this.add(jPanel1, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
}