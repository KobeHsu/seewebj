package org.iii.see.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.AccountDao;
import org.iii.see.domain.Account;
import org.iii.see.enumeration.AccountStatusEnum;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountManagementService {

	@Autowired
	private AccountDao accountDao;
	
	public PagingResultSet queryAccount(int recordStart, int recordLength, String queryAccount, String queryRealname, String queryStatus) {
		
		PagingResultSet pagingResultSet = new PagingResultSet();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(queryAccount)) {
			params.put("by_account", 1);
			params.put("account", "%" + queryAccount + "%");			
		} else {
			params.put("by_account", 0);
			params.put("account", StringUtils.EMPTY);			
		}
		if (StringUtils.isNotBlank(queryRealname)) {
			params.put("by_realname", 1);
			params.put("realname", "%" + queryRealname + "%");			
		} else {
			params.put("by_realname", 0);
			params.put("realname", StringUtils.EMPTY);			
		}

		if (StringUtils.isNotBlank(queryStatus)) {
			params.put("by_status", 1);
			params.put("status", queryStatus);
		} else {
			params.put("by_status", 0);
			params.put("status", StringUtils.EMPTY);
		}

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)accountDao.queryByNamedQuery("AccountManagement.queryAccount", 
                                                                                params, 
                                                                                recordStart, 
                                                                                recordLength);
		
		List countSet = accountDao.queryByNamedQuery("AccountManagement.queryAccountCount", params);
		if (countSet != null && countSet.size() == 1) {
			if (countSet.get(0) instanceof Long) {
				Long count = (Long)countSet.get(0);
				pagingResultSet.setRecordsTotal(count.intValue());
			}
		}

		pagingResultSet.setRecordsStart(recordStart);
		pagingResultSet.setPageSize(recordLength);
		pagingResultSet.setResultSet(resultSet);

		return pagingResultSet;
		
	}
	
	public Account createAccount(Account account) throws NoSuchAlgorithmException {
		
		String passwordMd5 = PasswordUtility.md5(account.getPassword());	
		account.setPassword(passwordMd5.toLowerCase());
		
		UUID uuid = UUID.randomUUID();
		account.setUuid(String.valueOf(uuid));
		account.setCreateTime(new Timestamp((new Date()).getTime()));
		
		return accountDao.create(account);
	}	

	public Account updateAccount(Account account) throws NoSuchAlgorithmException, NoDataFoundException {
				
		Account oAccount = accountDao.query(new Account(), account.getUuid());
		if (oAccount == null) {
			throw new NoDataFoundException();
		}
		
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oAccount.setAccount(account.getAccount());
		oAccount.setRealname(account.getRealname());
		oAccount.setNickname(account.getNickname());
		oAccount.setEmail(account.getEmail());
		oAccount.setPhone(account.getPhone());
		oAccount.setCompany(account.getCompany());
		oAccount.setTitle(account.getTitle());
		oAccount.setStatus(account.getStatus());
		
		if (!StringUtils.isEmpty(account.getPassword())) {
			String passwordMd5 = PasswordUtility.md5(account.getPassword());	
			oAccount.setPassword(passwordMd5.toLowerCase());			
		}
		
		oAccount.setUpdateTime(new Timestamp((new Date()).getTime()));
		
		return accountDao.update(oAccount);		
	}	

	public Account queryAccount(String uuid) {
		return accountDao.query(new Account(), uuid);
	}	
	
	public Account activateAccount(String uuid) throws NoDataFoundException {
		
		Account oAccount = accountDao.query(new Account(), uuid);
		if (oAccount == null) {
			throw new NoDataFoundException();
		}
		
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oAccount.setStatus(AccountStatusEnum.ACTIVE.getCode());
		oAccount.setVerifyTime(new Timestamp((new Date()).getTime()));
						
		return accountDao.update(oAccount);		
	}	
	
}
