package com.zwstudio.tools.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zwstudio.tools.models.HWDao;
import com.zwstudio.tools.services.Tool1Service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Controller
public class Tool1Controller implements Initializable {

    @FXML
    private TableColumn<HWDao, String> tcNum;
    @FXML
    private TableColumn<HWDao, String> tcModule;
    @FXML
    private TableView<HWDao> tblHWDaos;
    @FXML
    private TextField tfFilter;

	@Autowired
	private Tool1Service service;
	
	private FilteredList<HWDao> filteredList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcNum.setCellValueFactory(d -> d.getValue().getNumProp());
		tcModule.setCellValueFactory(d -> d.getValue().getModuleProp());
		
		filteredList = new FilteredList<>(service.getDaos(), p -> true);
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				filteredList.setPredicate(new Predicate<HWDao>() {
					@Override
					public boolean test(HWDao e) {
						return newValue == null  || newValue.isEmpty();
//								||
//							e.getId().toLowerCase().contains(newValue.toLowerCase());
					}
				});
			}
		});
		tblHWDaos.setItems(filteredList);
	}
	
    @FXML
    void btnLoadOnAction(ActionEvent event) {
    	try {
			service.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
