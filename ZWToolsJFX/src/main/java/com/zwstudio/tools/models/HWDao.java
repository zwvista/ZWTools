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
    
    @FunctionalInterface
    public interface FunctionThatThrows<T, R> {
        R apply(T t) throws NoSuchMethodException;
    }
    
    public HWDao() {
		FunctionThatThrows<String, JavaBeanStringProperty> builder =
				p -> JavaBeanStringPropertyBuilder.create().bean(this).name(p).build();
		try {
			numProp = builder.apply("num");
			moduleProp = builder.apply("module");
			classLNameProp = builder.apply("classLName");
			classPNameProp = builder.apply("classPName");
			methodLNameProp = builder.apply("methodLName");
			methodPNameProp = builder.apply("methodPName");
			daoClassProp = builder.apply("daoClass");
			sqlProp = builder.apply("sql");
			sqlStringProp = builder.apply("sqlString");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }
}
