package com.howtodoinjava.example.practitioner.beans;

import io.swagger.annotations.ApiModelProperty;

public class Practitioner {

	@ApiModelProperty(
			value = "first name of the practitioner",
			name = "name",
			dataType = "String",
			example = "Vatsal")
	private String name;
	@ApiModelProperty(
			value = "id of the practitioner",
			name = "id",
			dataType = "int",
			example = "211")
	private int id;
	
	public Practitioner(int id, String name) {
		super();
		this.id=id;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Practitioner [name=" + name + ", id=" + id + "]";
	}

}
