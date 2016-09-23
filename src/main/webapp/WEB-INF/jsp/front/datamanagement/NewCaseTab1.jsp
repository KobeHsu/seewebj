<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel-default margin-top-lg">
	
<form role="form" class="form-horizontal" id="newCaseTab1Form" name="newCaseTab1Form">
		<input type="hidden" id="projectUuid" name="projectUuid" value="${projectUuid}" />
		<input type="hidden" id="uuid" name="uuid" value="${dataUuid}" />
		<input type="hidden" id="functionName" name="functionName" />
		
		<div class="form-group">
    		<label for="account" class="control-label col-md-2 text-right">個案編號*</label>
    		<div class="col-md-6">
    				<div class="input-group"> <span class="input-group-addon"> <i class="fa fa-list-ol fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="caseNo" name="caseNo" maxlength="8" placeholder="請輸入個案編號" />
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="realname" class="control-label col-md-2 text-right">姓名*</label>
    		<div class="col-md-6">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-user fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="realname" name="realname" maxlength="32" placeholder="請輸入姓名" />
      			</div>
     		</div>
    </div>
    
		<div class="form-group">
    		<label for="sex" class="control-label col-md-2 text-right">性別*</label>
    		<div class="col-md-6">
      			<label class="radio-inline"><input type="radio" id="sex" name="sex" value="M">男</label>
						<label class="radio-inline"><input type="radio" id="sex" name="sex" value="F">女</label>
     		</div>
    </div>    

		<div class="form-group">
    		<label for="age" class="control-label col-md-2 text-right">年齡</label>
    		<div class="col-md-6">
    				<div class="input-group">
    					<span class="input-group-addon"> <i class="fa fa-paw fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="age" name="age" maxlength="3" placeholder="請輸入年齡" />
      				<span class="input-group-addon"> 歲　 </span>
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="height" class="control-label col-md-2 text-right">身高</label>
    		<div class="col-md-6">
    				<div class="input-group">
    					<span class="input-group-addon"> <i class="fa fa-align-justify fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="height" name="height" maxlength="3" placeholder="請輸入身高" />
      				<span class="input-group-addon"> 公分 </span>
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="weight" class="control-label col-md-2 text-right">體重</label>
    		<div class="col-md-6">
    				<div class="input-group"><span class="input-group-addon"> 
    					<i class="fa fa-anchor fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="weight" name="weight" maxlength="3" placeholder="請輸入體重" />
      				<span class="input-group-addon"> 公斤 </span>
      			</div>
     		</div>
    </div>
			
		<div class="form-group">
    		<label for="occupation" class="control-label col-md-2 text-right">職業</label>
    		<div class="col-md-6">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-credit-card fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="occupation" name="occupation" maxlength="32" placeholder="請輸入職業" />
      			</div>
     		</div>
    </div>

		<div class="form-group">
    		<label for="occupation" class="control-label col-md-2 text-right">年收入</label>
    		<div class="col-md-6">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-usd fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="income" name="income" maxlength="5" placeholder="請輸入年收入" />
      				<span class="input-group-addon"> 萬元 </span>
      			</div>
     		</div>
    </div>
        
		<div class="form-group">
    		<label for="businessPhone" class="control-label col-md-2 text-right">公司電話*</label>
    		<div class="col-md-5">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-phone fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="businessPhone" name="businessPhone" maxlength="32" placeholder="請輸入公司聯絡電話" />
      			</div>
     		</div>
    </div>
		<div class="form-group">
    		<label for="mobilePhone" class="control-label col-md-2 text-right">行動電話*</label>
    		<div class="col-md-5">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-mobile fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="mobilePhone" name="mobilePhone" maxlength="32" placeholder="請輸入個人行動電話" />
      			</div>
     		</div>
    </div>
		<div class="form-group">
    		<label for="address" class="control-label col-md-2 text-right">聯絡地址*</label>
    		<div class="col-md-8">
    				<div class="input-group"><span class="input-group-addon"> <i class="fa fa-home fa-width-sm"></i> </span>
      				<input type="text" class="form-control" id="address" name="address" maxlength="128" placeholder="請輸入聯絡地址" />
      			</div>
     		</div>
    </div>
	
</form>
</div>