package acme.features.manager.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task>{
	
	@Autowired
	ManagerMyTasksRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "title","startDate","endDate","workFlow","description","publicTask");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		Task result;
		
		result = this.repository.findTaskById(id);
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		
		entity.setTitle(request.getModel().getString("title"));
		entity.setStartDate(new Date(null).getModel().getString("startDate"));
		entity.setEndDate(request.getModel().getString("endDate"));
		entity.setWorkFlow(request.getModel().getString("workFlow"));
		entity.setDescription(request.getModel().getString("description"));
		entity.setPublicTask(request.getModel().getString("publicTask"));
		
	}
}
