package utils;

public class AutoData {
	private String expectedTitle;
	private String zipcodesString;
	private String  expectedH1;
	
	public AutoData(String expectedTitle, String zipcodesString, String expectedH1) {
		this.expectedTitle = expectedTitle;
		this.zipcodesString = zipcodesString;
		this.expectedH1 = expectedH1;
	}
	
	public String getExpectedTitle() {
		return expectedTitle;
	}

	public String getZipcodesString() {
		return zipcodesString;
	}

	public String getExpectedH1() {
		return expectedH1;
	}

	

}
