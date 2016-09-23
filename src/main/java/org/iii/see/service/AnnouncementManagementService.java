package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.ProjectAnnouncementDao;
import org.iii.see.domain.ProjectAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnnouncementManagementService {

	@Autowired
	private ProjectAnnouncementDao projectAnnouncementDao;	
	
	public List<Object[]> queryAnnouncementList(String projectUuid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectUuid", projectUuid);
		
		// 查詢結果欄位順序
	    // 0: uuid
	    // 1: projectUuid
	    // 2: title
	    // 3: summary
	    // 4: status
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)projectAnnouncementDao.queryByNamedQuery("AnnouncementManagement.queryAnnouncementList", params); 

		return resultSet;
	}
	
	public ProjectAnnouncement createProjectAnnouncement(ProjectAnnouncement announcement) {

		// TODO: 檢查討論區名稱是否已經存在
		
		UUID uuid = UUID.randomUUID();
		announcement.setUuid(String.valueOf(uuid));
		announcement.setCreateTime(new Timestamp((new Date()).getTime()));
		 
		ProjectAnnouncement createdAnnouncement = projectAnnouncementDao.create(announcement);
		return createdAnnouncement;
	}
	
}
