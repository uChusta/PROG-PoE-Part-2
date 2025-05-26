package cloudchat2.Gui;

import cloudchat2.Logic.Login;
import cloudchat2.Logic.MessageSystem; // Import MessageSystem
import javax.swing.SwingUtilities;

public final class Main extends javax.swing.JFrame {

    private NewLogin2 loginPanel;
    private NewRegistration registerPanel;
    private ChatPanel chatPanel; // Declare chatPanel
    public Login loginSystem = new Login();
    public MessageSystem messageSystem = new MessageSystem(); // Initialize MessageSystem

    public void setLogin() {
        loginPanel = new NewLogin2(this);
        jPanel2.removeAll();
        jPanel2.add(loginPanel);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public void setRegister() {
        registerPanel = new NewRegistration(this);
        jPanel2.removeAll();
        jPanel2.add(registerPanel);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    // New method to set the ChatPanel
    public void setChatPanel() {
        chatPanel = new ChatPanel(this);
        jPanel2.removeAll();
        jPanel2.add(chatPanel);
        SwingUtilities.updateComponentTreeUI(jPanel2);
    }

    public Main() {
        initComponents();
        setLogin(); // Default view is login
    }

    @SuppressWarnings({"unchecked", "Convert2Lambda"})
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(261, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome to QuickCHat");

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Login button action
                setLogin();
            }
        });
        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButton2.setText("Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //Register button action
                setRegister();
            }
        });

        //Exit button
        jButton3.setFont(new java.awt.Font("Segoe UI Black", 1, 18));
        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0); // Terminates the application
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // New button
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jButton1)
                .addGap(18, 18, 18) // Reduced gap
                .addComponent(jButton2)
                .addGap(18, 18, 18) // Gap for new button
                .addComponent(jButton3) // New Exit button
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Adjusted to give space
        );

        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3; // Declare the new button
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
}