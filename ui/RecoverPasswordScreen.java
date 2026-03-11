package ui;

import model.User;
import service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverPasswordScreen extends JFrame {
    private JTextField usernameField, nameField, ageField;
    private JButton recoverButton, backButton;
    private JLabel passwordLabel;
    private AuthService authService;

    public RecoverPasswordScreen() {
        authService = new AuthService();
        setTitle("Personal Finance Manager - Recover Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);
        
        panel.add(new JLabel("Password:"));
        passwordLabel = new JLabel("");
        panel.add(passwordLabel);
        
        recoverButton = new JButton("Show Password");
        panel.add(recoverButton);
        
        backButton = new JButton("Back to Login");
        panel.add(backButton);
        
        add(panel);
        
        // Add action listeners
        recoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String name = nameField.getText();
                String ageText = ageField.getText();
                
                if (username.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                    JOptionPane.showMessageDialog(RecoverPasswordScreen.this, 
                        "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int age;
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RecoverPasswordScreen.this, 
                        "Age must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                User user = authService.recoverPassword(username, name, age);
                if (user != null) {
                    passwordLabel.setText(user.getPassword());
                } else {
                    JOptionPane.showMessageDialog(RecoverPasswordScreen.this, 
                        "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
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