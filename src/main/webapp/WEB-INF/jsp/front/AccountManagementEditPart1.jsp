<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container padding-top-lg">
	 	<form role="form" class="form-horizontal" id="editForm1" name="editForm1">
	 		<input type="hidden" class="form-control" id="functionName" name="functionName" />
	 		<input type="hidden" class="form-control" id="projectUuid" name="projectUuid" value="1" />
	 		<input type="hidden" class="form-control" id="uuid" name="uuid" />
	 		<div class="form-group">
      	<label for="account" class="control-label col-md-2 text-right">帳號</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="account" name="account" maxlength="64" placeholder="請輸入帳號" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="account" class="control-label col-md-2 text-right">密碼</label>
      	<div class="col-md-3">
      		<input type="password" class="form-control" id="password" name="password" maxlength="24" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="account" class="control-label col-md-2 text-right">確認密碼</label>
      	<div class="col-md-3">
      		<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" maxlength="24" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="realname" class="control-label col-md-2 text-right">姓名</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="realname" name="realname" maxlength="32" placeholder="請輸入真實姓名" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="nickname" class="control-label col-md-2 text-right">暱稱</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="nickname" name="nickname" maxlength="32" placeholder="請輸入暱稱" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="company" class="control-label col-md-2 text-right">電子郵件</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="email" name="email" maxlength="32" placeholder="請輸入電子郵件" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="company" class="control-label col-md-2 text-right">聯絡電話</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="phone" name="phone" maxlength="32" placeholder="請輸入聯絡電話" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="company" class="control-label col-md-2 text-right">服務單位</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="company" name="company" maxlength="32" placeholder="請輸入公司或機關名稱" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="title" class="control-label col-md-2 text-right">職稱</label>
      	<div class="col-md-5">
      		<input type="text" class="form-control" id="title" name="title" maxlength="32" placeholder="請輸入職稱" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="createTime" class="control-label col-md-2 text-right">註冊時間</label>
      	<div class="col-md-3">
      		<input type="text" class="form-control" id="createTime" name="createTime" maxlength="32" disabled="disabled" />
      	</div>
    	</div>

	 		<div class="form-group">
      	<label for="verifyTime" class="control-label col-md-2 text-right">啟用時間</label>
      	<div class="col-md-3">
      		<input type="text" class="form-control" id="verifyTime" name="verifyTime" maxlength="32" disabled="disabled" />
      	</div>      	
      	<div class="col-md-2">
      		<button type="button" id="resendBtn" name="resendBtn" class="btn btn-default">重送啟用通知函</button>
      	</div>      	
    	</div>

	 		<div class="form-group">
      	<label for="status" class="control-label col-md-2 text-right">狀態</label>
      	<div class="col-md-3">
      		<select class="form-control" name="status" id="status">
      		</select>
      	</div>
    	</div>

		</form>
</div>