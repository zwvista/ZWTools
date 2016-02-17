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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller
public class Tool1Controller implements Initializable {

    @FXML
    private TableColumn<HWDao, String> tcNum;
    @FXML
    private TableColumn<HWDao, String> tcDaoClassName;
    @FXML
    private TableColumn<HWDao, String> tcDaoMethodName;
    @FXML
    private TableView<HWDao> tblHWDaos;
    @FXML
    private TextField tfFilter;
    @FXML
    private TextArea taSql;
    @FXML
    private TextArea taSqlString;

	@Autowired
	private Tool1Service service;
	
	private FilteredList<HWDao> filteredList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcNum.setCellValueFactory(d -> d.getValue().getNumProp());
		tcDaoClassName.setCellValueFactory(d -> d.getValue().getDaoClassNameProp());
		tcDaoMethodName.setCellValueFactory(d -> d.getValue().getDaoMethodNameProp());
		
		tblHWDaos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HWDao>() {
			@Override
			public void changed(ObservableValue<? extends HWDao> observable, HWDao oldValue, HWDao newValue) {
				String sql = newValue == null ? null : newValue.getSql();
				String sqlString = newValue == null ? null : newValue.getSqlString();
				taSql.setText(sql);
				taSqlString.setText(sqlString);
			}
		});
		
		filteredList = new FilteredList<>(service.getDaos(), p -> true);
		ChangeListener<String> listener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				filteredList.setPredicate(new Predicate<HWDao>() {
					@Override
					public boolean test(HWDao e) {
						String vf = tfFilter.getText().toLowerCase();
						return  e.getNum().toLowerCase().contains(vf);
					}
				});
			}
		};
		tfFilter.textProperty().addListener(listener);
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
	
    @FXML
    void btnUpdateSqlOnAction(ActionEvent event) {
    	int k = tblHWDaos.getSelectionModel().getSelectedIndex() + 1;
    	String sql = taSql.getText();
    	try {
			service.updateSql(k, sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
