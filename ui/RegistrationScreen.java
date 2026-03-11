package ui;

import service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationScreen extends JFrame {
    private JTextField nameField, ageField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton;
    private AuthService authService;

    public RegistrationScreen() {
        authService = new AuthService();
        setTitle("Personal Finance Manager - Registration");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);
        
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        
        panel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);
        
        registerButton = new JButton("Register");
        panel.add(registerButton);
        
        JButton backButton = new JButton("Back to Login");
        panel.add(backButton);
        
        add(panel);
        
        // Add action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                
                // Validate inputs
                if (name.isEmpty() || ageText.isEmpty() || username.isEmpty() || 
                    password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, 
                        "All fields are required", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, 
                        "Age must be a number", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, 
                        "Passwords do not match", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (authService.register(username, password, name, age)) {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, 
                        "Registration successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Return to login screen
                    LoginScreen loginScreen = new LoginScreen();
                    loginScreen.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegistrationScreen.this, 
                        "Username already taken", "Registration Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();
            }
        });
    }
}