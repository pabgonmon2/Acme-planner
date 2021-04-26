package acme.features.administrator.spamfilter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.shouts.SpamFilter;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spamfilter/")
public class AdministratorSFController extends AbstractController<Administrator, SpamFilter>{
	
	
	
	@Autowired 
	private AdministratorListSFService listService;
	@Autowired 
	private AdministratorShowSFService showService;
	
	
	@PostConstruct
	private void initialise() {
		
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
	
}

