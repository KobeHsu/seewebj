package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.ForumDao;
import org.iii.see.dao.ForumThreadDao;
import org.iii.see.dao.ForumThreadReplyDao;
import org.iii.see.domain.Forum;
import org.iii.see.domain.ForumThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumManagementService {

	@Autowired
	private ForumDao forumDao;

	@Autowired
	private ForumThreadDao forumThreadDao;

	@Autowired
	private ForumThreadReplyDao forumThreadReplyDao;
	
	public List<Object[]> queryForumList(String projectUuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		// 查詢結果欄位順序
	    // 0: uuid
	    // 1: projectUuid
	    // 2: title
	    // 3: summary
	    // 4: status
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)forumDao.queryByNamedQuery("ForumManagement.queryForumList", params); 

		return resultSet;
	}
	
	public Forum createForum(Forum forum) {

		// TODO: 檢查討論區名稱是否已經存在
		
		UUID uuid = UUID.randomUUID();
		forum.setUuid(String.valueOf(uuid));
		forum.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		Forum createdForum = forumDao.create(forum);
		return createdForum;
	}
	
	public Forum queryForum(String uuid) {
		return forumDao.query(new Forum(), uuid);
	}
	
	public List<ForumThread> queryForumThreadList(String forumUuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("forumUuid", forumUuid);
		
		@SuppressWarnings("unchecked")
		List<ForumThread> resultSet = (List<ForumThread>)forumThreadDao.queryByNamedQuery("ForumManagement.queryForumThreadList", params); 

		return resultSet;
	}

	public ForumThread createForumThread(ForumThread forumThread) {

		// TODO: 檢查討論區名稱是否已經存在
		
		UUID uuid = UUID.randomUUID();
		forumThread.setUuid(String.valueOf(uuid));
		forumThread.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		ForumThread createdForumThread = forumThreadDao.create(forumThread);
		return createdForumThread;
	}
	
}
