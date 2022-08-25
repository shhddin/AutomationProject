package utils;

public class CalculateData {
	private String zip;
	private boolean isOwn, isLease, isFinance;
	private boolean lessOne, twoToFive, moreThanFive;
	
	public CalculateData(String zip, boolean isOwn, boolean isLease, boolean isFinance, boolean lessOne,
			boolean twoToFive, boolean moreThanFive) {
		super();
		this.zip = zip;
		this.isOwn = isOwn;
		this.isLease = isLease;
		this.isFinance = isFinance;
		this.lessOne = lessOne;
		this.twoToFive = twoToFive;
		this.moreThanFive = moreThanFive;
	}

	public String getZip() {
		return zip;
	}

	public boolean isOwn() {
		return isOwn;
	}

	public boolean isLease() {
		return isLease;
	}

	public boolean isFinance() {
		return isFinance;
	}

	public boolean isLessOne() {
		return lessOne;
	}

	public boolean isTwoToFive() {
		return twoToFive;
	}

	public boolean isMoreThanFive() {
		return moreThanFive;
	}
	

}
