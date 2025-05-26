package cloudchat2.Gui;

import javax.swing.JOptionPane;

public class NewLogin2 extends javax.swing.JPanel {

    private Main main;

    public NewLogin2(Main main) {
        this.main = main;
        initComponents(); // Call initComponents first to ensure all components are initialized

        jButton2.addActionListener(evt -> main.setRegister());
        jButton1.addActionListener(evt -> {
            String username = jTextField1.getText();
            String password = jTextField2.getText();
            if (main.loginSystem.loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, main.loginSystem.returnLoginStatus());
                main.setChatPanel(); // Navigate to the chat panel on successful login
            } else {
                JOptionPane.showMessageDialog(this, main.loginSystem.returnLoginStatus());
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton(); // Initialize jButton1 here
        jButton2 = new javax.swing.JButton(); // Initialize jButton2 here

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log In");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jLabel3.setText("Password");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        jCheckBox1.setText("Remember Me");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14));
        jLabel4.setText("Forgot Password?");

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jButton1.setText("Log In");

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 16));
        jButton2.setText("Don't have an account? Register here");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(22, 22, 22)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jButton2)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        this.setLayout(new java.awt.BorderLayout());
        this.add(jPanel2, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}