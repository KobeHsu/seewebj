<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="margin-top-lg">
	<div class="pull-right margin-top-lg">
		<button type="button" class="btn btn-round btn-danger" id="newCaseBtn" name="newCaseBtn" 
					  onclick="javascript:location.href='NewCase?projectUuid=${projectUuid}'"><i class="fa fa-file-o"></i></button>
		<br /><br />
		<button type="button" class="btn btn-round btn-danger" id="openCaseBtn" name="openCaseBtn" 
					  onclick="javascript:location.href='NewCase?projectUuid=${projectUuid}'"><i class="fa fa-folder-open-o"></i></button>
		<br /><br />
		<button type="button" class="btn btn-round btn-danger" id="saveCaseBtn" name="saveCaseBtn" 
					  onclick="javascript:location.href='NewCase?projectUuid=${projectUuid}'"><i class="fa fa-floppy-o"></i></button>
		<br /><br />
		<button type="button" class="btn btn-round btn-danger" id="deleteCaseBtn" name="deleteCaseBtn" 
					  onclick="javascript:location.href='NewCase?projectUuid=${projectUuid}'"><i class="fa fa-trash-o"></i></button>

	</div>	
</div>