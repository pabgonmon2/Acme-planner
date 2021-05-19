package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/spamword/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String author, final String text, final String info, final String moment) {
		
		super.clickOnMenu("Anonymous", "Shout!");

		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", "");
		
		super.fillInputBoxIn("spamword", spamword);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Administrator", "Spam filter");
		super.checkColumnHasValue(0, 0, spamword);
		
		this.signOut();
	}
}
