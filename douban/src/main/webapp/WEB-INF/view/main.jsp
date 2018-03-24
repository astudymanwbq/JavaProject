<%--
  Created by IntelliJ IDEA.
  User: WUBQ2
  Date: 2018-2-4
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>电影</title>
    <meta charset="utf-8"/>
    <script type="text/javascript" src="/resource/static/js/jquery-3.2.1.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" href="/resource/layui/css/layui.css" media="all"/>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/resource/layui/layui.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <!-- Latest compiled and minified Locales -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
    <%--echartsjs--%>
    <script src="/resource/static/js/echarts.js"></script>
    <style type="text/css">
        .table-Center {
            width: 100%;
            height: 100%;
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
        }
    </style>
</head>
<body <%--style="background-image:url(/resource/page/start.png);text-align: center"--%>>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">movie</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%-- <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>--%>
                <li><a href="">豆瓣电影Top250</a></li>
                <li><a onclick="getNowPlayingMovie()">正在热映</a></li>
                <li><a onclick="getCommingPlayingMovie()">将要上映</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a onclick="getCollection()" data-toggle="modal" data-target="#myCollectModal">我的收藏</a></li>
                <li class="dropdown">
                    <a id="user" href="" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true"
                       aria-expanded="false">吴璧钦<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="" onclick="getInfo()" data-toggle="modal" data-target="#userInfoModal">个人信息</a>
                        </li>
                        </li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/douban/page/logout">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div>
    <input type="hidden" id="movieCommentUrl">
    <div style="padding-bottom: 70px">
        <table>
            <tbody>
            <tr>
                <td><label class="col-form-label">电影名称: </label></td>
                <td><input id="movieName" type="text" placeholder="请输入电影名称" class="form-control"></td>

                <td style="padding-left: 20px"><label class="col-form-label">电影类型: </label></td>
                <td><input id="movieType" type="text" placeholder="请输入电影类型" class="form-control"></td>

                <td style="padding-left: 50px">
                    <button class="btn btn-primary" type="button" name="search" onclick="search();">搜索</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <table id="top250Movie"></table>
    <table id="nowPlayingMovie"></table>
    <table id="commingMovie"></table>
</div>
<%--我的收藏--%>
<div class="modal fade table-Center bd-example-modal-lg" style="overflow: hidden;" id="myCollectModal" tabindex="-1"
     role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myCollectModalLabel">我的收藏</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div style="padding-bottom: 30px" id="labelAndName">
                    <table>
                        <tbody>
                        <tr>
                            <td><label class="col-form-label">电影名称: </label></td>
                            <td><input id="c_name" type="text" placeholder="请输入电影名称" class="form-control"></td>

                            <td style="padding-left: 20px"><label class="col-form-label">标签: </label></td>
                            <td><input id="c_label" type="text" placeholder="请输入标签" class="form-control"></td>

                            <td style="padding-left: 50px">
                                <button class="btn btn-primary" type="button" name="search" onclick="search();">搜索</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <table id="collection"></table>
                <div class="modal-footer">
                    <button id="closeMyCollect" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--热门评论--%>
