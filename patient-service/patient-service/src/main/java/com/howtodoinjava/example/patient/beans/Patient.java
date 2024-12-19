package com.howtodoinjava.example.patient.beans;

import io.swagger.annotations.ApiModelProperty;

public class Patient {

	@ApiModelProperty(
			value = "first name of the patient",
			name = "name",
			dataType = "String",
			example = "Vatsal")
	private String name;
	@ApiModelProperty(
			value = "id of the patient",
			name = "id",
			dataType = "int",
			example = "111")
	private int id;
	
	public Patient(int id, String name) {
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
		return "Patient [name=" + name + ", id=" + id + "]";
	}

}
