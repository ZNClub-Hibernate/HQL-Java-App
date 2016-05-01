
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE3")
public class Employee {

	public Employee() {

	}
	
	/*
	 * public EmployeeDetail getEmployeeDetail() { return employeeDetail; }
	 * 
	 * public void setEmployeeDetail(EmployeeDetail employeeDetail) {
	 * this.employeeDetail = employeeDetail; }
	 */
	@Id
	 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "firstname",nullable=false,length=50)
	private String firstname;

	@Column(name = "salary",nullable=false,length=50)
	private double salary;

	@Column(name = "department",nullable=false,length=50)
	private String department;
	
	

	public Employee( String firstname, double salary, String department) {
		super();
		
		this.firstname = firstname;
		this.salary = salary;
		this.department=department;
	}

	

	// @OneToOne(mappedBy="employee", cascade=CascadeType.ALL)
	// private EmployeeDetail employeeDetail;

	
	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}
