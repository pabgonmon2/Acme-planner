package administrator.spamfilter.spamword;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordCreateTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/createSpamword.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Add spam word");

		
//		super.checkSimplePath("/administrator/threshold/show");
		
		super.checkInputBoxHasValue("spamword", "");
		
		super.fillInputBoxIn("spamword", spamword);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Administrator", "Spam filter");
		super.checkColumnHasValue(0, 0, spamword);
		
		this.signOut();
	}
	
	@Test
	@Order(15)
	public void createNegative() {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Add spam word");
		
		super.checkInputBoxHasValue("spamword", "");
		
		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
		
		this.signOut();
	}
	
	@Test
	@Order(20)
	public void listNegativeAnonymous() {
		super.navigate("/administrator/spamword/create", ""); ;
		super.checkErrorsExist();
	}
	
	@Test
	@Order(30)
	public void listNegativeManager() {
		this.signIn("manager2", "manager2");
		super.navigate("/administrator/spamword/create", ""); ;
		super.checkErrorsExist();
	}
}
