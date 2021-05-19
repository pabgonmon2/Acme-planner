package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(35)
	public void filtrosShoutPositive() {
		
		super.clickOnMenu("Anonymous", "Shouts!");
		
		super.checkInputBoxHasValue("author", "John Doe");
		super.checkInputBoxHasValue("text", "Lorem ipsum!");
		super.checkInputBoxHasValue("info", "http://example.org");
		
		super.fillInputBoxIn("author", "Pablo");
		super.fillInputBoxIn("text", "Hola como estais?");
		super.fillInputBoxIn("info", "");
		
		super.clickOnSubmitButton("Shout!");
		
		super.clickOnMenu("Anonymous", "List shout");
		
		super.checkColumnHasValue(0, 1, "Pablo");
		super.checkColumnHasValue(0, 2, "Hola como estais?");
		super.checkColumnHasValue(0, 3, "");
		
	}
	
	@Test
	@Order(40)
	public void filtrosShoutNegative() {
		
		super.clickOnMenu("Anonymous", "Shouts!");
		
		super.checkInputBoxHasValue("author", "John Doe");
		super.checkInputBoxHasValue("text", "Lorem ipsum!");
		super.checkInputBoxHasValue("info", "http://example.org");
		
		super.fillInputBoxIn("author", "Sex");
		super.fillInputBoxIn("text", "Hola como estais?");
		super.fillInputBoxIn("info", "");
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();
		
	}
}
