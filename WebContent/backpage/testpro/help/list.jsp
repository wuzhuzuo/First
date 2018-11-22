<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>...</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>

	<form id="pagedForm" name="pagedForm" method="post"
		action="${ctx}//back/test_testlist.do">
		<div class="main">

			<span> <input type="hidden" name="id" id="id" value="${id}"></span>
			
			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="35">序号</th>
					<th>名称</th>
				</tr>

				<tr class="jg">
						<td align="center">1</td>
						<td>使用说明</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>