package administrator.spamfilter.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdListTest extends AcmePlannerTest {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listThreshold.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String value) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam threshold");
		
		super.checkColumnHasValue(recordIndex, 0, value);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("value", value);
		
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void listNegativeAnonymous() {
		super.navigate("/administrator/threshold/list", ""); ;
		super.checkErrorsExist();
	}
	
	
	@Test
	@Order(30)
	public void listNegativeManager() {
		this.signIn("manager2", "manager2");
		super.navigate("/administrator/threshold/list", ""); ;
		super.checkErrorsExist();
	}
}
