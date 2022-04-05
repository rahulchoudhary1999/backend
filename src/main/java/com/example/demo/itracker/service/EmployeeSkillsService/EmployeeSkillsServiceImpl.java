package com.example.demo.itracker.service.EmployeeSkillsService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.itracker.entity.Employee;
import com.example.demo.itracker.entity.EmployeeSkill;
import com.example.demo.itracker.entity.Skills;
import com.example.demo.itracker.repository.EmployeeRepository;
import com.example.demo.itracker.repository.EmployeeSkillRepository;
import com.example.demo.itracker.repository.SkillRepository;
import com.example.demo.itracker.utility.RequestBodyEmployeeSkills;
import com.example.demo.itracker.utility.Response;

@Service
public class EmployeeSkillsServiceImpl implements EmployeeSkillsService{

	@Autowired
	private EmployeeRepository erepo;
	
	@Autowired
	private SkillRepository srepo;
	
	@Autowired
	private EmployeeSkillRepository esrepo;
	
	@Override
	public Response addSkill(RequestBodyEmployeeSkills rq) {
		Response response=new Response();
		try {
			String employeeId=rq.getEmployeeId();
			int skillId=rq.getSkillId();
			
			Optional<Employee> eop=erepo.findById(employeeId);
			//System.out.println("Employee ID :"+op);
			if(!eop.isPresent()) throw new Exception("Invalid employee id : "+employeeId);
			
			Optional<Skills> sop=srepo.findById(skillId);
			//System.out.println("Skill :"+skill);
			if(!sop.isPresent()) throw new Exception("Invalid skill id : "+skillId);
			
			EmployeeSkill employeeSkill=new EmployeeSkill();
			//employeeSkill.setId(rq.getSkillId());
			employeeSkill.setEmployeeId(employeeId);
			employeeSkill.setSkillId(skillId);
			
			esrepo.save(employeeSkill);
			
			
			response.setSuccess(true);
			response.setMessage("Employee skill added successfully");
		}catch(Exception exception)
		{
			response.setSuccess(false);
			response.setMessage(exception.getMessage());
		}
      return response;
		
	}

	@Override
	public List<EmployeeSkill> getListOfEmployeeBySkill(int skillId) {
		// TODO Auto-generated method stub
		List<EmployeeSkill> lst=null;
		Optional<Skills> optional=srepo.findById(skillId);
		if(optional.isPresent())
		{
			lst=esrepo.findBySkillId(skillId);
		}
		
		return lst;
	}

	@Override
	public List<EmployeeSkill> getListOfEmployeeByEmployeeId(String employeeId) {
		List<EmployeeSkill> lst=null;
		Optional<Employee> optional=erepo.findById(employeeId);
		if(optional.isPresent())
		{
			lst=esrepo.findByEmployeeId(employeeId);
		}
		
		return lst;
	}

	@Override
	public Response updateSkill(int id, RequestBodyEmployeeSkills rq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteSkill(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeSkill> getAll() {
		
		return esrepo.findAll();
	}
	
}
