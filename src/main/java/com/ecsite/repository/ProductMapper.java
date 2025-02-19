package com.ecsite.repository;

import com.ecsite.model.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products")
    List<Product> findAll();

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product findById(Long id);

    @Insert("INSERT INTO products(name, description, price, stock) VALUES(#{name}, #{description}, #{price}, #{stock})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Product product);

    @Update("UPDATE products SET name=#{name}, description=#{description}, price=#{price}, stock=#{stock} WHERE id=#{id}")
    void update(Product product);

    @Delete("DELETE FROM products WHERE id=#{id}")
    void delete(Long id);
}

