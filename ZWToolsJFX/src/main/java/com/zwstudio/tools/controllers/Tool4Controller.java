package com.zwstudio.tools.controllers;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zwstudio.tools.services.Tool4Service;

@Controller
public class Tool4Controller implements Initializable {
	
	@Autowired
	Tool4Service service;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
