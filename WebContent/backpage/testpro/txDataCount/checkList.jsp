<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>...</title>
<%@ include file="../../commons/inc.jsp"%>
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

	function check() {
		
		var year = document.getElementById("year").value;
		var yearId = document.getElementById("yearId").value;
		var monthId = document.getElementById("monthId").value;
		
		if(year!="" && yearId!=""){
			document.dataForm.tdmYearseason.value =  year+yearId;
		}
		if(year!="" && yearId==""){
			document.dataForm.tdmYearseason.value =  year+"_";
		}
		if(year=="" && yearId!=""){
			document.dataForm.tdmYearseason.value =  "____"+yearId;
		}
		
		if(year!="" && monthId!=""){
			document.dataForm.tdmYearmonth.value =  year+"."+monthId;
		}
		if(year!="" && monthId==""){
			document.dataForm.tdmYearmonth.value =  year+"."+"_";
		}
		if(year=="" && monthId!=""){
			document.dataForm.tdmYearmonth.value =  "_"+"."+monthId;
		}
		
		document.dataForm.action = "${ctx}/back/dataCount_list.do";
		document.dataForm.submit();  
	}
</script>
</head>
<body>
<div class="main">
		<div class="maintit">
			<h2>
				<img src="${ctx}/backpage/images/tabicon.gif" width="16" height="16"/> 高级搜索
			</h2>
		</div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataForm" name="dataForm" method="post"
				enctype="multipart/form-data"
				action="${ctx}/back/dataCount_list.do">


				<table cellpadding="0" cellspacing="0" border="0" class="uiTable"
					id="tempMess">
					
				<input type="hidden" name="id" id="id" value="${id}">
				<input type="hidden" name="tdmYearseason" id="tdmYearseason" value="">
				<input type="hidden" name="tdmYearmonth" id="tdmYearmonth" value="">
				
					<tr>
						<td align="right">姓名：</td>
						<td>							
						    <input type="text" id="tdmName" name="tdmName"/>
						</td>
					</tr>
					<tr>
						<td align="right">考号：</td>
						<td>							
						    <input type="text" id="tdmTestnum" name="tdmTestnum"/>
						</td>
					</tr>
					<tr>
						<td align="right">学校：</td>
						<td>							
						    <input type="text" id="tdmSchool" name="tdmSchool"/>
						</td>
					</tr>
					<tr>
						<td align="right">班级：</td>
						<td>							
						    <input type="text" id="tdmClass" name="tdmClass"/>
						</td>
					</tr>
					<tr>
						<td align="right">年级：</td>
						<td>							
						    <input type="text" id="tdmGrade" name="tdmGrade"/>
						</td>
					</tr>
					<tr>
						<td align="right">考区：</td>
						<td>							
						    <input type="text" id="tdmTestarea" name="tdmTestarea"/>
						</td>
					</tr>
					<tr>
						<td align="right">年份：</td>
						<td>							
						    <input type="text" id="year" name="year"/>
						</td>
					</tr>
					<tr>
						<td align="right">月份：</td>
						<td>							
						<select name="monthId" id="monthId">
								<option value="" selected="selected">请选择</option>
									<option value="1">1月</option>
									<option value="2">2月</option>
									<option value="3">3月</option>
									<option value="4">4月</option>
									<option value="5">5月</option>
									<option value="6">6月</option>
									<option value="7">7月</option>
									<option value="8">8月</option>
									<option value="9">9月</option>
									<option value="10">10月</option>
									<option value="11">11月</option>
									<option value="12">12月</option>
						</select>
						</td>
					</tr>
					<tr>
						<td align="right">季节：</td>
						<td>							
						<select name="yearId" id="yearId">
								<option value="" selected="selected">请选择</option>
									<option value="1">春</option>
									<option value="2">夏</option>
									<option value="3">秋</option>
									<option value="4">冬</option>
						</select>
						</td>
					</tr>
					

				</table>
				
				<div class="page" >
					<input id="sc" type="button" value="提 交" class="but_shop"	onclick="check()"/>&nbsp;&nbsp;
					<input type="button" value="返 回" class="but_shop" onclick="history.back(-1)" />
				</div>

			</form>
		</div>
	</div>
</body>
</html>