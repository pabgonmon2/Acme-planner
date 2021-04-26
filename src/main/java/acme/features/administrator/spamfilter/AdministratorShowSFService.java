package acme.features.administrator.spamfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.SpamFilter;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorShowSFService implements AbstractShowService<Administrator, SpamFilter> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSFRepository repository;



	@Override
	public boolean authorise(final Request<SpamFilter> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<SpamFilter> request, final SpamFilter entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamwords", "threshold");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}

	@Override
	public SpamFilter findOne(final Request<SpamFilter> request) {
		assert request != null;

		final SpamFilter result;
//		result = this.repository.getSpamFilter();

		return new SpamFilter();
	}

}
