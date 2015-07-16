package com.zwstudio.tools.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zwstudio.tools.models.HWDao;
import com.zwstudio.tools.services.Tool2Service;

import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@Controller
public class Tool2Controller implements Initializable {
    @FXML
    private TableColumn<HWDao, String> tcSheetName;
    @FXML
    private TableColumn<HWDao, String> tcId;
    @FXML
    private TableColumn<HWDao, String> tcMessage;
    @FXML
    private TableView<HWDao> tblMessages;
    @FXML
    private TextField tfFilter;
	
	@Autowired
	private Tool2Service service;
	
	private FilteredList<HWDao> filteredList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		tcSheetName.setCellValueFactory(d -> d.getValue().getSheetNameProp());
//		tcId.setCellValueFactory(d -> d.getValue().getIdProp());
//		tcMessage.setCellValueFactory(d -> d.getValue().getMessageProp());
//		
//		filteredList = new FilteredList<>(service.getMessages(), p -> true);
//		tfFilter.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable,
//					String oldValue, String newValue) {
//				filteredList.setPredicate(new Predicate<HWDao>() {
//					@Override
//					public boolean test(HWDao e) {
//						return newValue == null  || newValue.isEmpty() ||
//							e.getId().toLowerCase().contains(newValue.toLowerCase());
//					}
//				});
//			}
//		});
//		tblMessages.setItems(filteredList);
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
