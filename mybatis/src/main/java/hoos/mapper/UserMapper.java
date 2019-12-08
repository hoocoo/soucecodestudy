package hoos.mapper;

import hoos.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select({"select * from user where id=${id}"})
    User selectById(String id);
}
