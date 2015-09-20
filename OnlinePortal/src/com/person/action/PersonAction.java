package com.person.action;

import com.person.Person;
import com.person.dao.PersonDAO;

public class PersonAction 
{
	private Person person;

	public Person getPerson() 
	{
		return person;
	}

	public void setPerson(Person person) 
	{
		this.person = person;
	}

	public String execute() 
	{
		System.out.println(this.person.getName());
		System.out.println(this.person.getAge());
		String name = this.person.getName();
		int age = this.person.getAge();
		PersonDAO personDAOObj = new PersonDAO();
		boolean checkPerson  = personDAOObj.checkPerson(name, age);
		String result = "ERROR";
		if(checkPerson == true)
		{
			result = "SUCCESS";
		}
		return result;

	}
}
