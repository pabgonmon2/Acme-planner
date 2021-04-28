package acme.features.authenticated.mytasks;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerMyTasksRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Task t WHERE t.manager.id=:id")
	Collection<Task> findMyTasks(@Param("id") int id);
}
