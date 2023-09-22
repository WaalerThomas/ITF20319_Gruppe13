package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements UIPageInterface {
    private JPanel mainPanel;
    private JButton userLoginBtn;
    private JButton guideLoginBtn;
    private JButton adminLoginBtn;
    private JButton newAccountBtn;

    public LoginPage(MainWindow mainWindow) {
        newAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.setPage("CreateUserPage");
            }
        });
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
