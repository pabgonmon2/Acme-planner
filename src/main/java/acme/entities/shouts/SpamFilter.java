package acme.entities.shouts;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamFilter extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@NotNull
	@ElementCollection(targetClass=String.class)
	protected List<String> spamwords;
	
	@NotNull
	protected Double threshold;
	
	
	
}
