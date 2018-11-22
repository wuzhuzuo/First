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

		alert("请选择要删除的学生姓名!!");

		return false;
	}

	$("input[name='leftCheck']:checked").each(function() {

		mId.push($(this).attr("id"));
	});
	
	if(confirm("确定要删除数据？删除后不可恢复！")==false){
		return false;
	}
	
	pagedForm.action = "${ctx}/back/txno_Delete.do?mId="+mId;
    pagedForm.submit();   
}

</script>
</head>
<body>

	<form id="pagedForm" name="pagedForm" method="post"
		action="${ctx}//back/txno_list.do">
		<div class="main">
			<div class="search">
			
					<label>姓名:<input id="txnoName" name="txnoName" size="15" /></label>
					<label>考号:<input id="txnoSpare1" name="txnoSpare1" size="15" /></label>
					
					<label> <input type="submit" value="搜 索"  id="btn_search" class="btn_search" onMouseOver="this.className='btn_search_over'" onMouseOut="this.className='btn_search'" />
					</label>
			</div>
			<div class="shop" id="shop" style="left: 10px; right: 10px;">
			<c:choose>
				<c:when test="${flag > 0 }">
				<p class="left">
					<input type="button" class="but_shop"
						onclick="addInput('${ctx}/back/txno_add.do')" value="新 增" />
						
					<input type="button" class="but_shop"
						onclick="addInput('${ctx}/back/txno_upload.do')" value="导 入" />
						
					<input type="button" class="but_shop" onclick="del()" value="删 除" />
					
				</p>
				</c:when>
			</c:choose>
			</div>
			
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="30"><input type="checkbox"
						onclick="selAll('leftCheck',this)" /></th>
				
					<th width="50">序号</th>
					<th width="100">姓名</th>
					<th width="100">考号</th>
					<th>备注</th>
				</tr>

				<c:forEach var="obj" items="${txnos}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td><input type="checkbox" name="leftCheck"	id="${obj.txnoId}" /></td>
						<td>${obj.txnoId}</td>
						<td><a href="${ctx}/back/txno_update.do?id=${obj.txnoId}" /> ${obj.txnoName}</td>
						<td>${obj.txnoSpare1}</td>
						<td>${obj.txnoRemark}</td>
					</tr>
				</c:forEach>
			</table>
			${page.pagedView}
		</div>
	</form>
</body>
</html>