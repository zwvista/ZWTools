package com.zwstudio.tools.models;

import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HWDao {
	private String num;
	private String module;
	private String message;
	
    private JavaBeanStringProperty numProp;
    private JavaBeanStringProperty moduleProp;
    private JavaBeanStringProperty messageProp;
    
    public HWDao() {
		try {
			numProp = JavaBeanStringPropertyBuilder.create().bean(this).name("num").build();
			moduleProp = JavaBeanStringPropertyBuilder.create().bean(this).name("module").build();
			messageProp = JavaBeanStringPropertyBuilder.create().bean(this).name("message").build();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }
}
