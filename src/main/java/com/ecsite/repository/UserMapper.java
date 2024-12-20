package com.ecsite.repository;

import com.ecsite.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    // ユーザーをIDで検索
    @Select("SELECT id, username, password FROM users WHERE id = #{id}")
    User findById(Long id);

    // ユーザーを挿入
    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    // ユーザーを更新
    @Update("UPDATE users SET username = #{username}, password = #{password} WHERE id = #{id}")
    void updateUser(User user);

    // ユーザーを削除
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);
}

