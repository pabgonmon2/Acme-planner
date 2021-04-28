package acme.entities.spamfilter;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SpamFilter extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	

	@OneToMany
	private Collection<Spamword> lista;
	
	protected Double Threshold;
	
}
