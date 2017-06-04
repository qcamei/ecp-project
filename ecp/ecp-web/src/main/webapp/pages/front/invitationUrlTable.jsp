<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Advanced Tables -->
<div class="card">
	<div class="card-action">客户邀请函链接</div>
	<div class="card-content">
		<div class="table-responsive" style="overflow: hidden">
			<table class="table table-striped table-bordered table-hover" id="dataTables-invitation-url">
				<thead>
					<tr>
						<th style="width: 10%;">客户姓名</th>
						<th style="width: 80%;">邀请函URL</th>
						<th style="width: 10%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${urlBeanList}" var="urlBean">
						<tr>
							<td>${urlBean.customerName}</td>
							<td id="${urlBean.urlId}">${urlBean.invitationUrl}</td>
							<td>
								<button type="button" class='btn btn-info' onclick="javascript:selectCurrUrl(${urlBean.urlId});">复制</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>
<!--End Advanced Tables -->

<!-- JS Scripts-->
<%@ include file="common/headJs.jsp"%>

<script type="text/javascript">
	/* 初始化列表分页插件 */
	$('#dataTables-invitation-url').dataTable();

	var g_prev_url_id = null;//保存上一个urlId
	
	function selectCurrUrl(urlId){

		if(g_prev_url_id!=null){
			var prevUrl = $("#url-input").val();
			$("#"+g_prev_url_id).text(prevUrl);
		}
		
		g_prev_url_id = urlId;
		
		var url = $("#"+urlId).text();
		$("#"+urlId).html("<input type='text' id='url-input' value='"+url+"' style='width:50%;' />&nbsp;&nbsp;&nbsp;&nbsp;使用&nbsp;&nbsp;Ctrl+C&nbsp;&nbsp;进行复制链接")
		$("#url-input").select();
	}
	
	/*
	 * 复制内容到剪贴板
	 */
	function copyToClipboard(txt) {
		if (window.clipboardData) {
			window.clipboardData.clearData();
			clipboardData.setData("Text", txt);
			alert("复制成功！");

		} else if (navigator.userAgent.indexOf("Opera") != -1) {
			window.location = txt;
		} else if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");
			}
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
			if (!clip)
				return;
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
			if (!trans)
				return;
			trans.addDataFlavor("text/unicode");
			var str = new Object();
			var len = new Object();
			var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
			var copytext = txt;
			str.data = copytext;
			trans.setTransferData("text/unicode", str, copytext.length * 2);
			var clipid = Components.interfaces.nsIClipboard;
			if (!clip)
				return false;
			clip.setData(trans, null, clipid.kGlobalClipboard);
			alert("复制成功！");
		}
	}
</script>
