package acme.features.authenticated.mytasks;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/authenticated/mytasks/")
public class ManagerMyTasksController extends AbstractController<Manager, Task>{
	
	@Autowired 
	private ManagerMyTasksListService listService;
	
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}
	
}
