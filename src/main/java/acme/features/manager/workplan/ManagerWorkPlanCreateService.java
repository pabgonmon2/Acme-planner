package acme.features.manager.workplan;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplans.Workplan;
import acme.features.authenticated.manager.AuthenticatedManagerRepository;
import acme.features.manager.task.ManagerMyTasksRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkPlanCreateService implements AbstractCreateService<Manager, Workplan>  {

	@Autowired
	protected ManagerWorkPlanRepository repository;
	
	@Autowired
	protected AuthenticatedManagerRepository managerRepository;
	
	@Autowired
	protected ManagerMyTasksRepository tasksRepository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request!=null;
		return true;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		Collection<Task>t;
		t=this.tasksRepository.findMyTasks(request.getPrincipal().getActiveRoleId());
		model.setAttribute("tasksInsert", t);
		request.unbind(entity, model,"startDate", "endDate");
		
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request!=null;
		final Workplan wp=new Workplan();
		wp.setTasks(new HashSet<>());
		wp.setWorkLoad(0.0);
		wp.setEndDate();
		wp.setStartDate();
		return wp;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
	}

	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request!=null;
		assert entity!=null;
		entity.setManager(this.managerRepository.findManagerById(request.getPrincipal().getActiveRoleId()));
		final Set<Task> t=entity.getTasks();
		final List<String> ids=request.getModel().getAttribute("tasks1", List.class);
		
		for(final String id:ids) {
			t.add(this.tasksRepository.findTaskById(Integer.valueOf(id)));
		}
		
		entity.setTasks(t);
		entity.setWorkLoad();
		entity.setEndDate();
		entity.setStartDate();
		this.repository.save(entity);
	}

}
