package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/manager/task/")
public class ManagerMyTasksController extends AbstractController<Manager, Task>{
	
	@Autowired 
	private ManagerMyTasksListService listService;
	
	@Autowired
	private ManagerMyTasksCreateService createService;

	@Autowired 
	private ManagerTaskShowService showService;
	
	@Autowired
	private ManagerMyTaskDeleteService deleteService;

	
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}
	
}
