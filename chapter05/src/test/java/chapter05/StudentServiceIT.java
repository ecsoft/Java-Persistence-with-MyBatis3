package chapter05;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chapter05.domain.Address;
import chapter05.domain.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class StudentServiceIT 
{
	@Autowired
	private StudentService studentService;
	

		
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		StudentService studentService = ctx.getBean(StudentService.class);
		List<Student> students = studentService.findAllStudents();
		for (Student student : students) {
			System.err.println(student);
		}
	}
	@Test
    public void testCreateStudent() 
	{
		Address address = new Address();
		address.setStreet("panjagutta");
		address.setCity("Hyd");
		address.setState("AP");
		address.setZip("500012");
		address.setCountry("India");
		
		Student student = new Student();
		student.setAddress(address);
		student.setEmail("test@gmail.com");
		student.setName("neha1");

		Student createdStudent = studentService.createStudent(student);
		Assert.assertNotNull(createdStudent);
		System.err.println(createdStudent.getId()+":"+createdStudent.getAddress().getId());
	}
	
	@Test(expected=RuntimeException.class)
    public void testCreateStudentFail() 
	{
		Address address = new Address();
		address.setStreet("kukatpally");
		address.setCity("Hyd");
		address.setState("AP");
		address.setZip("500012");
		address.setCountry("India");
		
		Student student = new Student();
		student.setAddress(address);
		student.setEmail("test@gmail.com");
		student.setName("");// this triggers error

		studentService.createStudent(student);
		System.err.println("---------------------------------");
	}
	//@Test
    public void testFindAllStudents() 
	{
		List<Student> students = studentService.findAllStudents();
		Assert.assertNotNull(students);
		for (Student student : students)
		{
			System.out.println(student);
		}
		
		
	}
	
	//@Test
    public void testFindStudentById() 
	{
		Student student = studentService.findStudentById(1);
			System.out.println(student);
	}
}