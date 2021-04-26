package acme.features.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.dashboard.Dashboard;
import acme.entities.roles.Administrator;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/administrator/dashboard/")
public class AdministratorTaskController extends AbstractController<Administrator, Dashboard>{
	
	@Autowired 
	private AdministratorDashBoardService dashboardListService;
	
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.dashboardListService);
	}
	
}
