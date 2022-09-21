package utils;

public enum DataMap {

	 Title("Title"),ZipCode("Zip Code"),TextDenied("Denied");

	private String value;

	private DataMap(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
