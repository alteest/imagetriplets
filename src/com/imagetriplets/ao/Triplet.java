package com.imagetriplets.ao;

public class Triplet {
	private String name;
	private String similar;
	private String negative;
	
	public Triplet(String name, String similar, String negative) {
		this.setName(name);
		this.setSimilar(similar);
		this.setNegative(negative);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimilar() {
		return similar;
	}

	public void setSimilar(String similar) {
		this.similar = similar;
	}

	public String getNegative() {
		return negative;
	}

	public void setNegative(String negative) {
		this.negative = negative;
	}

}
