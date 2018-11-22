<%@page import="java.util.List"%>
<%@page import="com.back.testpro.model.TxDataCount"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<%@ include file="../../commons/taglibs.jsp"%>
<%@ include file="../../commons/inc.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/backpage/scripts/msgbox.js"></script>

<script type="text/javascript">
	function download() {

		document.dataForm.submit();

	}

	function doPrint() {
		bdhtml = window.document.body.innerHTML;
		sprnstr = "<!--startprint-->";
		eprnstr = "<!--endprint-->";
		prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
		window.document.body.innerHTML = prnhtml;
		if (window.print)
			window.print();
		document.location = document.location;
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
		<div class="maintit"></div>
		<!-- 内容开始 -->
		<div class="centent" style="padding-top: 10px;">
			<form id="dataForm" name="dataForm" method="post"
				action="${ctx}/back/dataCount_exc.do">
				<span> <input type="hidden" name="mId" id="mId" value="${tdcm.tdmId}"></span>
				<span> <input type="hidden" name="id" id="id" value="${tdcm.tdmMrid}"></span>
				<!--startprint-->
				<table cellpadding="0" cellspacing="0" border="0" class="uiTable"
					id="tempMess">
					
					<%
					List<TxDataCount> list = (List)request.getAttribute("tdcs");
					%>
				
				
					<tr align="center">
						<td colspan="18"><strong>${tdcm.tdmSpare1}</strong></td>
					</tr>

					<tr>
						<td align="center" bgcolor="#90EE90">考区</td>
						<td align="center" bgcolor="#90EE90">组别</td>
						<td align="center" bgcolor="#90EE90" colspan="2">考号</td>
						<td align="center" bgcolor="#90EE90" colspan="2">姓名</td>
						<td align="center" bgcolor="#90EE90" colspan="2">ID</td>
						<td align="center" bgcolor="#90EE90">年级</td>
						<td align="center" bgcolor="#90EE90">班级</td>
						<td align="center" bgcolor="#90EE90">性别</td>
						<td align="center" bgcolor="#90EE90"></td>
						<td align="center" bgcolor="#90EE90"></td>
						<td align="center" bgcolor="#90EE90"></td>
						<td align="center" bgcolor="#90EE90" colspan="4">考 点</td>

					</tr>

					<tr>
						<td align="center">${tdcm.tdmTestarea}</td>
						<td align="center"><%=list.get(0).getTxdcCount()%> </td>
						<td align="center" colspan="2">${tdcm.tdmTestnum}</td>
						<td align="center" colspan="2">${tdcm.tdmName}</td>
						<td align="center" colspan="2"><%=list.get(1).getTxdcCount()%></td>
						<td align="center">${tdcm.tdmGrade}</td>
						<td align="center">${tdcm.tdmClass}</td>
						<td align="center"><%=list.get(5).getTxdcCount()%></td>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center"></td>
						<td colspan="4" align="center">${tdcm.tdmSchool}</td>
					</tr>


					<tr>
						<td colspan="7" bgcolor="#87ceeb" align="center">L区</td>
						<td colspan="7" bgcolor="#87ceeb" align="center">R区</td>
						<td colspan="4" bgcolor="#87ceeb" align="center">追踪</td>
					</tr>
					
					<tr>
						<td align="center" bgcolor="#FF8C00" align="center">维度<br>内容</td>
						<td align="center" bgcolor="#FF8C00" align="center">个人<br>得分率
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">地区<br>得分率
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">差值</td>
						<td align="center" bgcolor="#FF8C00" align="center">选手<br>得分
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">等级</td>
						<td align="center" bgcolor="#FF8C00" align="center">满分值</td>

						<td align="center" bgcolor="#FF8C00" align="center">维度<br>内容
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">个人<br>得分率
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">地区<br>得分率
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">差值</td>
						<td align="center" bgcolor="#FF8C00" align="center">选手<br>得分
						</td>
						<td align="center" bgcolor="#FF8C00" align="center">等级</td>
						<td align="center" bgcolor="#FF8C00" align="center">满分值</td>
						
						<td align="center" bgcolor="#FF8C00" align="center"></td>
						<td align="center" bgcolor="#FF8C00" align="center">
							${zzName1}
						</td>
						
						<td align="center" bgcolor="#FF8C00" align="center">
							${zzName2}
						</td>
						
						<td align="center" bgcolor="#FF8C00" align="center">
							${zzName3}
						</td>
					</tr>
					
					
					
					<c:forEach var="listZ" items="${listZ}" begin="0" end="${maxLine}" step="1">
					<tr>
					
						<td align="center" bgcolor="#90EE90">
							${listZ[0]}
						</td>
						
						<td align="center" bgcolor="#ffff00">
							<c:choose>
								<c:when test="${listZ[1] >= LmaxIn }">
								<font color="red">${listZ[1]}</font>
								</c:when>
								<c:when test="${listZ[1] <= LminIn }">
								<font color="green">${listZ[1]}</font>
								</c:when>
								<c:otherwise >
								${listZ[1]}
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">${listZ[2]}</td>
						<td align="center" bgcolor="#ffff00">${listZ[3]}</td>
						<td align="center">${listZ[4]}</td>
						<td align="center" bgcolor="#ffff00">${listZ[5]}</td>
						<td align="center">${listZ[6]}</td>
						
						
						
						<td align="center" bgcolor="#90EE90">
						${listZ[7]}
						</td>
						<td align="center" bgcolor="#ffff00">
							<c:choose>
								<c:when test="${listZ[8] >= RmaxIn }">
								<font color="red">${listZ[8]}</font>
								</c:when>
								<c:when test="${listZ[8] <= RminIn }">
								<font color="green">${listZ[8]}</font>
								</c:when>
								<c:otherwise >
								${listZ[8]}
								</c:otherwise>
							</c:choose>
						</td>
						<td align="center">${listZ[9]}</td>
						<td align="center" bgcolor="#ffff00">${listZ[10]}</td>
						<td align="center">${listZ[11]}</td>
						<td align="center" bgcolor="#ffff00">${listZ[12]}</td>
						<td align="center">${listZ[13]}</td>
						<td align="center" bgcolor="#90EE90">${listZ[14]}</td>
						<td align="center">${listZ[15]}</td>
						<td align="center" bgcolor="#ffff00">${listZ[16]}</td>
						<td align="center">${listZ[17]}</td>
					</tr>
					</c:forEach>
					
					<tr>
					
						<td align="center" bgcolor="#FF8C00" align="center">小计</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount1}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount2}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount3}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount4}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount5}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Lcount6}</td>
						<td align="center" bgcolor="#FF8C00" align="center">小计</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount1}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount2}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount3}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount4}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount5}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${Rcount6}</td>
						<td align="center" bgcolor="#FF8C00" align="center">标准总分</td>
						<td align="center" bgcolor="#FF8C00" align="center">${stand1}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${stand2}</td>
						<td align="center" bgcolor="#FF8C00" align="center">${stand3}</td>
					
					</tr>

					

					<tr>
						<!-- 图形展示 -->
						<td colspan="7" align="center"><IMG src="${ctx}/images/${fileName1}">
						</td>
						<td colspan="7" align="center" bgcolor="#f5f5f5"><IMG src="${ctx}/images/${fileName2}">
						</td>
						<td colspan="4" align="center"><IMG src="${ctx}/images/${fileName3}">
						</td>
					</tr>

					<tr>
						<td align="center">综&nbsp;<br>合&nbsp;<br>分&nbsp;<br>析&nbsp;
						</td>
						<td align="left" colspan="17">
						${gen}
						 <br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;依据图表中各项知识点的[个人得分率][差值][等级]等项得失，及时查缺补漏。
						</td>
					</tr>

					<tr>
						<td align="center">专&nbsp;<br>家&nbsp;<br>建&nbsp;<br>议&nbsp;
						</td>
						<td align="left" colspan="17">
						${adv}
						</td>
					</tr>
					

				</table>
				<!--endprint-->


				<div class="page">

					<input type="button" value="导 出" class="but_shop"
						onclick="download()" />&nbsp;&nbsp;<input type="button"
						value="打 印" class="but_shop" onclick="doPrint()" />&nbsp;&nbsp; <input
						type="button" value="返回列表" class="but_shop" onclick="goBackList()" />

				</div>
			</form>
		</div>
	</div>
</body>
</html>
