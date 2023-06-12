package com.example.sgosirvar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerInterface {
    private Label BusyLabel;

    @FXML
    private TextArea BusyUsers;

    @FXML
    private Label OfflineLabel;

    @FXML
    private TextArea OfflineUsers;

    @FXML
    private Label onlineLabel;

    @FXML
    private TextArea onlineUsers;


    public ServerInterface() {
        new Thread() {
            public void run() {
                try {
                    ResultSet results = Server.Users("Offline");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        OfflineUsers.setText(OfflineUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ResultSet results = Server.Users("Busy");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        BusyUsers.setText(BusyUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    ResultSet results = Server.Users("online");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        onlineUsers.setText(onlineUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void UpdateOnlineUsers() throws SQLException {
        ResultSet results = Server.Users("online");
        while (results.next()) {
            String UserName = results.getString(1);
            onlineUsers.setText(onlineUsers.getText() + "\n" + UserName);
        }
    }

    public void UpdateOfflineUsers() throws SQLException {
        ResultSet results = Server.Users("Offline");
        while (results.next()) {
            String UserName = results.getString(1);
            onlineUsers.setText(onlineUsers.getText() + "\n" + UserName);
        }
    }
        public void UpdateBusyUsers () throws SQLException {
            ResultSet results = Server.Users("Busy");
            while (results.next()) {
                String UserName = results.getString(1);
                onlineUsers.setText(onlineUsers.getText() + "\n" + UserName);
            }

        }
    }

