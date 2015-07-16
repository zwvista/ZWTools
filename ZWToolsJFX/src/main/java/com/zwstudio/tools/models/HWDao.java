package com.zwstudio.tools.models;

import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HWDao {
	private String num;
	private String module;
	private String classLName;
	private String classPName;
	private String methodLName;
	private String methodPName;
	private String daoClass;
	private String sql;
	private String sqlString;
	
    private JavaBeanStringProperty numProp;
    private JavaBeanStringProperty moduleProp;
    private JavaBeanStringProperty classLNameProp;
    private JavaBeanStringProperty classPNameProp;
    private JavaBeanStringProperty methodLNameProp;
    private JavaBeanStringProperty methodPNameProp;
    private JavaBeanStringProperty daoClassProp;
    private JavaBeanStringProperty sqlProp;
    private JavaBeanStringProperty sqlStringProp;
    
    public HWDao() {
		try {
			numProp = JavaBeanStringPropertyBuilder.create().bean(this).name("num").build();
			moduleProp = JavaBeanStringPropertyBuilder.create().bean(this).name("module").build();
			classLNameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("classLName").build();
			classPNameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("classPName").build();
			methodLNameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("methodLName").build();
			methodPNameProp = JavaBeanStringPropertyBuilder.create().bean(this).name("methodPName").build();
			daoClassProp = JavaBeanStringPropertyBuilder.create().bean(this).name("daoClass").build();
			sqlProp = JavaBeanStringPropertyBuilder.create().bean(this).name("sql").build();
			sqlStringProp = JavaBeanStringPropertyBuilder.create().bean(this).name("sqlString").build();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }
}
