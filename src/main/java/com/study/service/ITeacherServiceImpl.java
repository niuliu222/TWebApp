package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dao.TeacherMapper;
import com.study.model.Teacher;

@Service("teacherService")
@Transactional
public class ITeacherServiceImpl implements ITeacherService
{
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeachers()
    {
        return teacherMapper.selectTeachers();
    }
    
    @Override
    public Teacher getTeacherById(long id)
    {
        return teacherMapper.selectByPrimaryKey(id);
    }
}
