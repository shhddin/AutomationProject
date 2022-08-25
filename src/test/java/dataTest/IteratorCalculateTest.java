package dataTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import utils.CalculateData;

public class IteratorCalculateTest extends BaseClass{
	
	@DataProvider(name="calculateIterator")
	public Iterator<CalculateData> calculateDataIterator(){
		List<CalculateData> list = new ArrayList<CalculateData>();
		CalculateData calculateData1=new CalculateData("19149", true, false, false, true, false, false);
		CalculateData calculateData2=new CalculateData("19111", false, true, false, false, true, false);
		list.add(calculateData1);
		list.add(calculateData2);
		return list.iterator();
		
	}
	
	@Test (dataProvider = "calculateIterator")
	public void calculateStepsTest(CalculateData calculateData) {
		homePage.homepageCalculate();
		calculate.calculateSteps(calculateData);
		
	}

}
