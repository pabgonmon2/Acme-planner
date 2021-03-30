package acme.framework.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------
		@NotBlank
		@Size(max = 80)
		private String			title;
		
		@FutureOrPresent
		private LocalDate		startDate;
		
		@FutureOrPresent
		//Custom validator detras de inicio
		private LocalDate		endDate;
		
		//Custom validator
		private Double			workFlow;
		
		@NotBlank
		@Size(max = 500)
		private String			description;
		

		private String			url;
		
		// Derived atttributes ----------------------------------------------------


		@Override
		@Transient
		public boolean isTransient() {
			boolean result;

			result = this.id == 0;

			return result;
		}

		// Object interface -------------------------------------------------------
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
			result = prime * result + ((this.endDate == null) ? 0 : this.endDate.hashCode());
			result = prime * result + ((this.startDate == null) ? 0 : this.startDate.hashCode());
			result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
			result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
			result = prime * result + ((this.workFlow == null) ? 0 : this.workFlow.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (this.getClass() != obj.getClass())
				return false;
			final Task other = (Task) obj;
			if (this.description == null) {
				if (other.description != null)
					return false;
			} else if (!this.description.equals(other.description))
				return false;
			if (this.endDate == null) {
				if (other.endDate != null)
					return false;
			} else if (!this.endDate.equals(other.endDate))
				return false;
			if (this.startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!this.startDate.equals(other.startDate))
				return false;
			if (this.title == null) {
				if (other.title != null)
					return false;
			} else if (!this.title.equals(other.title))
				return false;
			if (this.url == null) {
				if (other.url != null)
					return false;
			} else if (!this.url.equals(other.url))
				return false;
			if (this.workFlow == null) {
				if (other.workFlow != null)
					return false;
			} else if (!this.workFlow.equals(other.workFlow))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Task [title=" + this.title + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", workFlow=" + this.workFlow + ", description=" + this.description + ", url=" + this.url + "]";
		}
		
		
		

}
