package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

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
	
		request.unbind(entity, model, "startDate", "endDate", "workLoad", "publicPlan", "tasks");
		
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		int id;
		id=request.getModel().getInteger("id");
		return this.repository.findById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Workplan wp=(Workplan) this.repository.findById(request.getModel().getInteger("id")).get();
		
		Set<Task> t;
		t=entity.getTasks();
		final Boolean arePublic=t.stream().allMatch(x->x.getPublicTask());
		errors.state(request,arePublic,"publicPlan", "manager.workplan.error.arentPublic");
		
		Collection<Task>ta;
		
		if(!wp.getTasks().isEmpty()) {
			final Date startRecommend=wp.getTasks().stream().map(Task::getStartDate).min((x,y)->x.compareTo(y)).orElse(null);
			startRecommend.setDate(startRecommend.getDate()-1);
			startRecommend.setHours(8);
			startRecommend.setMinutes(0);
			
			final Date finalRecommend=wp.getTasks().stream().map(Task::getEndDate).max((x,y)->x.compareTo(y)).orElse(null);
			finalRecommend.setDate(finalRecommend.getDate()+1);
			finalRecommend.setHours(17);
			finalRecommend.setMinutes(0);
			request.getModel().setAttribute("startRecommend", startRecommend);
			request.getModel().setAttribute("finalRecommend", finalRecommend);
			}
			if(wp.getPublicPlan())ta= this.tasksRepository.findMyPublicTasks(wp.getManager().getId());
			else ta= this.tasksRepository.findMyTasks(wp.getManager().getId());
			ta.stream().filter(x->!wp.getTasks().contains(x)).collect(Collectors.toSet());
			
			request.getModel().setAttribute("tasksInsert", ta);
		
		if(wp.getEndDate()!=null)request.getModel().setAttribute("canUpdate", wp.canUpdate());
		else request.getModel().setAttribute("canUpdate",true);
		
		request.getModel().setAttribute("tasks", wp.getTasks());
	
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		Workplan wp;
		wp=this.repository.findById(entity.getId());
		wp.setPublicPlan(true);
		this.repository.save(wp);
		
		
		
	}

}
