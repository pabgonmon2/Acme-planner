package acme.testing.administrator.spamfilter.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordUpdateTest extends AcmePlannerTest {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, spamword);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", spamword);
		
		super.fillInputBoxIn("spamword", "prueba");
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Spam filter");
		super.checkColumnHasValue(recordIndex, 0, "prueba");
		
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("spamword", "prueba");
		super.fillInputBoxIn("spamword", spamword);
		super.clickOnSubmitButton("Update");
		
		this.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateNegative(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, spamword);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", spamword);
		
		super.fillInputBoxIn("spamword", "");
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();
		super.clickOnMenu("Administrator", "Spam filter");
		super.checkColumnHasValue(recordIndex, 0, spamword);
		
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void updateNegativeManager() {
		this.signIn("manager2", "manager2");
		super.navigate("/administrator/spamword/show", "id=59"); ;
		super.checkErrorsExist();

	}
	@Test
	@Order(30)
	public void updateNegativeAnonymous() {
		super.navigate("/administrator/spamword/show", "id=59"); ;
		super.checkErrorsExist();
	}
}
