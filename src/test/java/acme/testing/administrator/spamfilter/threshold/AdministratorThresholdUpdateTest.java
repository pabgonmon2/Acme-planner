package acme.testing.administrator.spamfilter.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listThreshold.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String value) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, value);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("value", value);
		
		super.fillInputBoxIn("value", "15.00");
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		super.checkColumnHasValue(recordIndex, 0, "15.00");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("value", "15.00");
		super.fillInputBoxIn("value", value);
		super.clickOnSubmitButton("Update");
		
		this.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listThreshold.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String value) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, value);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("value", value);
		
		super.fillInputBoxIn("value", "asd");
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist("value");

	}
}
