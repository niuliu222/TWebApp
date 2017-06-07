package com.study.service;

import java.util.List;

import com.study.model.Teacher;

public interface ITeacherService
{
	public Teacher getTeacherById(long id);

	public List<Teacher> getTeachers();
}
