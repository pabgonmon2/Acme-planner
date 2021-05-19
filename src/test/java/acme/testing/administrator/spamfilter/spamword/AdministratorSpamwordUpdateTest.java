package acme.testing.administrator.spamfilter.spamword;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamwordUpdateTest extends AcmePlannerTest {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateSpamwordPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String spamword) {
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, "duro");
		
		super.clickOnListingRecord(recordIndex);		
		
		super.fillInputBoxIn("spamword", spamword);
		super.clickOnSubmitButton("Update");
		
		super.checkSimplePath("/administrator/spamword/list");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=66");
		super.checkInputBoxHasValue("spamword", spamword);

		super.fillInputBoxIn("spamword", "duro");
		super.clickOnSubmitButton("Update");
		
		this.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateSpamwordNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updateNegative(final int recordIndex, final String spamword) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Spam filter");
		
		super.checkColumnHasValue(recordIndex, 0, "duro");
		
		super.clickOnListingRecord(recordIndex);		
		
		super.fillInputBoxIn("spamword", spamword);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=66");
		super.checkInputBoxHasValue("spamword", "duro");
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void updateNegativeManager() {
		this.signIn("manager2", "manager2");
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=66");
		super.checkErrorsExist();

	}
	@Test
	@Order(30)
	public void updateNegativeAnonymous() {
		super.driver.get("http://localhost:8080/Acme-Planner/administrator/spamword/update?id=66");
		super.checkErrorsExist();
	}


}
