<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/res/kartik-v-bootstrap-fileinput/css/fileinput.min.css' />" rel="stylesheet">		
	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/plugins/canvas-to-blob.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput.min.js' />"></script>	
<script src="<c:url value='/res/kartik-v-bootstrap-fileinput/js/fileinput_locale_zh-TW.js' />"></script>	

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="editPersonaTab1Form" name="editPersonaTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="uuid" name="uuid" value="${dataUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
    		<label for="account" class="control-label col-md-2 text-right">編號*</label>
    		<div class="col-md-5">
    				<div class="input-group"> <span class="input-group-addon"> <i class="fa fa-list-ol fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="personaNo" name="personaNo" maxlength="8" placeholder="請輸入人物角色原型編號" value="${personaBasicData.personaNo}" />
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="realname" class="control-label col-md-2 text-right">姓名*</label>
    		<div class="col-md-5">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-user fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="realname" name="realname" maxlength="32" placeholder="請輸入姓名" value="${personaBasicData.realname}" />
      			</div>
     		</div>
    </div>
    
	 	<div class="form-group">
      	<label for="category" class="control-label col-md-2 text-right">類型*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="category" name="category">${personaBasicData.category}</textarea>
      	</div>
    </div>
    
	 	<div class="form-group">
      	<label for="background" class="control-label col-md-2 text-right">背景及態度*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="background" name="background">${personaBasicData.background}</textarea>
      	</div>
    </div>

	 	<div class="form-group">
      	<label for="behavior" class="control-label col-md-2 text-right">行為特質*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="behavior" name="behavior">${personaBasicData.behavior}</textarea>
      	</div>
    </div>

	 	<div class="form-group">
      	<label for="target" class="control-label col-md-2 text-right">欲達成目標*</label>
      	<div class="col-md-10">
      		<textarea class="form-control" rows="3" id="target" name="target">${personaBasicData.target}</textarea>
      	</div>
    </div>
	
</form>

		<form role="form" class="form-horizontal" id="editwPersonaTab2Form" name="editwPersonaTab2Form">
			<div class="form-group">
				<div class="col-md-6">
<c:choose>
	<c:when test="${not empty personaPortraitFile}">
				<img class="img-responsive" src="<c:url value='/PersonaFile/portrait?dataUuid=${personaBasicData.uuid}&uuid=${personaPortraitFile.uuid}&fileName=${personaPortraitFile.uuid.concat(".").concat(personaPortraitFile.extName)}' />" />
	</c:when>	
</c:choose>		
					
				</div>
		    <div class="col-md-6">
		    	<input type="file" class="file" id="portraitFile" name="portraitFile" />
		    </div>
			</div>		
		</form>		
</div>