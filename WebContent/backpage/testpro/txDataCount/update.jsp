<%@page import="com.back.testpro.model.TxDataCountMain"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>

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
			promptPosition : "topRight",//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
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
		
		var year = document.getElementById("year").value;
		var yearId = document.getElementById("yearId").value;
		var monthId = document.getElementById("monthId").value;
		
		document.dataForm.tdmYearseason.value =  year+yearId;
		document.dataForm.tdmYearmonth.value =  year+"."+monthId;
		
		document.getElementById("sc").disabled = true;

		document.dataForm.submit();

	}
	
	function viewTempMap(){
		
		document.getElementById("zs").disabled = true;
		document.getElementById("zs").value = "加载中...";
		var tdmId = document.getElementById("tdmId").value;
		var id = document.getElementById("id").value;
		document.dataForm.action = "${ctx}/back/dataCount_viewMap.do?tdmId="+tdmId+"&id="+id;
		document.dataForm.submit();  
	}
	
	function goBackList() {
		
		var id = document.getElementById("id").value;
		document.dataForm.action = "${ctx}/back/dataCount_list.do?id="+id;
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
				enctype="multipart/form-data"
				action="${ctx}/back/dataCount_updateSave.do">
				
				<span> <input type="hidden" name="id" id="id" value="${id}"></span>

				<span> <input type="hidden" name="tdmId" id="tdmId" value="${tdmId}"></span>

				<table cellpadding="0" cellspacing="0" border="0" class="uiTable" id="tempMess">
					
					<c:forEach var="obj" items="${tdcs}" varStatus="vs">

						<c:choose>
							<c:when test="${obj.txdcFlag == 0 }">
								<tr>
									<td width="120">
									<input type="hidden" name="txdcNames" id="txdcNames${vs.index}" value="${obj.txdcName}"> 
									<input	type="hidden" name="txdcFlags" id="txdcFlags${vs.index}"	value="${obj.txdcFlag}"> 
									<input type="hidden" name="txdcOrders" id="txdcOrders${vs.index}"	value="${obj.txdcOrder}"> 
									<input type="hidden" name="txdcSides" id="txdcSides${vs.index}" value="${obj.txdcSide}">
									${vs.index+1}.${obj.txdcName}：
									</td>
									<td colspan="7">
									<input type="text" name="txdcCounts" id="txdcCounts${vs.index}" value="${obj.txdcCount}">
									</td>
								</tr>
							</c:when>
						</c:choose>

						<c:choose>
							<c:when test="${obj.txdcFlag == 1 }">
								<tr>
									<td>
									<input type="hidden" name="txdcNames" id="txdcNames${vs.index}" value="${obj.txdcName}">
									<input	type="hidden" name="txdcFlags" id="txdcFlags${vs.index}"	value="${obj.txdcFlag}"> 
									<input type="hidden" name="txdcOrders" id="txdcOrders${vs.index}"	value="${obj.txdcOrder}"> 
									<input type="hidden" name="txdcSides" id="txdcSides${vs.index}" value="${obj.txdcSide}">
									${vs.index+1}.${obj.txdcName}：
									</td>
									<td>
									<c:forEach var="cc" items="${fn:split(obj.txdcCount,';') }" varStatus="cs">
										<c:choose>
										<c:when test="${cs.index == 0 }"> 
										得分:
										</c:when>
										<c:when test="${cs.index == 1 }"> 
										满分值:
										</c:when>
										<c:when test="${cs.index == 2 }"> 
										个人得分率:
										</c:when>
										<c:when test="${cs.index == 3 }"> 
										等级:
										</c:when>
										<c:when test="${cs.index == 4 }"> 
										团队平均得分率:
										</c:when>
										<c:when test="${cs.index == 5 }"> 
										标准偏差:
										</c:when>
										<c:when test="${cs.index == 6 }"> 
										标准分:
										</c:when>
										</c:choose>
										<input type="text" name="Ccs${vs.index}" id="Ccs${vs.index}${cs.index}" value="${cc}" style="width: 60px" class="validate[required,length[0,10]]">
									 </c:forEach>
									
									
									</td>
								</tr>
							</c:when>
						</c:choose>
						
					</c:forEach>
					
					<tr>
						<td colspan="8" bgcolor="#cdc9c9">
						</td>
					</tr>
					
					<% 
					TxDataCountMain tdcm = (TxDataCountMain)request.getAttribute("tdcm");
					String yearseason = tdcm.getTdmYearseason();
					String year = yearseason.substring(0, 4);
					String season = yearseason.substring(4, 5);
					%>
					
					<tr>
						<td>
						报告标题：
						</td>
						<td colspan="7">
						<input type="text" name="tdmSpare1" id="tdmSpare1"
							class="validate[required,length[0,100]]" value="${tdcm.tdmSpare1}">
						</td>
					</tr>
					
					<tr>
						<td>
						地区：
						</td>
						<td colspan="7">
						<input type="text" name="tdmTestarea" id="tdmTestarea"
							class="validate[required,length[0,100]]" value="${tdcm.tdmTestarea}">
						</td>
					</tr>
					
					<tr>
						<td>
						排名：
						</td>
						<td colspan="7">
						<input type="text" name="tdmSpare2" id="tdmSpare2"
							class="validate[required,custom[number],length[1,5]]" value="${tdcm.tdmSpare2}">
						</td>
					</tr>
					
					<tr>
						<td>
						百分位：
						</td>
						<td colspan="7">
						<input type="text" name="tdmSpare3" id="tdmSpare3"
							class="validate[required,length[0,100]]" value="${tdcm.tdmSpare3}">
						</td>
					</tr>
					
					<tr>
						<td>
						潜能发展总分：
						</td>
						<td colspan="7">
						<input type="text" name="tdmSpare4" id="tdmSpare4"
							class="validate[required,length[0,100]]" value="${tdcm.tdmSpare4}">
						</td>
					</tr>
					
					<% 
					String say = tdcm.getTdmSpare6();
					String[] count = say.split("\\|\\|");
					%>
					<tr>
						<td>
						标准总分：
						</td>
						<td colspan="7">
						<input type="text" name="count3" id="count3"
							class="validate[required,length[0,100]]" value="<%=count[2] %>">
						</td>
					</tr>
					
					
					<tr>
						<td>
						考试年份：
						<input type="text" name="year" id="year" value="<%=year %>" style="width: 40px"
						class="validate[required,custom[number],length[4,4]]" maxlength="4">
						</td>
						<td colspan="7">
						
						考试月份:
						<%
						String time = tdcm.getTdmYearmonth();
						String month = time.substring(5);
						%>
						<select name="monthId" id="monthId"
							class="validate[required,length[0,2]]" style="width: 60px">
									<option value="1" <% if(month.equals("1")){ %> selected="selected" <%} %>>1月</option>
									<option value="2" <% if(month.equals("2")){ %> selected="selected" <%} %>>2月</option>
									<option value="3" <% if(month.equals("3")){ %> selected="selected" <%} %>>3月</option>
									<option value="4" <% if(month.equals("4")){ %> selected="selected" <%} %>>4月</option>
									<option value="5" <% if(month.equals("5")){ %> selected="selected" <%} %>>5月</option>
									<option value="6" <% if(month.equals("6")){ %> selected="selected" <%} %>>6月</option>
									<option value="7" <% if(month.equals("7")){ %> selected="selected" <%} %>>7月</option>
									<option value="8" <% if(month.equals("8")){ %> selected="selected" <%} %>>8月</option>
									<option value="9" <% if(month.equals("9")){ %> selected="selected" <%} %>>9月</option>
									<option value="10" <% if(month.equals("10")){ %> selected="selected" <%} %>>10月</option>
									<option value="11" <% if(month.equals("11")){ %> selected="selected" <%} %>>11月</option>
									<option value="12" <% if(month.equals("12")){ %> selected="selected" <%} %>>12月</option>
						</select>
						
						考试季节：
						<select name="yearId" id="yearId" class="validate[required,length[0,1]]" style="width: 60px">
								<option value="1" <% if(season.equals("1")){ %> selected="selected" <%} %>>春</option>
								<option value="2" <% if(season.equals("2")){ %> selected="selected" <%} %>>夏</option>
								<option value="3" <% if(season.equals("3")){ %> selected="selected" <%} %>>秋</option>
								<option value="4" <% if(season.equals("4")){ %> selected="selected" <%} %>>冬</option>
						</select>
						
						<input type="hidden" name="tdmYearseason" id="tdmYearseason" value="">
						<input type="hidden" name="tdmYearmonth" id="tdmYearmonth" value="">
						
						</td>
					</tr>
					
					<tr>
						<td>
						综合分析：
						</td>
						<td colspan="7">
						<textarea name="zh" id="zh" style="width: 600px;height: 100px" ><%=count[0] %></textarea>
						</td>
					</tr>
					
					<tr>
						<td>
						专家建议：
						</td>
						<td colspan="7">
						<textarea name="zj" id="zj" style="width: 600px;height: 100px"><%=count[1] %></textarea>
						</td>
					</tr>



				</table>

				<div class="page">
				<c:choose>
					<c:when test="${flag > 0 }">
					<input id="sc" type="button" value="保 存" class="but_shop"	onclick="addTempMess()" />&nbsp;&nbsp;
					</c:when>
				</c:choose>
					<input id="zs" type="button" value="图表展示" class="but_shop"	onclick="viewTempMap()" />&nbsp;&nbsp;	
					<input type="button" value="返 回" class="but_shop" onclick="goBackList()" />

				</div>
			</form>
		</div>
	</div>
</body>
</html>
