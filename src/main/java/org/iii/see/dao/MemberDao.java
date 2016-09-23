package org.iii.see.dao;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;

import org.iii.see.domain.Member;
import org.iii.see.domain.MemberHistory;
import org.iii.see.domain.MemberHistoryPK;
import org.iii.see.enumeration.DaoActionEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao extends GenericDao<Member, String> {

	@Override
	public Member create(Member member) {
		EntityManager em = getEntityManager();
		
		// 新增MemberHistory
		MemberHistory memberHistory = new MemberHistory();
		BeanUtils.copyProperties(member, memberHistory);
		
		MemberHistoryPK memberHistoryPK = new MemberHistoryPK();
		memberHistoryPK.setAccount(member.getAccount());
		memberHistoryPK.setActioner(member.getCreator());
		memberHistoryPK.setAction(DaoActionEnum.CREATE.getCode());
		memberHistoryPK.setActionTime(member.getCreateTime());
		
		memberHistory.setId(memberHistoryPK);
		
		em.persist(member);
		em.persist(memberHistory);
		
		return member;
	}

	@Override
	public Member update(Member member) {
		EntityManager em = getEntityManager();
		
		// 新增MemberHistory
		MemberHistory memberHistory = new MemberHistory();
		BeanUtils.copyProperties(member, memberHistory);
		
		MemberHistoryPK memberHistoryPK = new MemberHistoryPK();
		memberHistoryPK.setAccount(member.getAccount());
		memberHistoryPK.setActioner(member.getUpdater());
		
		memberHistoryPK.setActionTime(member.getUpdateTime());

		if (member.getAgreeTime() != null && member.getUpdateTime() != null && member.getAgreeTime().equals(member.getUpdateTime())) {
			memberHistoryPK.setAction(DaoActionEnum.AGREE.getCode());
		} else {
			memberHistoryPK.setAction(DaoActionEnum.UPDATE.getCode());
		}
		
		memberHistory.setId(memberHistoryPK);
		
		em.merge(member);
		em.persist(memberHistory);
		
		return member;
	}
	
	public void delete(Member member, String account, String actioner) {
		EntityManager em = getEntityManager();
		
		Member oMemeber = em.find(member.getClass(), account);
		
		// 新增MemberHistory
		MemberHistory memberHistory = new MemberHistory();
		BeanUtils.copyProperties(oMemeber, memberHistory);
		
		MemberHistoryPK memberHistoryPK = new MemberHistoryPK();
		memberHistoryPK.setAccount(oMemeber.getAccount());
		memberHistoryPK.setActioner(actioner);
		memberHistoryPK.setAction(DaoActionEnum.DELETE.getCode());
		memberHistoryPK.setActionTime(new Timestamp((new Date()).getTime()));
		
		memberHistory.setId(memberHistoryPK);
		
		em.remove(oMemeber);
		em.persist(memberHistory);
	}
	
	
	
}
