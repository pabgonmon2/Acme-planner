package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;

@Repository
public interface AnonymousShoutRepository {
	
	@Query("select s from Shout s")
	Collection<Shout> findMany();
}
