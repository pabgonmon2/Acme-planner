package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.spamfilter.AdministratorSpamFilterService;
import acme.features.authenticated.manager.AuthenticatedManagerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerMyTasksCreateService implements AbstractCreateService<Manager, Task> {

	@Autowired
	protected ManagerMyTasksRepository repository;
	
	@Autowired
	protected AuthenticatedManagerRepository managerRepo;
	
	@Autowired
	protected AdministratorSpamFilterService spamService;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		request.unbind(entity, model, "title", "startDate", "endDate", "description", "publicTask", "url");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		Task task;
		final Double workFlow = 0.0;
		task = new Task();
		task.setWorkFlow(workFlow);
		return task;
	}
	
	public Boolean validacionFechas(final Task task) {
		return task.getStartDate().before(task.getEndDate());
	}
	
	public Boolean fechaInicialDespuesFechaActual(final Task task) {
		final Date actual = new Date(System.currentTimeMillis()-1);
		return task.getStartDate().after(actual);
	}
	
	public Boolean fechaFinalDespuesFechaActual(final Task task) {
		final Date actual = new Date(System.currentTimeMillis()-1);
		return task.getEndDate().after(actual);
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		final Boolean b1 = this.fechaInicialDespuesFechaActual(entity);
		final Boolean b2 = this.fechaFinalDespuesFechaActual(entity);
		final Boolean b3 = this.validacionFechas(entity);
		final Boolean spam = this.spamService.filtroTasks(entity);
		errors.state(request, b1, "startDate", "manager.mytasks.error.startDate");
		errors.state(request, b2, "endDate", "manager.mytasks.error.endDate");
		errors.state(request, b3, "endDate", "manager.mytasks.error.dates");
		errors.state(request, spam, "description", "manager.mytasks.error.spam");
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request!=null;
		assert entity!=null;
		
		final Double workFlow = 0.0;
		final Manager manager;
		final int id = request.getPrincipal().getActiveRoleId();
		manager = this.managerRepo.findManagerById(id);
		
		entity.setWorkFlow(workFlow);
		entity.setManager(manager);
		this.repository.save(entity);
	}

}
