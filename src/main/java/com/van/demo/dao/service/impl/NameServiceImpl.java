package com.van.demo.dao.service.impl;

import com.van.demo.dao.entity.Name;
import com.van.demo.dao.mapper.NameMapper;
import com.van.demo.dao.service.INameService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author van
 * @since 2019-08-23
 */
@Service
@Primary
public class NameServiceImpl extends ServiceImpl<NameMapper, Name> implements INameService {
	
}
