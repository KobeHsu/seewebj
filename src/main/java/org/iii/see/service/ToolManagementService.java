package org.iii.see.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.iii.see.dao.ToolDao;
import org.iii.see.domain.Tool;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ToolManagementService {
	
	@Autowired
	private ToolDao toolDao;
	
	public PagingResultSet queryToolList(int recordStart, int recordLength, String queryName) {
		
		PagingResultSet pagingResultSet = new PagingResultSet();
		
		Map<String, Object> params = new HashMap<String, Object>();		
		if (StringUtils.isNotBlank(queryName)) {
			params.put("by_name", 1);
			params.put("name", "%" + queryName + "%");			
		} else {
			params.put("by_name", 0);
			params.put("name", StringUtils.EMPTY);			
		}

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = (List<Object[]>)toolDao.queryByNamedQuery("ToolManagementService.queryToolList", 
                                                                             params, 
                                                                             recordStart, 
                                                                             recordLength);
		
		List countSet = toolDao.queryByNamedQuery("ToolManagementService.queryToolListCount", params);
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

	public Tool createTool(Tool tool) throws DuplicatedDataException {
		
		// 檢查是否已經存在相同名稱的工具
		if (checkDuplication(tool)) {
			throw new DuplicatedDataException();
		}
		
		UUID uuid = UUID.randomUUID();
		tool.setUuid(String.valueOf(uuid));
		
		return toolDao.create(tool);
	}	
	
	public Tool updateTool(Tool tool) throws NoDataFoundException, DuplicatedDataException {
		
		Tool oTool = toolDao.query(new Tool(), tool.getUuid());
		if (oTool == null) {
			throw new NoDataFoundException();
		}
		
		// 檢查是否已經存在相同名稱的工具
		if (!StringUtils.equals(oTool.getName(), tool.getName()) && checkDuplication(tool)) {
			throw new DuplicatedDataException();
		}		
		
		// 搬部分欄位至原資料, 避免將未提供修改欄位清空
		oTool.setName(tool.getName());
		oTool.setDescription(tool.getDescription());
		oTool.setUrl(tool.getUrl());
				
		return toolDao.update(oTool);		
	}	
	
	public void deleteTool(String uuid) throws NoDataFoundException {
		
		Tool oTool = toolDao.query(new Tool(), uuid);
		if (oTool == null) {
			throw new NoDataFoundException();
		}
		
		toolDao.delete(new Tool(), uuid);
	}
	
	private boolean checkDuplication(Tool tool) {
		boolean result = false;
		
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("name", tool.getName());			
		
		List countSet = toolDao.queryByNamedQuery("ToolManagementService.checkToolNameExisted", params);
		if (countSet != null && countSet.size() == 1) {
			if (countSet.get(0) instanceof Long) {
				Long count = (Long)countSet.get(0);
				if (count.longValue() > 0) {
					result = true;
				}
			}
		}
		
		return result;
	}
}
