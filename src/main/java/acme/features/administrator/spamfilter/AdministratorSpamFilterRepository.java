package acme.features.administrator.spamfilter;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamfilter.SpamFilter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamFilterRepository extends AbstractRepository{
	
	@Query("SELECT sf FROM SpamFilter sf")
	SpamFilter getSpamFilter();
	
}
