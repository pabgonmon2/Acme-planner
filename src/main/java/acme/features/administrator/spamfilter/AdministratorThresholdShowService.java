//package acme.features.administrator.spamfilter;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.roles.Administrator;
//import acme.entities.spamfilter.Threshold;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.services.AbstractListService;
//
//@Service
//public class AdministratorThresholdShowService implements AbstractListService<Administrator, Threshold>{
//
//	@Autowired
//	AdministratorSpamFilterRepository repository;
//
//	@Override
//	public boolean authorise(final Request<Threshold> request) {
//		assert request != null;
//		return true;
//	}
//
//	@Override
//	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
//		assert request!=null;
//		assert entity!=null;
//		assert model!=null;
//		
//		request.unbind(entity, model, "value");
//	}
//
//
//	@Override
//	public Collection<Double> findMany(final Request<Threshold> request) {
//		final Collection<Double> r = new ArrayList<Double>();
//		
//		r.add(this.repository.getSpamFilter().getThreshold());
//		
//		return r;
//	}
//	
//	
//	
//}
