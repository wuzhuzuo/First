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
		document.getElementById("sc").value = "更新中...";

		document.dataForm.submit();

	}
</script>
</head>
<body>
	<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16"
					align="middle" /> 添加/修改
			</h2>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataForm" name="dataForm" method="post"
				enctype="multipart/form-data" action="${ctx}/back/test_testSave.do">

				<span> <input type="hidden" name="id" id="id" value="${id}"></span>
				<span> <input type="hidden" name="tymmId" id="tymmId" value="${tymmId}"></span>

				<table cellpadding="0" cellspacing="0" border="0" class="uiTable"
					id="tempMess">
					
					<tr>
						<td></td>
					</tr>
					
					<tr>
						<td>模板名称：
						<input type="text" name="tymmName" id="tymmName" value="${tmm.tymmName}" maxlength="20" style="width:250px;">
						</td>
					</tr>
					
					
					<tr>
						<td></td>
					</tr>
					
					
					<tr>
						<td><strong>【对应列数与名称】</strong></td>
					</tr>
					
					
					

					<c:forEach var="obj" items="${tms}" varStatus="vs">

						<c:choose>
							<c:when test="${obj.tymFlag == 0 }">
								<tr>
									<td>
									<input type="hidden" name="tymNames" id="tymNames${vs.index}" value="${obj.tymName}"> 
									<input	type="hidden" name="tymFlags" id="tymFlags${vs.index}"	value="${obj.tymFlag}"> 
									<input type="hidden" name="tymOrders" id="tymOrders${vs.index}"	value="${obj.tymOrder}"> 
									<input type="hidden" name="tymSpare1s" id="tymSpare1s${vs.index}" value="${obj.tymSpare1}">
										${vs.index+1}.${obj.tymName}
									</td>
								</tr>
							</c:when>
						</c:choose>
						
						

						<c:choose>
							<c:when test="${obj.tymFlag == 1 }">
								<tr>
									<td bgcolor="#e6e6fa">
									<input type="hidden" name="tymNames" id="tymNames${vs.index}" value="${obj.tymName}"> 
									<input type="hidden" name="tymFlags" id="tymFlags${vs.index}" value="${obj.tymFlag}"> 
									<input type="hidden" name="tymOrders" id="tymOrders${vs.index}" value="${obj.tymOrder}"> 
									<input type="hidden" name="tymSpare1s" id="tymSpare1s${vs.index}" value="${obj.tymSpare1}">
										${vs.index+1}.${obj.tymName} 满分值： 
									<input type="text" name="tymFuldatas" id="tymFuldatas${vs.index}" value="${obj.tymFuldata}" 
									class="validate[required,custom[double],length[0,8]]" style="width:30px;" maxlength="5">

										所属区域： 
										<select name="side" id="side${vs.index}"
										class="validate[required,length[0,100]]" style="width:80px;">
											<c:choose>
												<c:when test="${obj.tymSide == 'L' }">
													<option value="">请选择</option>
													<option value="L" selected="selected">L区</option>
													<option value="R">R区</option>
												</c:when>
												<c:when test="${obj.tymSide == 'R' }">
													<option value="">请选择</option>
													<option value="L">L区</option>
													<option value="R" selected="selected">R区</option>
												</c:when>
											</c:choose>
										</select>
									</td>
								</tr>

								<c:forEach var="tmls" items="${tmls}" varStatus="vs">


									<c:choose>
										<c:when test="${tmls.tymlFlag == 0 && tmls.tymlName == obj.tymName }">
											<tr>
												<td>&nbsp;&nbsp;&nbsp;综合分析&nbsp;&nbsp;等级A： <textarea
														name="zhlevelsA" id="zhlevelsA${vs.index}">${tmls.tymlLevel1}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级B：
													<textarea name="zhlevelsB" id="zhlevelsB${vs.index}">${tmls.tymlLevel2}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级C：
													<textarea name="zhlevelsC" id="zhlevelsC${vs.index}">${tmls.tymlLevel3}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级D：
													<textarea name="zhlevelsD" id="zhlevelsD${vs.index}">${tmls.tymlLevel4}</textarea>
												</td>
											</tr>
										</c:when>
									</c:choose>

									<c:choose>
										<c:when test="${tmls.tymlFlag == 1 && tmls.tymlName == obj.tymName }">
											<tr>
												<td>&nbsp;&nbsp;&nbsp;专家建议&nbsp;&nbsp;等级A： <textarea
														name="zjlevelsA" id="zjlevelsA${vs.index}">${tmls.tymlLevel1}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级B：
													<textarea name="zjlevelsB" id="zjlevelsB${vs.index}">${tmls.tymlLevel2}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级C：
													<textarea name="zjlevelsC" id="zjlevelsC${vs.index}">${tmls.tymlLevel3}</textarea>
												</td>
											</tr>
											<tr>
												<td>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等级D：
													<textarea name="zjlevelsD" id="zjlevelsD${vs.index}">${tmls.tymlLevel4}</textarea>
												</td>
											</tr>
										</c:when>
									</c:choose>

								</c:forEach>

							</c:when>
						</c:choose>

					</c:forEach>

				</table>

				<div class="page">
					
					<c:choose>
						<c:when test="${flag > 0 }">
					<input id="sc" type="button" value="保 存" class="but_shop"
						onclick="addTempMess()" />&nbsp;&nbsp;
						</c:when>
					</c:choose>
					
					<input type="button"
						value="返 回" class="but_shop" onclick="history.back(-1)" />

				</div>
			</form>
		</div>
	</div>
</body>
</html>
