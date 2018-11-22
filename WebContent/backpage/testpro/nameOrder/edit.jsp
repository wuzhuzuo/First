<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>新增/修改</title>
<%@ include file="../../commons/taglibs.jsp"%>
<%@ include file="../../commons/inc.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/backpage/scripts/msgbox.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#tempMess").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#kinMess1").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#kinMess2").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#tempMess2").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			//returnIsValid: true,
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});

	});

	function addTempMess() {

		if ($("#tempMess").validationEngine({
			returnIsValid : true,
			promptPosition : "topRight",
			ajaxSubmit : true
		}) == false) {
			return false;
		}

		document.getElementById("sc").disabled = true;
		document.getElementById("sc").value = "保存中...";

		document.dataForm.submit();

	}
</script>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16"
					align="absmiddle" /> 添加/修改
			</h2>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataForm" name="dataForm" method="post"
				enctype="multipart/form-data" action="${ctx}/back/txno_add.do">


				<table cellpadding="0" cellspacing="0" border="0" class="uiTable"
					id="tempMess">
					<tr>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td align="left">姓名： <input type="text" name="txnoName"
							id="txnoName" value="" class="validate[required,length[0,10]]">
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td align="left">考号： 
						<textarea name="txnoSpare1" id="txnoSpare1" ></textarea>
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr>
						<td align="left">备注： 
						<textarea name="txnoRemark" id="txnoRemark" ></textarea>
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
					</tr>
					

					<tr>
						<td>&nbsp;</td>
					</tr>

				</table>

				<div class="page">

					<input id="sc" type="button" value="保 存" class="but_shop"
						onclick="addTempMess()" />&nbsp;&nbsp;<input type="button"
						value="返 回" class="but_shop" onclick="history.back(-1)" />

				</div>
			</form>
		</div>
	</div>
</body>
</html>
