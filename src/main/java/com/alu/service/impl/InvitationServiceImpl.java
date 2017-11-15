package com.alu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alu.service.InvitationServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("invitationService")
@Transactional
public class InvitationServiceImpl extends CommonServiceImpl implements InvitationServiceI {
	
}