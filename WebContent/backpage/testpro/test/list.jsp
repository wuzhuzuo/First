<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>...</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	function del() {


	var mId = new Array();

	var inputAr = $("input[name='leftCheck']:checked");

	if (inputAr.length == 0) {

		alert("请选择模板!!");

		return false;
	}

	$("input[name='leftCheck']:checked").each(function() {

		mId.push($(this).attr("id"));
	});
	
	if(confirm("确定要删除数据？删除后不可恢复！")==false){
		return false;
	}
	var id = document.getElementById("id").value;
	pagedForm.action = "${ctx}/back/test_testDelete.do?mId="+mId+"&id="+id;
    pagedForm.submit();   
}
</script>
</head>
<body>

	<form id="pagedForm" name="pagedForm" method="post"
		action="${ctx}//back/test_testlist.do">
		<div class="main">
			<div class="search">
			
					<label>模板名称:<input value="${obj.tymmName}" id="tymmName" name="tymmName" size="30" /></label>
					
					<label> <input type="submit" value="搜 索"  id="btn_search" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>
			</div>
			<div class="shop" id="shop" style="left: 10px; right: 10px;">
			<c:choose>
				<c:when test="${flag > 0 }">
				<p class="left">
					<input type="button" class="but_shop"
						onclick="addInput('${ctx}/back/test_test.do?id=${id}')" value="新 增" />
						
					<input type="button" class="but_shop" onclick="del()" value="删 除" />
					
				</p>
				</c:when>
			</c:choose>
			</div>
			<span> <input type="hidden" name="id" id="id" value="${id}"></span>
			
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="30"><input type="checkbox"
						onclick="selAll('leftCheck',this)" /></th>
						
					<th width="35">序号</th>
					<th>模板名称</th>
				</tr>

				<c:forEach var="obj" items="${tms}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td><input type="checkbox" name="leftCheck"	id="${obj.tymmId}" /></td>
						<td>${vs.index+1}</td>
						<td><a href="${ctx}/back/test_testUpdate.do?tymmId=${obj.tymmId}&id=${id}" /> ${obj.tymmName}</td>
					</tr>
				</c:forEach>
			</table>
			${page.pagedView}
		</div>
	</form>
</body>
</html>