package acme.features.administrator.spamfilter;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.SpamFilter;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSFRepository extends AbstractRepository{

	@Query("select sf from SpamFilter sf")
	Collection<SpamFilter> getSpamFilter();
}
