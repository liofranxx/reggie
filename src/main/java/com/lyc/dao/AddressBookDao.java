package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.domain.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/21 15:31
 */
@Mapper
public interface AddressBookDao extends BaseMapper<AddressBook> {
}
