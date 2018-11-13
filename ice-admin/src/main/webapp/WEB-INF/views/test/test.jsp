<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>活动规则</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/static/js/style.css">
    <script type="text/javascript" src="/static/js/style.js"></script>
</head>
<body>
<div id="slideBar">
    <div class='slide-box'>
        <div class='slide-img-block'>
            <div class='slide-loading'></div>
            <div class='slide-img-border'>
                <div class='scroll-background slide-top'></div>
                <div class='slide-img-div'>
                    <div class='slide-img-nopadding'>
                        <img class='slide-img' id='slideImg' src=''/>
                        <div class='slide-block' id='slideBlock'></div>
                        <div class='slide-box-shadow' id='cutBlock'></div>
                    </div>
                    <div class='scroll-background  slide-img-hint-info' id='slideHintInfo'>
                        <div class='slide-img-hint'>
                            <div class='scroll-background slide-icon' id='slideIcon'></div>
                            <div class='slide-text'><span class='slide-text-type' id='slideType'></span>
                                <span class='slide-text-content' id='slideContent'></span></div>
                        </div>
                    </div>
                </div>
                <div class='scroll-background slide-bottom'>
                    <div class='scroll-background slide-bottom-refresh' id='refreshBtn' title='更换图片'></div>
                    <div class='slide-bottom-no-logo'></div>
                </div>
            </div>
        </div>
        <div class='scroll-background scroll-bar'>
            <div class='scroll-background slide-btn' id='slideBtn'></div>
            <div class='slide-title' id='slideHint'> <-按住滑块，拖动完成上面拼图</div>
        </div>
    </div>

</div>
<script type="text/javascript">
    var dataList = ["0", "1"];
    var options = {
        dataList: dataList,
        success: function () {
            console.log("show");
        },
        fail: function () {
            console.log("fail");
        }
    };
    SliderBar("slideBar", options);
</script>


<%--<div id="wrapper-l">
    <div class="rule-txt">
        <div class='register-content' style="box-shadow:0px 0px 10px 10px #ccc; border-radius:2px;">
            <img src="data:image/png;base64,${stringStringMap.slider}" alt="">
            <img src="data:image/png;base64,${stringStringMap.background}" alt="">
        </div>
    </div>

</div>--%>
</body>

</html>