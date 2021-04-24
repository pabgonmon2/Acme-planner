package acme.features.administrator.spamfilter;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.SpamFilter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorListSFService implements AbstractListService<Administrator, SpamFilter>{

	
	@Autowired
	AdministratorSFRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamFilter> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<SpamFilter> request, final SpamFilter entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "spamwords","threshold");
		
	}

	@Override
	public Collection<SpamFilter> findMany(final Request<SpamFilter> request) {
		assert request != null;
		
		final Collection<SpamFilter> result = new ArrayList<SpamFilter>();
		
		result.add(this.repository.getSpamFilter());
		
		return result;
	}

	public SpamFilter getSpamFilter() {
		return this.repository.getSpamFilter();
	}
	
	
}

