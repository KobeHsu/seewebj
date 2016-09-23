<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="newPersonaTab1Form" name="newPersonaTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="uuid" name="uuid" value="${dataUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
    		<label for="account" class="control-label col-md-2 text-right">編號*</label>
    		<div class="col-md-5">
    				<div class="input-group"> <span class="input-group-addon"> <i class="fa fa-list-ol fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="personaNo" name="personaNo" maxlength="8" placeholder="請輸入人物角色原型編號" />
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="realname" class="control-label col-md-2 text-right">姓名*</label>
    		<div class="col-md-5">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-user fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="realname" name="realname" maxlength="32" placeholder="請輸入姓名" />
      			</div>
     		</div>
    </div>
    
	 	<div class="form-group">
      	<label for="category" class="control-label col-md-2 text-right">類型*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="category" name="category"></textarea>
      	</div>
    </div>
    
	 	<div class="form-group">
      	<label for="background" class="control-label col-md-2 text-right">背景及態度*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="background" name="background"></textarea>
      	</div>
    </div>

	 	<div class="form-group">
      	<label for="behavior" class="control-label col-md-2 text-right">行為特質*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="behavior" name="behavior"></textarea>
      	</div>
    </div>

	 	<div class="form-group">
      	<label for="target" class="control-label col-md-2 text-right">欲達成目標*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="target" name="target"></textarea>
      	</div>
    </div>
	
</form>

<form role="form" class="form-horizontal" id="newCaseTab3Form" name="newCaseTab3Form">
	<div class="form-group">
		<label for="portrait" class="control-label col-md-2 text-right">人物角色原型影像</label>
    <div class="col-md-5">
    	<input type="file" class="file" id="portraitFile" name="portraitFile" />
    </div>
	</div>

</form>

</div>