<div class="modal fade table-Center bd-example-modal-lg" style="overflow: hidden;" id="myModal" tabindex="-1"
     role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">热门评论</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="hotComment"></table>
                <div class="modal-footer">
                    <button id="closeWindow" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="submitForm" data-toggle="modal" data-target="#imageModal" type="button"
                            class="btn btn-primary" onclick="setMovieCommentUrl()">生成词云
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--电影简介--%>
<div class="modal fade table-Center bd-example-modal-lg" style="overflow: hidden;" id="summaryModal" tabindex="-1"
     role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="summaryModalLabel">电影简介</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h3><strong id="name"></strong></h3>
                <div class="col-sm-12">
                    <div class="col-sm-4">
                        <img id="movieimage" src="/resource/page/water.jpg" alt="" style="width:200px; height:300px">
                    </div>
                    <div class="col-sm-8">
                        <div id="info">
                            <table>
                                <tbody>
                                <tr>
                                    <td class="col-sm-4"><label class="control-label">导演：</label></td>
                                    <td class="col-sm-8" id="director"></td>
                                </tr>
                                <br>
                                <tr>
                                    <td class="col-sm-4"><label>编剧：</label></td>
                                    <td class="col-sm-8" id="screenwriter"></td>
                                </tr>
                                <br>
                                <tr>
                                    <td class="col-sm-4"><label>主演：</label></td>
                                    <td class="col-sm-8" id="actor"></td>
                                </tr>
                                <tr>
                                    <td class="col-sm-4"><label>类型：</label></td>
                                    <td class="col-sm-8" id="type"></td>
                                </tr>
                                <tr>
                                    <td class="col-sm-4"><label>上映日期：</label></td>
                                    <td class="col-sm-8" id="releasedDate"></td>
                                </tr>
                                <tr>
                                    <td class="col-sm-4"><label>片长：</label></td>
                                    <td class="col-sm-8" id="length"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <br>
                        <div id="shortcontent">
                            <table>
                                <tr>
                                    <td class="col-sm-4"><label>剧情简介：</label></td>
                                </tr>
                                <tr>
                                    <td class="col-sm-8" id="content"></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="close" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%--词云--%>
<div class="modal fade table-Center bd-example-modal-lg" style="overflow: hidden;" id="imageModal" tabindex="-1"
     role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="imageModallabel">关键词词云</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img id="ciyunImage" src="/resource/page/start.png" alt="" style="width:100%; height: 70%">
                <div class="modal-footer">
                    <button id="closeciyun" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%--修改电影标签--%>
<div class="modal fade table-Center" style="overflow: hidden;" id="collectModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="collectInfoLabel">编辑</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td><label>电影名称：</label></td>
                        <td><input class="form-control" id="collect_movieName" disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td><label>标签：</label></td>
                        <td><input class="form-control" id="label_text"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="closeCollect" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="updateLabel()" type="button" class="btn btn-default" data-dismiss="modal">提交</button>
            </div>
        </div>
    </div>
</div>


<%--评分分布--%>
<div class="modal fade table-Center" style="overflow: hidden;" id="scoreModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="scoreModallabel">评分分布</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="chart" style="width:600px;height:400px"></div>
                <div id="better">
                    <table>
                        <tbody>
                        <tr>
                            <td><label>好于: </label></td>
                            <td id="better_1"></td>
                            <td id="better_2"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button id="closescore" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
