package com.study.dao;

import com.study.model.Teacher;

public interface TeacherMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    int insert(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    int insertSelective(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    Teacher selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teacher
     *
     * @mbg.generated Tue May 30 22:26:58 CST 2017
     */
    int updateByPrimaryKey(Teacher record);
}