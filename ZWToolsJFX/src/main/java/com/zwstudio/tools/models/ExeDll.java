package com.zwstudio.tools.models;

import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExeDll {
	private String kind;
	private String sheetName;
	private String num;
	private String name;
	private String id;
	private String releaseTo;
	
    private JavaBeanStringProperty kindProp;
    private JavaBeanStringProperty sheetNameProp;
    private JavaBeanStringProperty numProp;
    private JavaBeanStringProperty nameProp;
    private JavaBeanStringProperty idProp;
    private JavaBeanStringProperty releaseToProp;
    
    public ExeDll() {
		try {
			kindProp = JavaBeanStringPropertyBuilder.create().bean(this).name("kind").build();
			sheetNameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("sheetName").build();
			numProp = JavaBeanStringPropertyBuilder.create().bean(this).name("num").build();
			nameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("name").build();
			idProp = JavaBeanStringPropertyBuilder.create().bean(this).name("id").build();
			releaseToProp = JavaBeanStringPropertyBuilder.create().bean(this).name("releaseTo").build();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }
}
