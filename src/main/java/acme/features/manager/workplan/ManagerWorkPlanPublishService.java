package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplans.Workplan;
import acme.features.manager.task.ManagerMyTasksRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkPlanPublishService implements AbstractUpdateService<Manager, Workplan>{

	@Autowired
	ManagerMyTasksRepository tasksRepository;
	
	@Autowired
	protected ManagerWorkPlanRepository repository;
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request!=null;
		final int id=request.getModel().getInteger("id");
		final Workplan wp=this.repository.findById(id);
		final int managerId= request.getPrincipal().getActiveRoleId();
		return wp.getManager().getId()==managerId;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int id=request.getModel().getInteger("id");
		final Workplan wp=this.repository.findById(id);
		Collection<Task>t;
		t=this.tasksRepository.findMyTasks(wp.getManager().getId());
		
		model.setAttribute("tasksInsert", t);
		request.unbind(entity, model, "startDate", "endDate", "workLoad", "publicPlan", "tasks");
		
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		int id;
		id=request.getModel().getInteger("id");
		return this.repository.findById(id);
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		Set<Task> t;
		t=entity.getTasks();
		final Boolean arePublic=t.stream().allMatch(x->x.getPublicTask());
		errors.state(request,arePublic,"publicPlan", "manager.workplan.error.arentPublic");
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		Workplan wp;
		wp=this.repository.findById(entity.getId());
		wp.setPublicPlan(true);
		this.repository.save(wp);
		
		
		
	}

}
