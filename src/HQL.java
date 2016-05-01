import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class HQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("creating configuration object");
		// creating configuration object
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the
											// configuration file

		System.out.println("creating session factory object");
		// creating session factory object
		SessionFactory factory = cfg.buildSessionFactory();

		System.out.println("creating session object");
		// creating session object
		Session session = factory.openSession();

		System.out.println("creating  transaction object");
		// creating transaction object
		Transaction t = null;

		// save
		/*
		 * Employee e1=new Employee("Sil",4000,"HR"); session.save(e1); e1=new
		 * Employee("Jay",3010,"Tech"); session.save(e1); e1=new
		 * Employee("WRen",7930,"Tech"); session.save(e1);
		 */

		// create HQL FROM

		Query query;
		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("FROM:");
			query = session.createQuery("From Employee");
			@SuppressWarnings("unchecked")
			List<Employee> em1 = (List<Employee>) query.list();
			for (Employee e : em1) {
				System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname());
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// create HQL SELECT

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("SELECT firstname");
			query = session.createQuery("Select E.firstname from Employee E");
			@SuppressWarnings("unchecked")
			List<String> em2 = (List<String>) query.list();
			for (String e : em2) {
				System.out.println("==" + e);
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// create HQL WHERE

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("WHERE firstname");
			query = session.createQuery("Select E.firstname from Employee E WHERE employee_id=905 ");
			@SuppressWarnings("unchecked")
			List<String> em3 = (List<String>) query.list();
			for (String e : em3) {
				System.out.println("==" + e);
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// create HQL ORDER BY

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("ORDER BY id");
			query = session.createQuery("FROM Employee E WHERE employee_id > 2 ORDER BY E.salary ASC");
			@SuppressWarnings("unchecked")
			List<Employee> em5 = (List<Employee>) query.list();
			for (Employee e : em5) {
				System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// create HQL ORDER BY more than 1 property

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("ORDER BY id and firstname");
			query = session.createQuery("FROM Employee E WHERE employee_id > 2 ORDER BY E.salary DESC,E.firstname ");
			@SuppressWarnings("unchecked")
			List<Employee> em6 = (List<Employee>) query.list();
			for (Employee e : em6) {
				System.out.println("==" + e.getSalary() + "==" + e.getFirstname() + "==" + e.getSalary());
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// prevent SQL injection by named parameters

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("prevent SQL injection by named parameters");
			query = session.createQuery("FROM Employee E WHERE E.id = :employee_id");
			query.setParameter("employee_id", 3);
			@SuppressWarnings("unchecked")
			List<Employee> em7 = (List<Employee>) query.list();
			for (Employee e : em7) {
				System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		// group by (not good)

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("group by department");
			query = session.createQuery("SELECT SUM(E.salary) FROM Employee E " + "GROUP BY E.department");
			@SuppressWarnings("unchecked")
			List<Double> em8 = (List<Double>) query.list();
			for (Double e : em8) {
				System.out.println("==" + e);
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e);
			t.rollback();
			e.printStackTrace();
		}

		// update

		int result;
		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			query = session.createQuery("UPDATE Employee set salary = :salary WHERE id = :employee_id");
			query.setParameter("salary", 1000.0);
			query.setParameter("employee_id", 1);
			result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e);
			t.rollback();
			e.printStackTrace();
		}

		// delete

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			query = session.createQuery("DELETE FROM Employee WHERE id = :employee_id");
			query.setParameter("employee_id", 4);
			result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e);
			t.rollback();
			e.printStackTrace();
		}

		// aggregates
		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			// max(E.salary)
			// min(E.salary)
			// count(*)
			// count(distinct E.department)
			// sum(E.salary)
			// avg(E.salary)

			query = session.createQuery("Select count(distinct E.department) from Employee E");
			@SuppressWarnings("unchecked")
			List<Long> em8 = (List<Long>) query.list();
			for (Long e : em8) {
				System.out.println("==" + e);
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e);
			t.rollback();
			e.printStackTrace();
		}

		// pagination

		try {
			System.out.println("creating  transaction object");
			// creating transaction object
			t = session.beginTransaction();

			System.out.println("pagination query");

			query = session.createQuery("FROM Employee");
			int start = 0;
			int end = 4;
			query.setFirstResult(start);
			query.setMaxResults(end);
			@SuppressWarnings("unchecked")
			List<Employee> em7 = (List<Employee>) query.list();
			for (Employee e : em7) {
				System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
			}

			System.out.println("commit");
			t.commit();// transaction is committed
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			System.out.println("Rollback since Exception :" + e1);
			t.rollback();
			e1.printStackTrace();
		}

		Criteria cr;

		// To get records having salary more than 2000
		System.out.println("Greater than 2000");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.gt("salary", 2000.0));
		@SuppressWarnings("unchecked")
		List<Employee> em9 = (List<Employee>) cr.list();
		for (Employee e : em9) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To get records having salary less than 2000
		System.out.println("Less than 2000");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.lt("salary", 2000.0));
		@SuppressWarnings("unchecked")
		List<Employee> em10 = (List<Employee>) cr.list();
		for (Employee e : em10) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To get records having fistName starting with zara
		System.out.println("firstname %a%");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.like("firstname", "%a%"));
		@SuppressWarnings("unchecked")
		List<Employee> em11 = (List<Employee>) cr.list();
		for (Employee e : em11) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// Case sensitive form of the above restriction.
		System.out.println("case insensitive firstname %a%");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.ilike("firstname", "%a%"));
		@SuppressWarnings("unchecked")
		List<Employee> em12 = (List<Employee>) cr.list();
		for (Employee e : em12) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To get records having salary in between 1000 and 5000
		System.out.println("between 1000-5000");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.between("salary", 1000.0, 5000.0));
		@SuppressWarnings("unchecked")
		List<Employee> em13 = (List<Employee>) cr.list();
		for (Employee e : em13) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To check if the given property is null
		System.out.println("salary null");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.isNull("salary"));
		@SuppressWarnings("unchecked")
		List<Employee> em14 = (List<Employee>) cr.list();
		for (Employee e : em14) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To check if the given property is not null
		System.out.println("salary not null");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.isNotNull("salary"));
		@SuppressWarnings("unchecked")
		List<Employee> em15 = (List<Employee>) cr.list();
		for (Employee e : em15) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// To check if the given property is empty
		System.out.println("salary not empty");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.ne("salary", 0.0));
		@SuppressWarnings("unchecked")
		List<Employee> em16 = (List<Employee>) cr.list();
		for (Employee e : em16) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// OR operation
		System.out.println("OR operation");
		cr = session.createCriteria(Employee.class);

		Criterion salary = Restrictions.gt("salary", 2000.0);
		Criterion name = Restrictions.ilike("firstname", "%a%");
		LogicalExpression orExp = Restrictions.or(salary, name);
		cr.add(orExp);
		@SuppressWarnings("unchecked")
		List<Employee> em17 = (List<Employee>) cr.list();
		for (Employee e : em17) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// AND operation
		System.out.println("AND operation");
		cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.gt("salary", 2000.0)).add(Restrictions.like("firstname", "%a%"));
		@SuppressWarnings("unchecked")
		List<Employee> em18 = (List<Employee>) cr.list();
		for (Employee e : em18) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// asc order
		System.out.println("asc order");
		cr = session.createCriteria(Employee.class);
		cr.addOrder(Order.asc("salary"));
		@SuppressWarnings("unchecked")
		List<Employee> em19 = (List<Employee>) cr.list();
		for (Employee e : em19) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// desc order
		System.out.println("desc order");
		cr = session.createCriteria(Employee.class);
		cr.addOrder(Order.desc("salary"));
		@SuppressWarnings("unchecked")
		List<Employee> em20 = (List<Employee>) cr.list();
		for (Employee e : em20) {
			System.out.println("==" + e.getEmployeeId() + "==" + e.getFirstname() + "==" + e.getSalary());
		}

		// count

		System.out.println("count rows");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.rowCount());
		List<Integer> em21 = (List<Integer>) cr.list();
		for (Integer e : em21) {
			System.out.println("==" + e);
		}

		// count distinct

		System.out.println("count distinct");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.countDistinct("department"));
		@SuppressWarnings("unchecked")
		List<Integer> em22 = (List<Integer>) cr.list();
		for (Integer e : em22) {
			System.out.println("==" + e);
		}
		
		// sum 

		System.out.println("sum ");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.sum("salary"));
		@SuppressWarnings("unchecked")
		List<Double> em23 = (List<Double>) cr.list();
		for (Double e : em23) {
			System.out.println("==" + e);
		}
		
		// avg 

		System.out.println("avg ");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.avg("salary"));
		@SuppressWarnings("unchecked")
		List<Double> em24 = (List<Double>) cr.list();
		for (Double e : em24) {
			System.out.println("==" + Math.round(e));
		}
		
		// max 

		System.out.println("max ");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.max("salary"));
		@SuppressWarnings("unchecked")
		List<Double> em25 = (List<Double>) cr.list();
		for (Double e : em25) {
			System.out.println("==" + e);
		}

		
		// min 

		System.out.println("min ");
		cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.min("salary"));
		@SuppressWarnings("unchecked")
		List<Double> em26 = (List<Double>) cr.list();
		for (Double e : em26) {
			System.out.println("==" + e);
		}
		
		
		System.out.println("close");
		session.close();

	}

}
