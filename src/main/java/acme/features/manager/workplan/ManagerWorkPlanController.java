package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.workplans.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("manager/workplan/")
public class ManagerWorkPlanController extends AbstractController<Manager, Workplan> {
	
	@Autowired
	private ManagerWorkPlanListService listService;
	
	@Autowired
	private ManagerWorkPlanShowService showService;
	
	@Autowired
	private ManagerWorkPlanCreateService createService;
	
	@Autowired
	private ManagerWorkPlanDeleteService deleteService;

	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
	
}
