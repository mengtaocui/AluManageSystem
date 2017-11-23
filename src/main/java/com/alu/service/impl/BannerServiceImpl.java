package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.BannerServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("bannerService")
@Transactional
public class BannerServiceImpl extends CommonServiceImpl implements BannerServiceI {
	
}