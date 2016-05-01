
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAIL")
public class EmployeeDetail {

	@Id
	@Column(name = "employee_id", unique = true, nullable = false)
	private int employeeId;

	@Column(name = "designation")
	private String designation;

	
	//@PrimaryKeyJoinColumn
	
	//private Employee employee;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/*public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
*/
	public EmployeeDetail(int employeeId, String designation, Employee employee) {
		super();
		this.employeeId = employeeId;
		this.designation = designation;
		//this.employee = employee;
	}

}