//个人信息
<div class="modal fade table-Center" style="overflow: hidden;" id="userInfoModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userInfoLabel">用户个人信息</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td><label>账号：</label></td>
                        <td><input class="form-control" id="account" disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td><label>用户名：</label></td>
                        <td><input class="form-control" id="userName" disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td><label>邮箱：</label></td>
                        <td><input class="form-control" id="email" disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td><label>注册日期：</label></td>
                        <td><input class="form-control" id="createDate" disabled="disabled"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    });
    $(function () {
        $.ajax({
            url: "/douban/userInfo",
            type: "get",
            async: true,
            success: function (result) {
                if (result.success) {
                    $("#user").html(result.data.userName);
                }
            }
        })
    })
    /*var url='/douban/movie/top250';*/
    $('#top250Movie').bootstrapTable({
        url: '/douban/movie/top250',
        queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
        queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
            return {
                pageSize: params.limit, // 每页要显示的数据条数
                offset: params.offset, // 每页显示数据的开始行号
                sort: params.sort, // 要排序的字段
                sortOrder: params.order, // 排序规则
                movieName: $("#movieName").val(), // 额外添加的参数
                movieType: $("#movieType").val(),
            }
        },
        method: "post",
        pagination: true,
        pageNumber: 1,
        pageSize: 17,
        pageList: [15, 30, 50, 100],
        sidePagination: "client",         //分页方式：client客户端分页，server服务端分页（*）
        striped: true,                    //是否显示行间隔色
        cache: false,
        uniqueId: "ranking",               //每一行的唯一标识，一般为主键列
        height: $(window).height() - 200,
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        columns: [{
            field: 'ranking',
            title: '排名',
            align: 'center',
            width: '10%'
        }, {
            field: 'movieName',
            title: '电影名称',
            align: 'center',
            width: '30%'

        }, {
            field: 'score',
            title: '评分',
            align: 'center',
            width: '10%'
        }, {
            field: 'scoreNum',
            title: '评价人数',
            align: 'center',
            width: '10%'
        }, {
            title: "操作",
            align: 'center',
            width: '40%',
            formatter: function (value, row) {
                return '<a onclick="collect(\''+row.movieName+'\')">收藏 </a><a href=""  data-toggle="modal" data-target="#summaryModal"  onclick="getSummary(\'' + row.movieName + '\')">简介</a> <a href="" data-toggle="modal" data-target="#myModal" onclick="getComments(\'' + row.commentsUrl + '\')">热门评论</a> <a href="" data-toggle="modal" data-target="#scoreModal" onclick="getScore(\'' + row.movieName + '\')">评分分布</a>';
    }
        }],
        onLoadSuccess: function () {  //加载成功时执行
            console.info("加载成功");
        },
        onLoadError: function () {  //加载失败时执行
            console.info("加载数据失败");
        }
    });

    //查询
    function search() {
        $('#top250Movie').bootstrapTable('refresh', {pageNumber: 1});
        $('#nowPlayingMovie').bootstrapTable('refresh', {pageNumber: 1});
        $('#commingMovie').bootstrapTable('refresh', {pageNumber: 1});
        $('#collection').bootstrapTable('refresh', {pageNumber: 1});
        $('#movieName').val("");
        $('#movieType').val("");
        $('#c_name').val("");
        $('#c_label').val("");

    }


    //设置查询参数
    function getComments(commentsUrl) {
        //生成词云用到
        $("#movieCommentUrl").val(commentsUrl);
        //热门评论
        $('#hotComment').bootstrapTable({
            url: "/douban/movie/hotcomment",
            queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                    commentsUrl: commentsUrl, // 额外添加的参数
                }
            },
            method: "get",
            pagination: true,
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            sidePagination: "client",         //分页方式：client客户端分页，server服务端分页（*）
            striped: true,                    //是否显示行间隔色
            cache: false,
            uniqueId: "name",               //每一行的唯一标识，一般为主键列
            height: 600,
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            columns: [{
                field: 'name',
                title: '评论人',
                align: 'center',
                width: '10%'
            }, /*{
                field:'date',
                title:'评论日期',
                align:'center'
            },*/{
                field: 'star',
                title: '点赞数',
                align: 'center',
                width: '10%'
            }, {
                field: 'comment',
                title: '评论',
                align: 'center',
                width: '80%'
            }],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });
    }

    //监听模态框关闭 清除数据
    $("#myModal").on("hidden.bs.modal", function () {
        $("#hotComment").bootstrapTable('destroy');
    });
    //词云
    $("#imageModal").on("hidden.bs.modal", function () {
        $("#imageId").bootstrapTable('destroy');
    });
    //电影简介
    $("#summaryModal").on("hidden.bs.modal", function () {
        $("#info").bootstrapTable('destroy');
        $("#shortcontent").bootstrapTable('destroy');
    });
    //我的收藏
    $("#myCollectModal").on("hidden.bs.modal", function () {
        $("#collection").bootstrapTable('destroy');
    });
    $("#collectModal").on("hidden.bs.modal", function () {

    });
    //设置显示词云的num
    function setMovieCommentUrl() {
        var val = $("#movieCommentUrl").val();
        var movienum = val.split('/')[4];
        var imagepath = '/resource/page/ciyun/' + movienum + '.jpg';
        $("#ciyunImage").attr('src', imagepath)
    }

    //获得用户的个人信息
    function getInfo() {
        $.ajax({
            url: "/douban/userInfo",
            type: "get",
            async: true,
            success: function (result) {
                if (result.success) {
                    $("#account").val(result.data.account);
                    $("#userName").val(result.data.userName);
                    $("#email").val(result.data.email);
                    var date = new Date(result.data.createDate)
                    $("#createDate").val(date);
                }
            }
        })
    }

    //获得电影简介
    function getSummary(movieName) {
        $.ajax({
            url: '/douban/movie/summary?movieName=' + movieName,
            type: 'GET',
            async: true,
            success: function (data) {
                $("#director").html(data.director);
                $("#screenwriter").html(data.screenwriter);
                $("#actor").html(data.actor);
                $("#type").html(data.type);
                $("#releasedDate").html(data.releasedDate);
                $("#length").html(data.length);
                $("#content").html(data.summary);
                $("#name").html(data.name);
                var imagepath = '/resource/page/movieimage/' + data.imagePath
                $("#movieimage").attr('src', imagepath);
            }
        })
    }

    //获得星率分布图
    function getScore(movieName,url) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '豆瓣评分好评率',
            },
            tooltip: {},
            legend: {
                data: ['好评率']
            },
            xAxis: {
                data: ["一星", "二星", "三星", "四星", "五星"]
            },
            yAxis: {},
            series: [{
                name: '好评率',
                type: 'bar',
                data: []
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        /*myChart.setOption(option);*/
        var names = [];
        var val = [];
        $.ajax({
            url: '/douban/movie/scorechart?movieName=' + movieName,
            type: 'get',
            async: true,
            dataType: "json",
            success: function (data) {
                var star1 = data.star1.split('%')[0];
                var star2 = data.star2.split('%')[0];
                var star3 = data.star3.split('%')[0];
                var star4 = data.star4.split('%')[0];
                var star5 = data.star5.split('%')[0];

                $("#better_1").html(data.better1);
                $("#better_2").html(data.better2);
                myChart.setOption({
                    title: {
                        text: '豆瓣评分好评率',
                    },
                    tooltip: {},
                    legend: {
                        data: ['好评率']
                    },
                    xAxis: {
                        data: ["一星", "二星", "三星", "四星", "五星"]
                    },
                    yAxis: {},
                    series: [{
                        name: '好评率',
                        type: 'bar',
                        data: [star1, star2, star3, star4, star5]
                    }]
                });
            }
        })
    }

    //获取正在上映的电影
    function getNowPlayingMovie() {
        $('#top250Movie').bootstrapTable('destroy');
        $('#commingMovie').bootstrapTable('destroy');

        $('#nowPlayingMovie').bootstrapTable({
            url: '/douban/movie/nowPlayingMovies',
            queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                    movieName: $("#movieName").val(), // 额外添加的参数
                    movieType: $("#movieType").val(),
                }
            },
            method: "post",
            pagination: true,
            pageNumber: 1,
            pageSize: 17,
            pageList: [10, 20, 50, 100],
            sidePagination: "client",         //分页方式：client客户端分页，server服务端分页（*）
            striped: true,                    //是否显示行间隔色
            cache: false,
            uniqueId: "movieName",               //每一行的唯一标识，一般为主键列
            height: $(window).height() - 200,
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            columns: [{
                field: 'movieName',
                title: '电影名称',
                align: 'center',
                width: '30%'
            },{
                field: 'score',
                title: '评分',
                align: 'center',
                width: '10%'
            }, {
                field: 'scoreNum',
                title: '评价人数',
                align: 'center',
                width: '10%'
            },{
                title: "操作",
                align: 'center',
                width: '40%',
                formatter: function (value, row) {
                    return '<a onclick="collect(\''+row.movieName+'\')">收藏 </a><a href=""  data-toggle="modal" data-target="#summaryModal" onclick="getSummary(\'' + row.movieName + '\')">简介</a> <a href="" data-toggle="modal" data-target="#myModal" onclick="getComments(\'' + row.commentsUrl + '\')">热门评论</a> <a href="" data-toggle="modal" data-target="#scoreModal" onclick="getScore(\'' + row.movieName + '\')">评分分布</a>';
                }
            }],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });
    }
    //将要上映电影
    function getCommingPlayingMovie() {
        $('#top250Movie').bootstrapTable('destroy');
        $('#nowPlayingMovie').bootstrapTable('destroy');

        $('#commingMovie').bootstrapTable({
            url: '/douban/movie/commingMovies',
            queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                    movieName: $("#movieName").val(), // 额外添加的参数
                    movieType: $("#movieType").val(),
                }
            },
            method: "post",
            pagination: true,
            pageNumber: 1,
            pageSize: 17,
            pageList: [10, 20, 50, 100],
            sidePagination: "client",         //分页方式：client客户端分页，server服务端分页（*）
            striped: true,                    //是否显示行间隔色
            cache: false,
            uniqueId: "movieName",               //每一行的唯一标识，一般为主键列
            height: $(window).height() - 200,
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            columns: [{
                field: 'movieName',
                title: '电影名称',
                align: 'center',
                width: '30%'
            },{
                field: 'movieType',
                title: '电影类型',
                align: 'center',
                width: '10%'
            }, {
                field: 'area',
                title: '地区',
                align: 'center',
                width: '10%'
            }, {
                field: 'wantWatchNum',
                title: '想看',
                align: 'center',
                width: '10%'
            },{
                title: "操作",
                align: 'center',
                width: '40%',
                formatter: function (value, row) {
                    return '<a onclick="collect(\''+row.movieName+'\')">收藏 </a><a href=""  data-toggle="modal" data-target="#summaryModal" onclick="getSummary(\'' + row.movieName + '\')">简介</a>';
                }
            }],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });

    }
    //添加收藏
    function collect(movieName){
        $.ajax({
            url:'/douban/movie/collect?movieName='+movieName,
            type:'get',
            async:true,
            success:function(result){
                if(result.success){
                    layer.msg("收藏成功")
                }
                else{
                    layer.msg("该影片已收藏!!!")
                }
            }
        })
    }
    //我的收藏
    function getCollection(){
        $('#collection').bootstrapTable({
            url: "/douban/collection",
            queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
            queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                return {
                    pageSize: params.limit, // 每页要显示的数据条数
                    offset: params.offset, // 每页显示数据的开始行号
                    sort: params.sort, // 要排序的字段
                    sortOrder: params.order, // 排序规则
                    name: $("#c_name").val(),
                    label:$("#c_label").val(),// 额外添加的参数
                }
            },
            method: "post",
            pagination: true,
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            sidePagination: "client",         //分页方式：client客户端分页，server服务端分页（*）
            striped: true,                    //是否显示行间隔色
            cache: false,
            uniqueId: "name",               //每一行的唯一标识，一般为主键列
            height: 600,
            paginationPreText: "上一页",
            paginationNextText: "下一页",
            columns: [{
                field: 'name',
                title: '电影名称',
                align: 'center',
                width: '30%'
            },{
                field: 'label',
                title: '标签',
                align: 'center',
                width: '30%'
            },{
                title: "操作",
                align: 'center',
                width: '40%',
                formatter: function (value, row) {
                    return '<a data-toggle="modal" data-target="#collectModal" onclick="showLabel(\''+row.name+'\')">编辑标签 </a> <a style="color: red" onclick="deleteCollection(\''+row.name+'\')">删除</a>';
                }}],
            onLoadSuccess: function () {  //加载成功时执行
                console.info("加载成功");
            },
            onLoadError: function () {  //加载失败时执行
                console.info("加载数据失败");
            }
        });
    }
    //删除收藏
    function deleteCollection(name) {
        $.ajax({
            url:'/douban/collection/delete?name='+name,
            type:'post',
            async:true,
            success:function(result){
                layer.msg("删除成功")
                search()
            }
        })
    }
    //设置电影名称
    function showLabel(name){
        $("#collect_movieName").val(name);
        $.ajax({
            url:'/douban/collection/'+name,
            type:'get',
            async:true,
            success:function(result){
                $("#label_text").val(result.label);
            }
        })
    }
    //更新电影标签
    function updateLabel(){
        $.ajax({
            url:'/douban/collection/update?name='+$("#collect_movieName").val()+'&label='+$("#label_text").val(),
            type:'post',
            async:true,
            success:function (result) {
                search();
            }
        })
    }
</script>
</body>
</html>

