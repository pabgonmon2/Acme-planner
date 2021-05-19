package acme.testing.administrator.spamfilter.threshold;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorThresholdUpdateTest extends AcmePlannerTest{

	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateThresholdPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updatePositive(final int recordIndex, final String value) {        
        super.signIn("administrator", "administrator");
        
        super.clickOnMenu("Administrator", "Spam threshold");
        super.checkColumnHasValue(recordIndex, 0, "10.00");
        
        super.clickOnListingRecord(recordIndex);
        
        super.fillInputBoxIn("value", value);    

        super.clickOnSubmitButton("Update");
        
        super.checkSimplePath("/administrator/threshold/list");
                
        super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/update?id=67");

        super.checkInputBoxHasValue("value", value);
        
        super.fillInputBoxIn("value", "10.00");    
        super.clickOnSubmitButton("Update");
        super.signOut();
    }
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spamfilter/updateThresholdNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(15)
	public void updateNegative(final int recordIndex, final String value) {        
		super.signIn("administrator", "administrator");
        
        super.clickOnMenu("Administrator", "Spam threshold");
        super.checkColumnHasValue(recordIndex, 0, "10.00");
        super.clickOnListingRecord(recordIndex);
        super.fillInputBoxIn("value", value);    

        super.clickOnSubmitButton("Update");
        
        super.checkErrorsExist();
        
                
        super.driver.get("http://localhost:8080/Acme-Planner/administrator/threshold/update?id=67");

        super.checkInputBoxHasValue("value", "10.00");
        super.signOut();

    }

	

	
}
