package com.pff.ipchat.view;

import com.jfoenix.controls.JFXTextField;
import com.pff.ipchat.DataManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Good_Pudge(Senya) on 02.07.2017.
 * From com.pff.ipchat.view
 */
public class ChangeNameController implements Initializable {
    @FXML
    private JFXTextField nicknameTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onAction() {
        String text = nicknameTextField.getText();
        if (!text.isEmpty()) {
            DataManager.setUserName(text);
            ComponentManager.labelName.setText(text);
        }
        ComponentManager.changeNicknameDialog.close();
    }

    @FXML
    public void onCancelAction() {
        ComponentManager.changeNicknameDialog.close();
    }
}
