<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<c:url value='/res/js/jquery.md5.js' />"></script>	
<script type="text/javascript" src="<c:url value='/res/js/seeweb/Login.js' />"></script>

<link href="<c:url value='/res/css/landing-page.css' />" rel="stylesheet">

    <!-- Header -->
    <div class="intro-header">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>S.E.E.</h1>
                        <h3>資策會服務體驗工程方法平台</h3>
                        <hr class="intro-divider">
                        
                        <div class="container col-lg-offset-4 col-lg-4">
	                        <form role="form" class="form-horizontal" id="loginForm" name="loginForm">
															<div class="form-group form-group-sm">
																<label class="control-label col-lg-1 text-right"></label>
																<div class="col-lg-11">
																	<div class="input-group"><span class="input-group-addon"> <i class="fa fa-user fa-width-sm"></i> </span>
							  										<input type="text" class="form-control" placeholder="請輸入帳號" id="userAccount" name="userAccount" />  										
							  									</div>
							  								</div>
															</div>

															<div class="form-group form-group-sm">
																<label class="control-label col-lg-1 text-right"></label>
																<div class="col-lg-11">
																	<div class="input-group"><span class="input-group-addon"> <i class="fa fa-key fa-width-sm"></i> </span>
							  										<input type="password" class="form-control" placeholder="請輸入密碼" id="userPassword" name="userPassword" />  										
							  									</div>
							  								</div>
															</div>
	                        		
												      <div class="form-group">
												      		<div class="col-lg-12 text-right">
													        	<button type="button" class="btn btn-xs btn-danger" id="loginBtn" name="loginBtn">登入</button>
													      	  <button type="button" class="btn btn-xs btn-default" onclick="javascript:location.href='ResetPassword'" >忘記密碼</button>
													      	  <button type="button" class="btn btn-xs btn-default" id="privacyStatementBtn" name="privacyStatementBtn">隱私權聲明</button>
												      		</div>
												      </div>
	                        		
	                      	</form>
                      	</div>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>

    <footer>
        <div class="container">
            <div class="row">
                    <p class="copyright text-muted small">Copyright © 資策會 | 創新應用服務研究所 III-IDEAS</p>
                </div>
            </div>
        </div>
    </footer>


<div class="modal fade" role="dialog" id="privacyStatementModal" name="privacyStatementModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
	    <div class="modal-header">
	      <h4 class="modal-title">隱私權聲明</h4>
	    </div>			
			<div class="modal-body">
				<p>
					財團法人資訊工業策進會（以下簡稱本會）尊重並保護您的隱私權。為了幫助您瞭解本會如何蒐集、處理及利用您的個人資料，請務必詳細閱讀本會的「隱私權聲明」。
				</p>
				<p>
					<strong>壹、適用範圍</strong>
				</p>
				<p>
					一、本會「隱私權聲明」適用於您與本會洽辦業務、參與各項活動（如報名研討會/課程、加入網站會員、訂閱電子報等）或透過電話、傳真或本會網站意見信箱提出詢問或建議時（包括本會官網及本會各業務部門網站），所涉及之個人資料蒐集、處理與利用行為。
				</p>
				<p>二、凡經由本會網站連結至第三方獨立管理、經營之網站，有關個人資料的保護，適用第三方或各該網站的隱私權政策，本會不負任何連帶責任。
				</p>
				
				<p>
					<strong>貳、個人資料之蒐集、處理及利用</strong>
				</p>
				<p>
					一、當您與本會洽辦業務或參與本會各項活動，我們將視業務或活動性質請您提供必要的個人資料，並在該特定目的範圍內處理及利用您的個人資料；非經您書面同意，本會不會將個人資料用於其他用途。
				</p>
				<p>二、如果您使用電話、傳真或本會網站意見信箱與本會聯繫時，請您提供正確的電話、傳真號碼或電子信箱地址，作為回覆來詢事項之用。</p>
				<p>三、您的個人資料在處理過程中，本會將遵守相關之流程及內部作業規範，並依據資訊安全之要求，進行必要之人員控管。</p>
				<p>四、單純瀏覽本會網站及下載檔案之行為，本會不會蒐集任何與個人身份有關之資訊。</p>
				
				<p>
					<strong>參、與第三人共用個人資料</strong>
				</p>
				<p>一、本會絕不會提供、交換、出租或出售任何您的個人資料給其他個人、團體、私人企業或公務機關，但有法律依據或合約義務者，不在此限。</p>
				
				<p>二、前項但書之情形包括不限於：</p>
				<p>1. 配合司法單位合法的調查。</p>
				<p>2. 配合主管機關依職權或職務需要之調查或使用（例如審計部或會計師查帳）。</p>
				<p>3. 基於善意相信揭露您的個人資料為法律所必需。</p>
				<p>4.
					當您在本會網站的行為，違反本會的服務條款或可能損害或妨礙本會權益或導致任何人遭受損害時，經本會研析揭露您的個人資料是為了辨識、聯絡或採取法律行動所必要者。
				</p>
				<p>5. 基於委外契約關係，本會依約履行提供個人資料之義務。</p>
				<p>三、本會委託廠商協助蒐集、處理或利用您的個人資料時，將對委外廠商或個人善盡監督管理之責。</p>
				<p>
					<strong>肆、cookie 之運用</strong>
				</p>
				<p>一、基於網站內部管理之需要及提供最佳個人化服務，本會網站將在您的瀏覽器中寫入cookies並讀取記錄瀏覽者的 IP
					位址、上網時間以及在各項資訊查閱之次數，進行網站流量和網路行為調查之總量分析，不會對「個別」瀏覽者進行分析。</p>
				<p>二、若您不願接受 cookie 的寫入，您可將使用中之瀏覽器設定為拒絕 cookie
					的寫入，但也因此會使網站某些功能無法正常執行。</p>
				<p>
					<strong>伍、伺服器紀錄</strong>
				</p>
				<p>
					當您透過瀏覽器、應用程式或其他用戶端使用本會網站時，我們的伺服器會自動記錄特定的技術性資訊。這些伺服器紀錄可能包含您的網頁要求、網際網路通訊協定位址、瀏覽器類型、瀏覽器語言、送出要求的日期和時間等資訊。此伺服器紀錄僅作為伺服器管理的參考，本會不會利用此伺服器紀錄對「個別」瀏覽者進行分析。
				</p>
				<p>
					<strong>陸、隱私權聲明之修改</strong>
				</p>
				<p>
					本隱私權聲明將適時依據法律修正、相關技術之發展及內部管理制度之調整而配合修改，以落實保障您隱私權及網路安全之初衷。當本會完成相關條款修改時，會立即將其刊登於本會網站中，並以醒目標示提醒您前往點選閱讀。
				</p>				
			</div>
			
			<div class="modal-footer">
      	<button data-dismiss="modal" class="btn btn-danger" id="btnConfirmDelete">確認</button>
			</div>
    </div>
  </div>
</div>