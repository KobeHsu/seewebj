<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">

<div class="row">
	<div class="col-md-3 col-sm-3 hidden-xs margin-bottom-lg">
<c:choose>
	<c:when test="${not empty casePortraitFile}">
		<img class="img-responsive" src="<c:url value='/CaseFile/portrait?dataUuid=${caseBasicData.uuid}&uuid=${casePortraitFile.uuid}&fileName=${casePortraitFile.uuid.concat(".").concat(casePortraitFile.extName)}' />" />
	</c:when>	
	<c:when test="${caseBasicData.sex eq 'M'}">			
			<img class="img-responsive" src="<c:url value='/res/images/male.png' />" />
	</c:when>
	<c:otherwise>
			<img class="img-responsive" src="<c:url value='/res/images/female.png' />" />
	</c:otherwise>
</c:choose>		
	</div>
	
	<div class="col-md-9 col-sm-9 col-xs-9">
		<table class="table table-striped">
		<tbody>
			<tr>
				<td width="25%" class="text-right">個案編號 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td width="75%">${caseBasicData.caseNo}</td>
			</tr>
			<tr>
				<td class="text-right">姓名 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.realname}</td>
			</tr>
			<tr>
				<td class="text-right">性別 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.sex == 'M' ? '男' : '女'}</td>
			</tr>
			<tr>
				<td class="text-right">年齡 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.age == 0 ? '' : caseBasicData.age} ${caseBasicData.age == 0 ? '' : '歲'}</td>
			</tr>
			<tr>
				<td class="text-right">身高 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.height == 0 ? '' : caseBasicData.height} ${caseBasicData.height == 0 ? '' : '公分'}</td>
			</tr>
			<tr>
				<td class="text-right">體重 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.weight == 0 ? '' : caseBasicData.weight} ${caseBasicData.weight == 0 ? '' : '公斤'}</td>
			</tr>
			<tr>
				<td class="text-right">職業 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.occupation}</td>
			</tr>
			<tr>
				<td class="text-right">年收入 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.income == 0 ? '' : caseBasicData.income} ${caseBasicData.income == 0 ? '' : '萬元'}</td>
			</tr>
			<tr>
				<td class="text-right">公司電話 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.businessPhone}</td>
			</tr>
			<tr>
				<td class="text-right">行動電話 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.mobilePhone}</td>
			</tr>
			<tr>
				<td class="text-right">聯絡地址 <i class="fa fa-angle-right fa-width-sm"></i></td>
				<td>${caseBasicData.address}</td>
			</tr>
		
		</tbody>	
		</table>
	
	</div>

</div>
</div>