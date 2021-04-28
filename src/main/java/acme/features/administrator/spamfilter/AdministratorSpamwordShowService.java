//package acme.features.administrator.spamfilter;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.roles.Administrator;
//import acme.entities.spamfilter.Spamword;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.services.AbstractListService;
//
//@Service
//public class AdministratorSpamwordShowService implements AbstractListService<Administrator, Spamword>{
//
//	@Autowired
//	AdministratorSpamFilterRepository repository;
//
//	@Override
//	public boolean authorise(final Request<Spamword> request) {
//		assert request != null;
//		return true;
//	}
//
//	@Override
//	public void unbind(final Request<Spamword> request, final Spamword entity, final Model model) {
//		assert request!=null;
//		assert entity!=null;
//		assert model!=null;
//		
//		request.unbind(entity, model, "word");
//	}
//
//
//	@Override
//	public Collection<Spamword> findMany(final Request<Spamword> request) {
//		final Collection<Spamword> r = new ArrayList<Spamword>();
//		
////		for(final Spamword s : this.repository.findAllSpamWords()) {
////			r.add(s);
////		}
//		
//		return r;
//	}
//	
//	
//	
//}
