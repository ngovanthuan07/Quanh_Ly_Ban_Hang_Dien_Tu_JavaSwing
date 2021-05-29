package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.mapper.RowMapper;
import java.util.List;

public interface GenericDAO<T> {

    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    void update(String sql, Object... parameters);

    Object insert(String sql, Object... parameters);
}
