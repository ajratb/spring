package ru.wts.sboot.jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FOO_BAAR") //customed table name
public class FooBar { //foo_bar - default table name!
	
	@Id
    private long id;
    private String name;
    
	public FooBar(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
