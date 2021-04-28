//package acme.features.administrator.spamfilter;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import acme.entities.roles.Administrator;
//import acme.entities.spamfilter.Threshold;
//import acme.framework.components.BasicCommand;
//import acme.framework.controllers.AbstractController;
//
//@Controller
//@RequestMapping("/administrator/threshold/")
//public class AdministratorThresholdController extends AbstractController<Administrator, Threshold>{
//	
//	@Autowired 
//	private AdministratorThresholdShowService thresholdService;
//	
//	
//	@PostConstruct
//	private void initialise() {
//		super.addBasicCommand(BasicCommand.LIST, this.thresholdService);
//		
//	}
//	
//}
