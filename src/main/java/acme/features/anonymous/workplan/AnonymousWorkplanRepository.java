package acme.features.anonymous.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplans.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkplanRepository extends AbstractRepository{
	
	@Query("SELECT w FROM WorkPlan w WHERE w.endDate > CURRENT_DATE")
	Collection<WorkPlan> findPublicWorkplansNonFinished();
	
	@Query("SELECT w FROM WorkPlan w WHERE w.endDate > CURRENT_DATE AND w.id = ?1")
	WorkPlan findOnePublicWorkplansNonFinished(int id);
	
}
