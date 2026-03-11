package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import service.AuthService;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, forgotPasswordButton;
    private AuthService authService;

    public LoginScreen() {
        authService = new AuthService();
        setTitle("Personal Finance Manager - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        loginButton = new JButton("Login");
        panel.add(loginButton);
        
        registerButton = new JButton("Register");
        panel.add(registerButton);
        
        forgotPasswordButton = new JButton("Forgot Password");
        panel.add(forgotPasswordButton);
        
        add(panel);
        
        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginScreen.this, 
                        "Please enter both username and password", 
                        "Login Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (authService.login(username, password)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, 
                        "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Open dashboard
                    DashboardScreen dashboard = new DashboardScreen(username);
                    dashboard.setVisible(true);
                    dispose(); // Close login screen
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, 
                        "Invalid username or password", 
                        "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationScreen registrationScreen = new RegistrationScreen();
                registrationScreen.setVisible(true);
                dispose(); // Close login screen
            }
        });
        
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecoverPasswordScreen recoverScreen = new RecoverPasswordScreen();
                recoverScreen.setVisible(true);
                dispose(); // Close login screen
            }
        });
    }
}