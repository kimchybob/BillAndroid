package com.backend.Dao;

import com.backend.Domain.Map;

public interface MapMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer mid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    int insert(Map record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    int insertSelective(Map record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    Map selectByPrimaryKey(Integer mid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Map record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table map
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Map record);
}