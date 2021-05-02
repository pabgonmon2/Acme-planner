package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository{
	
	@Query("select s from Shout s ORDER BY s.moment DESC")
	Collection<Shout> findMany();
	
	@Query("select s from Shout s where s.moment >= ?1 ORDER BY s.moment DESC")
	Collection<Shout> findRecentShouts(Date deadline);
	
	
}
