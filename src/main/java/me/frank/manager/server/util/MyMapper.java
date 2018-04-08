package me.frank.manager.server.util;

import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by frank on 2018/3/9.
 */
public interface MyMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>, MySqlMapper<T> {
}
