package com.howtodoinjava.example.medicalrecord.beans;

import io.swagger.annotations.ApiModelProperty;

public class MedicalRecord {

	@ApiModelProperty(
			value = "id of the medical record",
			name = "id",
			dataType = "int",
			example = "311")
	private int id;

	@ApiModelProperty(
			value = "id of the patient",
			name = "id",
			dataType = "int",
			example = "111")
	private int patientId;

	@ApiModelProperty(
			value = "id of the practitioner record",
			name = "id",
			dataType = "int",
			example = "211")
	private int practitionerId;

	public MedicalRecord(int id, int patientId, int practitionerId) {
		this.id = id;
		this.patientId = patientId;
		this.practitionerId = practitionerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPractitionerId() {
		return practitionerId;
	}

	public void setPractitionerId(int practitionerId) {
		this.practitionerId = practitionerId;
	}
}
