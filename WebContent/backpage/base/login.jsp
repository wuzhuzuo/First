<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>中小学综合测评系统</title>
<%@ include file="../commons/taglibs.jsp"%>
<%@ include file="../commons/inc.jsp"%>
<script type="text/javascript">
function com() {
	
	loginFrm.submit();
	
}
	
	
	
	
</script>
    <style type="text/css">
        body {
            MARGIN: 0px;
        }

        .STYLE1 {
            font-size: 14px
        }

        .STYLE2 {
            font-size: 15px
        }
    </style>
    <style type="text/css">
        .div1 {
            padding-left: 30px;
            background: url(${ctx}/backpage/base/imagess/1.png) 4px no-repeat;
            background-color: #FFFFFF;
            width: 140px;
            height: 25px;
            line-height: 25px;
            border: 1px solid #ccc;
        }

        .div2 {
            padding-left: 30px;
            background: url(${ctx}/backpage/base/imagess/2.png) 4px no-repeat;
            background-color: #FFFFFF;
            width: 140px;
            height: 25px;
            line-height: 25px;
            border: 1px solid #ccc;
        }

        input {
            border: 0;
            font-size: 18px;
        }
        a:link {
            color:#3F4476;
            text-decoration:blink;
        }
    </style>
</head>

<body>
<form id="loginFrm" method="post"
			action="<%=contextPath%>/login.do?cmd=login">
<img src="${ctx}/backpage/base/imagess/bg.jpg" width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0">
<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:100px">
    <tr>
        <td height="200" style="text-align: center">
                <span><img src="${ctx}/backpage/base/imagess/logo.png" width="40" height="20"><span>
                <span style="font-size: 30px;font-weight:800">中小学综合测评系统</span>

        </td>
    </tr>
</table>
<table width="543" height="255" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td background="${ctx}/backpage/base/imagess/kuang.png">
            <table width="320" border="0" align="center" cellpadding="0" cellspacing="15">
                <tr>
                    <td width="25%"><span class="STYLE2">账  号：</span></td>                    <td width="75%">
                        <div class="div1">
                            <input name="username" type="text" id="username"
                                   style="height:22px;width:137px;outline:none; vertical-align:middle;"/>
                        </div>
                        <strong class="red">${msg}</strong>
                    </td>
                </tr>
                <tr>
                    <td width="25%"><span class="STYLE2">密  码：</span></td>
                    <td width="75%">
                        <div class="div2">
                            <input name="password" type="password" id="password"
                                   style="height:22px;width:137px;outline:none;vertical-align:middle;"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center"><img src="${ctx}/backpage/base/imagess/login.jpg" width="81" height="26" onclick="com()"/></div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</form>
</body>
</html>
