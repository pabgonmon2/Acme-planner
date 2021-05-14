package administrator.spamfilter.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordListTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/listSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String value) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, value);
		
		super.clickOnListingRecord(recordIndex);
		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("value", value);
		
		this.signOut();
	}
}
