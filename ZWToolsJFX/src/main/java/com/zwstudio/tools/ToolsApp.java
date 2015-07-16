package com.zwstudio.tools;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ToolsApp extends Application {
	private AnnotationConfigApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ToolsApp.class, args);
        launch(args);
    }
    
	@Override
	public void init() throws Exception {
		super.init();
		context = new AnnotationConfigApplicationContext(ToolsApp.class);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
	}
	
	public <T> T load(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> param) {
				return context.getBean(param);
			}
		});
        return loader.load();
    }

	@Override
	public void start(Stage stage) throws Exception {
		Parent page = load("/main.fxml");
		Scene scene = new Scene(page);
		scene.getStylesheets().add(getClass().getResource("/main.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}
