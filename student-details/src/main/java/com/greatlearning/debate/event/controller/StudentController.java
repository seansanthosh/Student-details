package com.greatlearning.debate.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.debate.event.entity.Student;
import com.greatlearning.debate.event.service.IStudentService;
import com.greatlearning.debate.event.service.StudentServiceImpl;

import com.greatlearning.debate.event.dao.IStudentDao;

@Controller
@RequestMapping("/Student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@RequestMapping(method = RequestMethod.GET, path = "/show-form")
	public String showFormToUser() {
		return "first";
	}
	@RequestMapping(method = RequestMethod.GET, path = "/new")
	public String newRegistration() {
		return "newRegi";
	}
	@RequestMapping(method = RequestMethod.GET, path = "/show")
	public String studentDetailsDisplay() {
		return "redirect:show-students";
	}
	@RequestMapping(method = RequestMethod.GET,path="/delete-student")
    public String editSave(@RequestParam("id") Integer id){
		studentService.deleteStudent(id);
        System.out.println("deleted");
        return ("redirect:show-students");
    }
	



	@RequestMapping(method = RequestMethod.POST, path = "/new-student")
	public String addStudentDetails(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {
		Student student = new Student(id, name, department, country);
		studentService.add(student);

		return "redirect:show-students";
	}
	 @RequestMapping(path="/update",method = RequestMethod.POST)
	    public String editsave(@ModelAttribute("student") Student student){
	
	        studentService.updateStudentDetails(student.getId(), student);
	        System.out.println("to :"+ student.toString());
	        return ("redirect:show-students");
	    }
	
	 @RequestMapping(method = RequestMethod.GET,path="/updateForm")
	    public String updateStudent(@RequestParam("id") int theId, Model theModel) {
	        Student theStudent = studentService.getStudent(theId);
	        System.out.println("The details of student with id "+theId+" are updated from ");
	        System.out.println(theStudent.toString());
	        theModel.addAttribute("command", theStudent);
	        return "updateDetails";
	    }

	@RequestMapping(method = RequestMethod.GET, path = "/show-students")
	public ModelAndView getStudentsDetails(ModelAndView mav) {

		List<Student> students = studentService.getAllStudents();
		mav.setViewName("studentInfo");
		mav.addObject("studentInfo", students);

		return mav;
	}

}
