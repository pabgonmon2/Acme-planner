package acme.features.administrator.spamfilter.spamword;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spamfilter.Spamword;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spamword/")
public class AdministratorSpamwordController extends AbstractController<Administrator, Spamword>{
	
	@Autowired
	private AdministratorSpamwordListService spamFilterService;
	
	@Autowired
	private AdministratorSpamwordUpdateService spamwordUpdateService;
	
	@Autowired
	private AdministratorSpamwordShowService spamwordShowService;

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.spamFilterService);
		super.addBasicCommand(BasicCommand.SHOW, this.spamwordShowService);
		super.addBasicCommand(BasicCommand.UPDATE, this.spamwordUpdateService);
	}
}
