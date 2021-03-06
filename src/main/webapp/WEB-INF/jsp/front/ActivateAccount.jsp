<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/seeweb/ActivateAccount.js' />"></script>

<div class="page-header">
	<h3>
		帳號啟用 <small>蒐集個人資料告知事項暨個人資料提供同意書</small>
	</h3>
</div>
<p class="text-right">2016 Jan.版</p>

<p>財團法人資訊工業策進會(下稱本會)為遵守個人資料保護法令及本會個人資料保護政策、規章，於向您蒐集個人資料前，依法向您告知下列事項，敬請詳閱。</p>
<p>
	<strong>一、蒐集目的及類別</strong>
</p>
<p>本會因辦理或執行業務、活動、計畫、提供服務及供本會用於內部行政管理、陳報主管機關或其他合於本會捐助章程所定業務、寄送本會或產業相關活動訊息之蒐集目的，而需獲取您下列個人資料類別：姓名、聯絡方式(如電話號碼、職稱、電子信箱、居住或工作地址等)、身分證統一編號，或其他得以直接或間接識別您個人之資料。</p>
<p>※您日後如不願再收到本會所寄送之行銷訊息，可於收到前述訊息時，直接點選訊息內拒絕接受之連結。</p>

<p>
	<strong>二、個人資料利用之期間、地區、對象及方式</strong>
</p>
<p>除涉及國際業務或活動外，您的個人資料僅供本會於中華民國領域、在前述蒐集目的之必要範圍內，以合理方式利用至蒐集目的消失為止。</p>

<p>
	<strong>三、當事人權利</strong>
</p>
<p>
	您可依前述業務、活動所定規則或依本會網站（http://www.iii.org.tw/）「個資當事人行使權利專頁」公告方式向本會行使下列權利：
</p>
<p>(一)查詢或請求閱覽。</p>
<p>(二)請求製給複製本。</p>
<p>(三)請求補充或更正。</p>
<p>(四)請求停止蒐集、處理及利用。</p>
<p>(五)請求刪除您的個人資料用。</p>

<p>
	<strong>四、不提供個人資料之權益影響</strong>
</p>
<p>若您未提供正確或不提供個人資料，本會將無法為您提供蒐集目的之相關服務。</p>

<p>
	<strong>五、您瞭解此一同意書符合個人資料保護法及相關法規之要求，且同意本會留存此同意書，供日後取出查驗。</strong>
</p>

<p>
	<strong>個人資料之同意提供：</strong>
</p>
<form role="form" class="form-horizontal" id="registrationForm" name="registrationForm">
	<input type="hidden" id="sid" name="sid" value="${sid}" /> 
	<input type="hidden" id="agreementVersion" name="agreementVersion" value="III.v10501" /> 
	
	<div class="checkbox">
		<label class="text-primary"><input type="checkbox" id="sign" name="sign" value="Y">
			一、本人已充分獲知且已瞭解上述貴會告知事項。<br />
			二、本人同意貴會於所列蒐集目的之必要範圍內，蒐集、處理及利用本人之個人資料。
		</label>
	</div>
	<div class="pull-right margin-bottom-lg">
		<button type="button" id="nextBtn" name="nextBtn" class="btn btn-default">確認</button>
		<button type="button" id="cancelBtn" name="cancelBtn" class="btn btn-default" onclick="javascript:location.href='./'">取消</button>
	</div>


</form>