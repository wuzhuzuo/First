<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>...</title>
<%@ include file="../../commons/inc.jsp"%>
<script type="text/javascript">
	/**
	$(document).keydown(function(event){
		
		if(event.keyCode == 13){
			$('from').each(function(){
				event.preventDefault();
			})
		}
		
	}) 
	 **/

	$(document).ready(function() {
		document.onkeydown = function(evt) {
			if (evt.keyCode == 13) {
				return;
			}

		}

	})

	function exlOut() {

		var mId = new Array();

		var inputAr = $("input[name='leftCheck']:checked");

		if (inputAr.length == 0) {

			alert("请选择学生!!");

			return false;
		}

		$("input[name='leftCheck']:checked").each(function() {

			mId.push($(this).attr("id"));

		});

		var id = document.getElementById("id").value;
		pagedForm.action = "${ctx}/back/dataCount_exc.do?mId=" + mId + "&id="
				+ id;
		pagedForm.submit();
		pagedForm.action = "${ctx}/back/dataCount_list.do?";

	}

	function exlAll() {

		var tdmName = document.getElementById("tdmName").value;
		var tdmTestnum = document.getElementById("tdmTestnum").value;
		var tdmSpare5 = document.getElementById("tdmSpare5").value;
		var tdmSchool = document.getElementById("tdmSchool").value;
		var tdmClass = document.getElementById("tdmClass").value;
		var tdmGrade = document.getElementById("tdmGrade").value;
		var tdmTestarea = document.getElementById("tdmTestarea").value;
		var tdmYearseason = document.getElementById("tdmYearseason").value;
		var tdmYearmonth = document.getElementById("tdmYearmonth").value;

		if (tdmName == "" && tdmTestnum == "" && tdmSpare5 == ""
				&& tdmSchool == "" && tdmClass == "" && tdmGrade == ""
				&& tdmTestarea == "" && tdmYearseason == ""
				&& tdmYearmonth == "") {
			alert("请填写查询条件,并进行一次查询操作!");
			return false;
		}

		var num = document.getElementsByName("totalRows");
		
		if (num[0].value == 0) {
			alert("导出数为0，请核对查询条件!");
			return false;
		}

		if (num[0].value > 0) {
			if (confirm("确定要进行[" + num[0].value + "条]数据导出操作吗？点击确定后请勿做其他操作！") == false) {
				return false;
			}
		}
		
		var id = document.getElementById("id").value;
		pagedForm.action = "${ctx}/back/dataCount_excAll.do?id=" + id;
		pagedForm.submit();
		pagedForm.action = "${ctx}/back/dataCount_list.do?";

	}

	function del() {

		var mId = new Array();

		var inputAr = $("input[name='leftCheck']:checked");

		if (inputAr.length == 0) {

			alert("请选择学生!!");

			return false;
		}

		$("input[name='leftCheck']:checked").each(function() {

			mId.push($(this).attr("id"));
		});

		if (confirm("确定要删除数据？删除后不可恢复！") == false) {
			return false;
		}
		var id = document.getElementById("id").value;
		pagedForm.action = "${ctx}/back/dataCount_del.do?mId=" + mId + "&id="
				+ id;
		pagedForm.submit();
	}

	function check() {
		pagedForm.action = "${ctx}/back/dataCount_checkList.do";
		pagedForm.submit();
	}
</script>
</head>
<body>

	<form id="pagedForm" name="pagedForm" method="post"
		action="${ctx}/back/dataCount_list.do">
		<div class="main">
			<div class="search">

				<label>姓名:<input value="${tdcm.tdmName}" id="tdmName"
					name="tdmName" size="10" /></label> <label>考号:<input
					value="${tdcm.tdmTestnum}" id="tdmTestnum" name="tdmTestnum"
					size="10" /></label> <label>身份证号:<input value="${tdcm.tdmSpare5}"
					id="tdmSpare5" name="tdmSpare5" size="20" /></label> <input type="hidden"
					value="${tdcm.tdmSchool}" id="tdmSchool" name="tdmSchool" /> <input
					type="hidden" value="${tdcm.tdmClass}" id="tdmClass"
					name="tdmClass" /> <input type="hidden" value="${tdcm.tdmGrade}"
					id="tdmGrade" name="tdmGrade" /> <input type="hidden"
					value="${tdcm.tdmTestarea}" id="tdmTestarea" name="tdmTestarea" />
				<input type="hidden" value="${tdcm.tdmYearseason}"
					id="tdmYearseason" name="tdmYearseason" /> <input type="hidden"
					value="${tdcm.tdmYearmonth}" id="tdmYearmonth" name="tdmYearmonth" />

				<label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="submit" value="搜 索" id="btn_search" />
				</label> <label> <input type="button" value="高级搜索" id="btn_search1"
					onclick="check()" />
				</label>

			</div>
			<div class="shop" id="shop" style="left: 10px; right: 10px;">
				<p class="left">
					<input type="button" class="but_shop"
						onclick="addInput('${ctx}/back/dataCount_add.do?id=${id}')"
						value="新 增" /> <input type="button" class="but_shop"
						onclick="exlOut()" value="导 出" />
					<c:choose>
						<c:when test="${flag > 0 }">
							<input type="button" class="but_shop" onclick="del()" value="删 除" />
							<input type="button" class="but_shop" onclick="exlAll()"
								value="批量导出" />
						</c:when>
					</c:choose>

				</p>
			</div>
			<span> <input type="hidden" name="id" id="id" value="${id}"></span>

			<table cellpadding="0" cellspacing="0" border="0" class="uiTable">
				<tr>
					<th width="30"><input type="checkbox"
						onclick="selAll('leftCheck',this)" /></th>
					<th width="35">序号</th>
					<th>姓名</th>
					<th>考号</th>
					<th>学校</th>
					<th>年级</th>
					<th>班级</th>
					<th>考试时间</th>
				</tr>

				<c:forEach var="obj" items="${tdcms}" varStatus="vs">
					<tr class="${vs.count % 2 == 0 ? 'jg' : ''}">
						<td><input type="checkbox" name="leftCheck" id="${obj.tdmId}" /></td>
						<td>${vs.index+1}</td>
						<td><a
							href="${ctx}/back/dataCount_update.do?tdmId=${obj.tdmId}&id=${id}" />
							${obj.tdmName}</td>
						<td>${obj.tdmTestnum}</td>
						<td>${obj.tdmSchool}</td>
						<td>${obj.tdmGrade}</td>
						<td>${obj.tdmClass}</td>
						<td>${obj.tdmYearmonth}</td>
					</tr>
				</c:forEach>
			</table>
			${page.pagedView}
		</div>
	</form>
</body>
</html>