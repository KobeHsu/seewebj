package org.iii.see.service;

import java.sql.Timestamp;
import java.util.UUID;

import org.iii.see.dao.MemberAuditLogDao;
import org.iii.see.dao.MemberDao;
import org.iii.see.domain.Member;
import org.iii.see.domain.MemberAuditLog;
import org.iii.see.enumeration.MemberAgreementRequiredEnum;
import org.iii.see.utility.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberAuditLogDao memberAuditLogDao;
	
	public Member queryMember(String account) {		
		return memberDao.query(new Member(), account);
	}
	
	public void signAgreement(Member member, String agreementVersion) {

		Timestamp now = DateTimeUtility.getCurrentTimestamp();
		
		member.setAgreementRequired(MemberAgreementRequiredEnum.N.getCode());
		member.setAgreementVersion(agreementVersion);
		member.setAgreeTime(now);
		member.setUpdateTime(now);
		member.setUpdater(member.getAccount());
				
		memberDao.update(member);		
	}
	
	public void createMemberAuditLog(String account, String action, String note, String ipAddress) {
		
		MemberAuditLog log = new MemberAuditLog();
		
		UUID uuid = UUID.randomUUID();
		log.setUuid(String.valueOf(uuid));		
		log.setAccount(account);
		log.setAction(action);
		log.setNote(note);
		log.setIpAddress(ipAddress);
		log.setCreateTime(DateTimeUtility.getCurrentTimestamp());
		
		memberAuditLogDao.create(log);
	}
}
