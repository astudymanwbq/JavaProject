<%--
  Created by IntelliJ IDEA.
  User: WUBQ2
  Date: 2017-12-12
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登录页面</title>
    <%--css--%>
    <style type="text/css">
        .Absolute-Center {
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            width: 50%;
            height: 40%;
        }

        .table-Center {
            width: 60%;
            height: 60%;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
        }

        .setWhite {
            color: white;
        }

    </style>
    <link rel="stylesheet" href="/resource/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <%--cdn的js--%>
    <script type="text/javascript" src="/resource/static/js/jquery-3.2.1.min.js"></script>
   <%-- <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>--%>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/resource/layui/layui.js"></script>
</head>
<body style="background-image:url(/resource/page/start.png);text-align: center">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend class="setWhite" style="text-align: center">豆瓣电影数据爬取分析展示系统</legend>
</fieldset>
<div>
    <div class=" Absolute-Center"
         style="border-style:initial;background-color: white;border-style: wave;border-radius:20px">
        <form id="loginForm">
            <table class="table-Center">
                <tbody>
                <tr>
                    <td><label class="layui-form-label">账号：</label></td>
                    <td><input id=account type="text" name="account" placeholder="请输入账号"
                               class="form-control"></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label">密码：</label></td>
                    <td><input id="password" type="password" name="password" placeholder="请输入密码" class="form-control">
                    </td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"></label></td>
                    <td><input id="checkCode" name="checkCode" placeholder="请输入验证码" type="text" class="form-control">
                    </td>
                    <td>&nbsp;&nbsp;<img src="/image/imageCode" id="validateImg" alt="验证码" title="单击更新验证码"
                                         onclick="reloadImage();"></td>
                </tr>
                <%--<tr>
                    <td></td>
                    <td>
                        <label class="checkbox">
                            <input id="isRemember" type="checkbox" name="isRemember"> 记住登录</label>
                    </td>
                </tr>--%>
                <tr>
                    <td>
                        <button data-toggle="modal" data-target="#myModal" class="btn btn-info" type="button"
                                name="register">注册
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-info" type="button" name="reset" onclick="resetInfo();">重置</button>
                    </td>
                    <td>
                        <button class="btn btn-info" type="button" name="login" onclick="check();">登录</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>

<!-- 注册模态框 -->
<div class="modal fade table-Center" style="overflow: hidden;" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">注册账号</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="registerForm">
                    <table class="">
                        <tbody>
                        <tr>
                            <td><label class="control-label">账号：</label></td>
                            <td><input class="form-control" type="text" name="account" id="registerAccount"
                                       placeholder="请输入账号"></td>
                        </tr>
                        <tr>
                            <td><label class="control-label">用户名：</label></td>
                            <td><input class="form-control" type="text" name="userName" id="registerUsername"
                                       placeholder="请输入用户名"></td>
                        </tr>
                        <tr>
                            <td><label class="control-label">密码：</label></td>
                            <td><input class="form-control" type="password" name="password" id="registerPassword"
                                       placeholder="请输入密码"></td>
                        </tr>
                        <tr>
                            <td><label class="control-label">确认密码：</label></td>
                            <td><input class="form-control" type="password" name="ensurePassword" id="ensurePassword"
                                       placeholder="请确认密码">
                            </td>
                        </tr>
                        <tr>
                            <td><label class="control-label">邮箱：</label></td>
                            <td><input class="form-control" type="email" name="email" id="email" placeholder="请输入邮箱">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="modal-footer">
                        <button id="closeWindow" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="submitForm" type="button" class="btn btn-primary" onclick="validate();">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });
    /*$('#myModal').modal({backdrop: 'static', keyboard: true});*/
    /*登录*/
    function loginSystem() {
        var account = $("#account").val();
        var password = $("#password").val();
        /*
                var isRemember = $("#isRemember").is(":checked");
        */
        if (account === null || account === undefined || account === "" || password === null || password === undefined || password === "") {
            layer.msg("账号，密码不能为空 请输入账号，密码！");
        }
        else {
            //TODO 冻结按钮
            $.ajax({
                url: "/douban/login?account=" + account + "&password=" + password,
                type: "GET",
                async: false,
                success: function (result) {
                    //账号密码正确
                    if (result.success) {
                        layer.msg(result.msg);
                        //跳转页面
                        turnToMainPage(result.data);
                    }
                    else {
                        layer.msg(result.msg);
                        $("#checkCode").val("");
                        //刷新验证码
                        reloadImage();
                    }
                }
            })
        }
    }

    /*跳转页面*/
    function turnToMainPage(page) {
        window.location.href = page
    }

    /* 重置信息*/
    function resetInfo() {
        $("#account").val("");
        $("#password").val("");
        $("#checkCode").val("");
    }

    /* 验证信息*/
    function validate() {
        var registerAccount = $("#registerAccount").val();
        var registerUsername = $("#registerUsername").val();
        var registerPassword = $("#registerPassword").val();
        var ensurePassword = $("#ensurePassword").val();
        var email = $("#email").val();
        if (registerAccount === "" || registerUsername === "" || registerPassword === "" || ensurePassword === "" || email === "") {
            layer.msg("请填写完整注册信息");
        }
        else {
            if (registerPassword !== ensurePassword) {
                layer.msg("两次填写的密码不一致");
            } else {
                register();
            }
        }
        ;
    }

    /*注册账号*/
    function register() {
        $("#submitForm").attr("disabled", true);
        var registerInfo = $("#registerForm").serialize();
        // language=JQuery-CSS
        /*var registerInfoForm = new FormData();
        registerInfoForm.append()*/
        $.ajax({
            type: "POST",
            async: true,
            url: "/douban/account/register",
            data: registerInfo,
            success: function (result) {
                debugger;
                if (result.success) {
                    $("#submitForm").attr("disabled", false);
                    layer.msg("注册成功，请登录");
                    window.setTimeout("flush()", 1000);
                }
                else {
                    $("#submitForm").attr("disabled", false);
                    layer.msg(result.msg);
                }
            },
            error: function () {
                $("#submitForm").attr("disabled", false);
                layer.msg("注册失败");
            }
        })
    }

    /* 刷新页面*/
    function flush() {
        window.location.reload()
    }

    /*更新验证码*/
    function reloadImage() {
        $("#validateImg").attr("src", $("#validateImg").attr("src").split("?")[0] + "?" + new Date().getTime())
    }

    /*验证验证码*/
    function check() {
        var checkCode = $("#checkCode").val();
        $.ajax({
            url: "/image/checkCode?checkCode=" + checkCode,
            type: "GET",
            async: true,
            success: function (data) {
                if (data == "1") {
                    //验证码验证成功,尝试登录系统
                    loginSystem();
                }
                else {
                    layer.msg("验证码错误！！！");
                    $("#checkCode").val("");
                    //刷新验证码
                    reloadImage();
                }
            }

        })
    }

    //监听模态框关闭 清除数据
    $("#myModal").on("hidden.bs.modal", function () {
        var registerAccount = $("#registerAccount").val("");
        var registerUsername = $("#registerUsername").val("");
        var registerPassword = $("#registerPassword").val("");
        var ensurePassword = $("#ensurePassword").val("");
        var email = $("#email").val("");
    });
</script>
</body>
</html>
