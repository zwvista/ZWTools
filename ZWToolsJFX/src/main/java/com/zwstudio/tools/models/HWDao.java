package com.zwstudio.tools.models;

import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HWDao {
	private String num;
	private String sql;
	private String sqlString;
	private String daoClassName;
	private String daoMethodName;
	
	
    private JavaBeanStringProperty numProp;
    private JavaBeanStringProperty sqlProp;
    private JavaBeanStringProperty sqlStringProp;
    private JavaBeanStringProperty daoClassNameProp;
    private JavaBeanStringProperty daoMethodNameProp;
    
    @FunctionalInterface
    public interface FunctionThatThrows<T, R> {
        R apply(T t) throws NoSuchMethodException;
    }
    
    public HWDao() {
		FunctionThatThrows<String, JavaBeanStringProperty> builder =
				p -> JavaBeanStringPropertyBuilder.create().bean(this).name(p).build();
		try {
			numProp = builder.apply("num");
			sqlProp = builder.apply("sql");
			sqlStringProp = builder.apply("sqlString");
			daoClassNameProp = builder.apply("daoClassName");
			daoMethodNameProp = builder.apply("daoMethodName");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
    }
}
