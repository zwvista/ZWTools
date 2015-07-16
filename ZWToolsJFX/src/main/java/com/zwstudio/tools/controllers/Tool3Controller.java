package com.zwstudio.tools.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zwstudio.tools.services.Tool31Service;
import com.zwstudio.tools.services.Tool32Service;

@Controller
public class Tool3Controller implements Initializable {
	@Autowired
	Tool31Service service1;
	@Autowired
	Tool32Service service2;
	
    @FXML
    private Button btnConvertMssyaban;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

    @FXML
    void btnConvertTestOnAction(ActionEvent event) {
    	try {
			service1.convertTest();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnConvertHonbanOnAction(ActionEvent event) {
    	try {
			service1.convertHonban();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnCountDocManualOnAction(ActionEvent event) {
    	try {
			service2.countDocManual();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
