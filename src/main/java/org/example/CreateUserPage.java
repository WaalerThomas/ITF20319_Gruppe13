package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserPage implements UIPageInterface {
    private JPanel mainPanel;
    private JButton cancelBtn;

    public CreateUserPage(MainWindow mainWindow) {

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainWindow.setPage("LoginPage");
            }
        });
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
