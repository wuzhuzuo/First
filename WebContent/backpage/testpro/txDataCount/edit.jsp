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
	cities = new Object();

	cities['北京'] = new Array('北京');

	cities['上海'] = new Array('上海');

	cities['天津'] = new Array('天津');

	cities['重庆'] = new Array('重庆');

	cities['河北省'] = new Array('石家庄', '张家口', '承德', '秦皇岛', '唐山', '廊坊', '保定',
			'沧州', '衡水', '邢台', '邯郸');

	cities['山西省'] = new Array('太原', '大同', '朔州', '阳泉', '长治', '晋城', '忻州', '吕梁',
			'晋中', '临汾', '运城');

	cities['辽宁省'] = new Array('沈阳', '朝阳', '阜新', '铁岭', '抚顺', '本溪', '辽阳', '鞍山',
			'丹东', '大连', '营口', '盘锦', '锦州', '葫芦岛');

	cities['吉林省'] = new Array('长春', '白城', '松原', '吉林', '四平', '辽源', '通化', '白山',
			'延边');

	cities['黑龙江省'] = new Array('哈尔滨', '齐齐哈尔', '黑河', '大庆', '伊春', '鹤岗', '佳木斯',
			'双鸭山', '七台河', '鸡西', '牡丹江', '绥化', '大兴安');

	cities['江苏省'] = new Array('南京', '徐州', '连云港', '宿迁', '淮阴', '盐城', '扬州', '泰州',
			'南通', '镇江', '常州', '无锡', '苏州');

	cities['浙江省'] = new Array('杭州', '湖州', '嘉兴', '舟山', '宁波', '绍兴', '金华', '台州',
			'温州', '丽水');

	cities['安徽省'] = new Array('合肥', '宿州', '淮北', '阜阳', '蚌埠', '淮南', '滁州', '马鞍山',
			'芜湖', '铜陵', '安庆', '黄山', '六安', '巢湖', '池州', '宣城');

	cities['福建省'] = new Array('福州', '南平', '三明', '莆田', '泉州', '厦门', '漳州', '龙岩',
			'宁德');

	cities['江西省'] = new Array('南昌', '九江', '景德镇', '鹰潭', '新余', '萍乡', '赣州', '上饶',
			'抚州', '宜春', '吉安');

	cities['山东省'] = new Array('济南', '聊城', '德州', '东营', '淄博', '潍坊', '烟台', '威海',
			'青岛', '日照', '临沂', '枣庄', '济宁', '泰安', '莱芜', '滨州', '菏泽');

	cities['河南省'] = new Array('郑州', '三门峡', '洛阳', '焦作', '新乡', '鹤壁', '安阳', '濮阳',
			'开封', '商丘', '许昌', '漯河', '平顶山', '南阳', '信阳', '周口', '驻马店');

	cities['湖北省'] = new Array('武汉', '十堰', '襄攀', '荆门', '孝感', '黄冈', '鄂州', '黄石',
			'咸宁', '荆州', '宜昌', '恩施', '襄樊');

	cities['湖南省'] = new Array('长沙', '张家界', '常德', '益阳', '岳阳', '株洲', '湘潭', '衡阳',
			'郴州', '永州', '邵阳', '怀化', '娄底', '湘西');

	cities['广东省'] = new Array('广州', '清远', '韶关', '河源', '梅州', '潮州', '汕头', '揭阳',
			'汕尾', '惠州', '东莞', '深圳', '珠海', '江门', '佛山', '肇庆', '云浮', '阳江', '茂名',
			'湛江');

	cities['海南省'] = new Array('海口', '三亚');

	cities['四川省'] = new Array('成都', '广元', '绵阳', '德阳', '南充', '广安', '遂宁', '内江',
			'乐山', '自贡', '泸州', '宜宾', '攀枝花', '巴中', '达川', '资阳', '眉山', '雅安', '阿坝',
			'甘孜', '凉山');

	cities['贵州省'] = new Array('贵阳', '六盘水', '遵义', '毕节', '铜仁', '安顺', '黔东南', '黔南',
			'黔西南');

	cities['云南省'] = new Array('昆明', '曲靖', '玉溪', '丽江', '昭通', '思茅', '临沧', '保山',
			'德宏', '怒江', '迪庆', '大理', '楚雄', '红河', '文山', '西双版纳');

	cities['陕西省'] = new Array('西安', '延安', '铜川', '渭南', '咸阳', '宝鸡', '汉中', '榆林',
			'商洛', '安康');

	cities['甘肃省'] = new Array('兰州', '嘉峪关', '金昌', '白银', '天水', '酒泉', '张掖', '武威',
			'庆阳', '平凉', '定西', '陇南', '临夏', '甘南');

	cities['青海省'] = new Array('西宁', '海东', '西宁', '海北', '海南', '黄南', '果洛', '玉树',
			'海西');

	cities['内蒙古'] = new Array('呼和浩特', '包头', '乌海', '赤峰', '呼伦贝尔盟', '兴安盟', '哲里木盟',
			'锡林郭勒盟', '乌兰察布盟', '鄂尔多斯', '巴彦淖尔盟', '阿拉善盟');

	cities['广西'] = new Array('南宁', '桂林', '柳州', '梧州', '贵港', '玉林', '钦州', '北海',
			'防城港', '南宁', '百色', '河池', '柳州', '贺州');

	cities['西藏'] = new Array('拉萨', '那曲', '昌都', '林芝', '山南', '日喀则', '阿里');

	cities['宁夏'] = new Array('银川', '石嘴山', '吴忠', '固原');

	cities['新疆'] = new Array('乌鲁木齐', '克拉玛依', '喀什', '阿克苏', '和田', '吐鲁番', '哈密',
			'博尔塔拉', '昌吉', '巴音郭楞', '伊犁', '塔城', '阿勒泰');

	cities['香港'] = new Array('香港');

	cities['澳门'] = new Array('澳门');

	cities['台湾'] = new Array('台北', '台南', '其他');

	function set_city(province, city){

		var pv, cv;

		var i, ii;

		pv = province.value;

		cv = city.value;

		city.length = 1;

		if (pv == '')
			return;

		if (typeof (cities[pv]) == 'undefined')
			return;

		for (i = 0; i < cities[pv].length; i++)

		{

			ii = i + 1;

			city.options[ii] = new Option();

			city.options[ii].text = cities[pv][i];

			city.options[ii].value = cities[pv][i];

		}

	}

	$(document).ready(function() {
		$("#tempMess").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#kinMess1").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#kinMess2").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
			success : false,//为true时即使有不符合的也提交表单,false表示只有全部通过验证了才能提交表单,默认false
			promptPosition : "topRight"//,//提示所在的位置，topLeft, topRight, bottomLeft,  centerRight, bottomRight
		});
		$("#tempMess2").validationEngine({
			validationEventTriggers : "keyup blur", //触发的事件  validationEventTriggers:"keyup blur",
			inlineValidation : true,//是否即时验证，false为提交表单时验证,默认true
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
		
		var ef = $("#excelFile").val();

		if (ef == "" || ef.length == 0) {
			alert("请选择上传文件!")
			return false;
		}

		var fileA = ef.split("//");
		var fileB = fileA[fileA.length - 1].toLowerCase().split(".");
		var fileC = fileB[fileB.length - 1];

		if (fileC != "xls") {
			alert("请选择office03版本excel文件!")
			return false;
		}
		
		var year = document.getElementById("year").value;
		var yearId = document.getElementById("yearId").value;
		var monthId = document.getElementById("monthId").value;
		var city = document.getElementById("city").value;
		var modelId = document.getElementById("mcId").value;
		
		
		document.dataForm.tdmYearseason.value =  year+yearId;
		document.dataForm.tdmYearmonth.value =  year+"."+monthId;
		document.dataForm.tdmTestarea.value = city;
		document.dataForm.modelId.value = modelId;

		
		if(confirm("确定进行数据上传吗？确定开始后，请勿进行其他操作并等待上传完毕。")){
		document.getElementById("show1").style.display = "none"; 
		document.getElementById("show2").style.display = "block"; 
		document.dataForm.submit();
		}

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
				enctype="multipart/form-data"
				action="${ctx}/back/dataCount_upload.do">

				<span> <input type="hidden" name="tdmMrid" id="tdmMrid" value="${id}"></span>
				<span> <input type="hidden" name="tdmYearmonth" id="tdmYearmonth" value=""></span>
				<span> <input type="hidden" name="tdmYearseason" id="tdmYearseason" value=""></span>
				<span> <input type="hidden" name="tdmTestarea" id="tdmTestarea" value=""></span>
				
				<span> <input type="hidden" name="modelId" id="modelId" value=""></span>

				<table cellpadding="0" cellspacing="0" border="0" class="uiTable"
					id="tempMess">
					
					<tr>
						<td class="mains">请选择模板：</td>
						<td>							
						<select name="mcId" id="mcId"
							class="validate[required,length[0,100]]">
								<option value="" selected="selected">请选择</option>
								<c:forEach var="obj" items="${tms}" varStatus="vs">
									<option value="${obj.tymmId}">${obj.tymmName}</option>
								</c:forEach>
						</select>
							
						</td>
					</tr>
					
					
					<tr>
						<td></td>
						<td></td>
					</tr>
					
					<tr>
						<td class="mains">请填写成绩报告标题：</td>
						<td><input type="text" name="tdmSpare1" id="tdmSpare1"
							class="validate[required,length[0,100]]"></td>
					</tr>

					<tr>
						<td class="mains">请填写年份：</td>
						<td><input type="text" name="year" id="year"
							class="validate[required,custom[number],length[4,4]]"></td>
					</tr>
					
					<tr>
						<td class="mains">请选择月份：</td>
						<td><select name="monthId" id="monthId"
							class="validate[required,length[0,2]]">
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
						</select></td>
					</tr>
					
					<tr>
						<td class="mains">请选择季节：</td>
						<td><select name="yearId" id="yearId"
							class="validate[required,length[0,1]]">
								<option value="" selected="selected">请选择</option>
									<option value="1">春</option>
									<option value="2">夏</option>
									<option value="3">秋</option>
									<option value="4">冬</option>
						</select></td>
					</tr>


					<tr>
						<td class="mains">地区：</td>
						<td>
						<SELECT name="sheng" id="to_cn"
							onchange="set_city(this, document.getElementById('city')); WYL();"
							style="width: 80px" class="validate[required,custom[chinese]]">

								<option value="">请选择</option>

								<option value=北京>北京</option>

								<option value=上海>上海</option>

								<option value=天津>天津</option>

								<option value=重庆>重庆</option>

								<option value=河北省>河北省</option>

								<option value=山西省>山西省</option>

								<option value=辽宁省>辽宁省</option>

								<option value=吉林省>吉林省</option>

								<option value=黑龙江省>黑龙江省</option>

								<option value=江苏省>江苏省</option>

								<option value=浙江省>浙江省</option>

								<option value=安徽省>安徽省</option>

								<option value=福建省>福建省</option>

								<option value=江西省>江西省</option>

								<option value=山东省>山东省</option>

								<option value=河南省>河南省</option>

								<option value=湖北省>湖北省</option>

								<option value=湖南省>湖南省</option>

								<option value=广东省>广东省</option>

								<option value=海南省>海南省</option>

								<option value=四川省>四川省</option>

								<option value=贵州省>贵州省</option>

								<option value=云南省>云南省</option>

								<option value=陕西省>陕西省</option>

								<option value=甘肃省>甘肃省</option>

								<option value=青海省>青海省</option>

								<option value=内蒙古>内蒙古</option>

								<option value=广西>广西</option>

								<option value=西藏>西藏</option>

								<option value=宁夏>宁夏</option>

								<option value=新疆>新疆</option>

								<option value=香港>香港</option>

								<option value=澳门>澳门</option>

								<option value=台湾>台湾</option>

						</SELECT> 
						<select id="city"  name="shi"
							style="width: 80px" class="validate[required,custom[chinese]]">

								<option value="">请选择</option>

						</select>

						</td>
					</tr>
					
					
					<tr>
						<td class="mains">请选择数据文件：</td>
						<td><input id="excelFile" name="excelFile" type="file"
							accept=".xls" /></td>
					</tr>



				</table>
				
				<span id="show1" style="display: block;">
				<div class="page" >
					<input id="sc" type="button" value="保 存" class="but_shop"	onclick="addTempMess()"/>&nbsp;&nbsp;
					<input type="button" value="返 回" class="but_shop" onclick="history.back(-1)" />
				</div>
				</span>
				<span id="show2" style="display: none;">
				<div align="center">
					<IMG src="${ctx}/backpage/images/flush.jpg">
				</div>
				</span>
			</form>
		</div>
	</div>
</body>
</html>
