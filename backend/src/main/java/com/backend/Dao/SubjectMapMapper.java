package com.backend.Dao;

import com.backend.Domain.SubjectMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectMapMapper {

    @Select("select * from subjmap where subjID=#{subjID}")
    List<SubjectMap> selectBySubjId(@Param("subjID") Integer subjID);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer smid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    int insert(SubjectMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    int insertSelective(SubjectMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    SubjectMap selectByPrimaryKey(Integer smid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SubjectMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table subjmap
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SubjectMap record);
}