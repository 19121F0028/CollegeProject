package com.project.myapp.payload.response;

public class BatchRespose {

	private Long id;
	private String batchName;
	private int studentCount;
	public BatchRespose() {
		super();
	}
	public BatchRespose(Long id, String batchName, int studentCount) {
		super();
		this.id = id;
		this.batchName = batchName;
		this.studentCount = studentCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public int getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}
	
}
