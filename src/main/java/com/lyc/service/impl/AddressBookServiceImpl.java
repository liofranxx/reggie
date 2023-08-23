package com.lyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.dao.AddressBookDao;
import com.lyc.domain.AddressBook;
import com.lyc.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/21 15:33
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookDao, AddressBook> implements AddressBookService {
}
