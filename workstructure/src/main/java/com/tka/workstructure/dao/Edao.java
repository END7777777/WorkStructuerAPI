package com.tka.workstructure.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.workstructure.entity.Employee;

@Repository
public class Edao {

	@Autowired
	SessionFactory factory;
	
	public String addemp(Employee emp) {
		Session session = null;
		Transaction tx=null;
		String msg=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			session.persist(emp);
			tx.commit();
			msg="Employee is added.....";
			
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return msg;
	}

	public String updateEmployee(Employee emp, int id) {
		Session session = null;
		Transaction tx = null;

		String msg = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Employee employe = session.get(Employee.class, id);
			employe.setName(emp.getName());
			employe.setSalary(emp.getSalary());
			employe.setStatus(emp.getStatus());
			employe.setDepartment(emp.getDepartment());
			employe.setCountry(emp.getCountry());
			employe.setEmailid(emp.getEmailid());
			employe.setMobileno(emp.getMobileno());
			employe.setUpdatedBy(emp.getUpdatedBy());
			employe.setUpdatedDate(emp.getUpdatedDate());
			session.merge(employe);
			tx.commit();
			msg = "Employe is Update successfully";
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public String deleteemp(int id) {
	Session session=null;
	Transaction tx=null;
	String msg=null;
	Employee employee=null;
	try {
		session=factory.openSession();
		tx=session.beginTransaction();
		employee=session.get(Employee.class, id);
		session.remove(employee);
		tx.commit();
		msg="Employee is deleted";
	} catch (Exception e) {
		if(tx!=null) {
			tx.rollback();
		}
		e.printStackTrace();
	}finally {
		if(session!=null) {
			session.close();
		}
	}
		return msg;
	}

	public List<Employee> getallemployee() {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlquery="from Employee";
		
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> que=session.createQuery(hqlquery,Employee.class);
			list=que.list();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
	}

	public Employee empbyid(int id) {
		Session session=null;
		Transaction tx=null;
		
		Employee emp=null;
		try {
			session=factory.openSession();
			tx=session.beginTransaction();
			emp=session.get(Employee.class,id);
			tx.commit();
		} catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}		
		return emp;
	}

	public Employee login(Employee emp) {
		Session session=null;
		Transaction tx=null;
		Employee employee=null;
		String hqlQuery="from Employee where emailid=:emailid and mobileno=:mobileno";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			
			Query<Employee> query= session.createQuery(hqlQuery,Employee.class);
			query.setParameter("emailid", emp.getEmailid());
			query.setParameter("mobileno", emp.getMobileno());
			employee= query.uniqueResult();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		return employee;
	}

	public List<Employee> salaryRange(double startSal, double endSal) {
		Session session=null;
		Transaction tx=null;
		List<Employee> list=null;
		String hqlQuery="from Employee where salary between :startSal and :endSal";
		try {	
			session=factory.openSession();
			tx=session.beginTransaction();
			Query<Employee> query=session.createQuery(hqlQuery,Employee.class);
			query.setParameter("startSal", startSal);
			query.setParameter("endSal", endSal);
			list=query.list();
			tx.commit();
			
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}		
		
		return list;
	}
	
	public List<Employee> EmployeeStatus(String status) {
		Session session = null;
		Transaction tx = null;
		String msg = null;
		List<Employee> emp=null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "from Employe where status=:mystatus ";
			Query<Employee>query=session.createQuery(hql,Employee.class);
			query.setParameter("mystatus", status);
			emp=query.list();
	
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			 
		}
		return emp;
	}
	
	
	public String ChangeEmployeeStatus(int id) {
	    Session session = null;
	    Transaction tx = null;
	    String msg = null;
	    Employee emp = null;

	    try {
	        session = factory.openSession();
	        tx = session.beginTransaction();

	        // Fetch employee by ID
	        emp = session.get(Employee.class, id);

	        if (emp != null) {
	            // Change status based on current status
	            switch (emp.getStatus()) {
	                case "active":
	                    msg = "Employee status changed from Active to Inactive.";
	                    emp.setStatus("inactive");
	                    break;

	                case "inactive":
	                    msg = "Employee status changed from Inactive to Active.";
	                    emp.setStatus("active");
	                    break;

	                default:
	                    msg = "Employee status changed to Suspend.";
	                    emp.setStatus("suspend");
	                    break;
	            }

	            // Update the employee in the database
	            session.merge(emp);
	            tx.commit();
	        } else {
	            msg = "Employee not found with ID: " + id;
	        }
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        msg = "Error occurred while changing employe status: " + e.getMessage();
	        e.printStackTrace(); // You can replace this with proper logging
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return msg;
	}

	
	public List<Employee> GetEmployeesameCountry(int country) {
//		FROM Employee e 
//		WHERE e.country.id = :countryId
		
		Session session = null;
		Transaction tx = null;
		String msg = null;
		List<Employee> emp=null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Employee e WHERE e.country.cid = :countryId ";
			Query<Employee>query=session.createQuery(hql,Employee.class);
			query.setParameter("countryId", country);
			emp=query.list();
	
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			 
		}
		return emp;
	}

}
