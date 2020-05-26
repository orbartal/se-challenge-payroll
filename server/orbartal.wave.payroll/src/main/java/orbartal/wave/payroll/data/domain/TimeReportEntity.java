package orbartal.wave.payroll.data.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="time_report")
public class TimeReportEntity {
	
	@Id 
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="uid", unique=true)
	private String uid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
