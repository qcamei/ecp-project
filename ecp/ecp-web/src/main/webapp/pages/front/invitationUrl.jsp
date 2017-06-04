<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="common/headCss.jsp"%>

<div class='container'>
	<div class='panel' style="margin-top: 20px;">
		<div class='panel-body'>
			<div class="row">


				<div class="col-md-3">
					<select id="select-invitation" class="form-control">
						<option value="0">请选择邀请函主题</option>
						<c:forEach items="${invitationList}" var="invitation">
							<option value="${invitation.invitationId}">${invitation.invTitle}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-9">
					<button type="button" class='btn btn-info' onclick="javascript:createInvitationUrl();">创建全部客户邀请函链接</button>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div id="url-item"></div>
	</div>
</div>

<!-- JS Scripts-->
<%@ include file="common/headJs.jsp"%>

<script type="text/javascript">
	function createInvitationUrl() {
		var invitationId = $("#select-invitation").val();
		if (invitationId <= 0) {
			show_err_msg("请选择邀请函");
		} else {
			show_loading();
			var url = "front/invUrl/createInvitationUrl";
			var params = new Object();
			params.invitationId = invitationId;
			$("#url-item").load(url, params, function() {
				console.log("加载完成");
				show_loaded();
			});
		}
	}
</script>