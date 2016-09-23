<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/FunctionBtnGroup.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/FormControlUtil.js' />"></script>
<script type="text/javascript" src="<c:url value='/res/js/seeweb/ToolManagement.js' />"></script>

<div class="panel panel-default panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">工具管理</h3>
  </div>
  <div class="panel-body">
  	<div class="well well-danger well-sm" id="messageBar" name="messageBar"></div>
  	  	
    <div class="btn-group margin-bottom-lg" role="group" aria-label="..." id="functionBtnGroup">
  		<button type="button" id="btnQuery" class="btn btn-success btn-lg"><i class="fa fa-filter"></i> 查詢</button>
  		<button type="button" id="btnNew" class="btn btn-default btn-lg"><i class="fa fa-file-o"></i> 新增</button>
  		<button type="button" id="btnSave" class="btn btn-default btn-lg"><i class="fa fa-floppy-o"></i> 存檔</button>
  		<button type="button" id="btnDelete"class="btn btn-default btn-lg"><i class="fa fa-trash-o"></i> 刪除</button>
  		<button type="button" id="btnCancel" class="btn btn-default btn-lg"><i class="fa fa-undo"></i> 清除</button>
		</div>		
    
		<div role="tabpanel">
  		
  		<ul class="nav nav-tabs" role="tablist">
    		<li role="presentation" class="active"><a href="#queryPanel" aria-controls="query" role="tab" data-toggle="tab">查詢</a></li>
    		<li role="presentation"><a href="#editPanel1" aria-controls="edit" role="tab" data-toggle="tab">工具設定</a></li>
  		</ul>

  		<div class="tab-content">
    		<div role="tabpanel" class="tab-pane active" id="queryPanel">
    			<c:import url="/WEB-INF/jsp/front/administration/ToolManagementQuery.jsp" />
    		</div>
    		<div role="tabpanel" class="tab-pane" id="editPanel1">
    			<c:import url="/WEB-INF/jsp/front/administration/ToolManagementEditPart1.jsp" />
    		</div>
  		</div>

		</div>
    
  </div>

</div>

<div class="modal fade" role="dialog" id="confirmDeleteModal" name="confirmDeleteModal">
  <div class="modal-dialog">
    <div class="modal-content">
	    <div class="modal-header">
	      <h4 class="modal-title">刪除資料</h4>
	    </div>			
			<div class="modal-body">
				<p>請確認是否刪除本筆資料?</p>
			</div>
			
			<div class="modal-footer">
      	<button data-dismiss="modal" class="btn btn-default">取消</button>
      	<button data-dismiss="modal" class="btn btn-danger" id="btnConfirmDelete">確認</button>
			</div>
    </div>
  </div>
</div>