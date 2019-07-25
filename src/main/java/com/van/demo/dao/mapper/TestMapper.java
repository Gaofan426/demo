package com.van.demo.dao.mapper;

import com.van.demo.dto.Name;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {

    @Select("select * from name ")
    List<Name> getNameList();

    @Insert("INSERT INTO `name`(`name`) VALUES (#{name});")
    boolean insertName(@Param("name") String name);
}
