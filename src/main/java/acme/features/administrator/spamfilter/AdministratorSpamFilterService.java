package acme.features.administrator.spamfilter;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.spamfilter.SpamFilter;
import acme.entities.spamfilter.Spamword;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamFilterService implements AbstractListService<Administrator, SpamFilter>{

	
	@Autowired
	AdministratorSpamFilterRepository repository;
	
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
		
		request.unbind(entity, model, "word");
	}

	@Override
	public Collection<SpamFilter> findMany(final Request<SpamFilter> request) {
		final Collection<SpamFilter> r = null;
		return r;
	}
	
	
	public Boolean filtro(final Shout shout) {
		final SpamFilter spamFilter = this.repository.getSpamFilter();
		final Collection<Spamword> spamwords = spamFilter.getLista();
		final Double umbral = spamFilter.getThreshold();
		final String autor = shout.getAuthor().toLowerCase();
		final String mensaje = shout.getText().toLowerCase();
		Integer count = 0;
		for(final Spamword sw: spamwords) {
			if(autor.contains(sw.getWord().toLowerCase())) {
				count++;
			}
			if(mensaje.contains(sw.getWord().toLowerCase())) {
				count++;
			}
		}
		
		final Integer cantPalabras = autor.split(" ").length + mensaje.split(" ").length;
		final Double operacion = (double)count/cantPalabras*100.0;
		if(operacion >= umbral) {
			return false;
		}else {
			return true;
		}
		
		
	}
	
	public Boolean filtroTasks(final Task task) {
		final SpamFilter spamFilter = this.repository.getSpamFilter();
		final Collection<Spamword> spamwords = spamFilter.getLista();
		final Double umbral = spamFilter.getThreshold();
		final String title = task.getTitle().toLowerCase();
		final String description = task.getDescription().toLowerCase();
		Integer count = 0;
		for(final Spamword sw: spamwords) {
			if(title.contains(sw.getWord().toLowerCase())) {
				count++;
			}
			if(description.contains(sw.getWord().toLowerCase())) {
				count++;
			}
		}
		
		final Integer cantPalabras = title.split(" ").length + description.split(" ").length;
		final Double operacion = (double)count/cantPalabras*100.0;
		if(operacion >= umbral) {
			return false;
		}else {
			return true;
		}
		
		
	}

}
