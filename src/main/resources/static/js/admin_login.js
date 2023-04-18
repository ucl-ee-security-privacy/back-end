// Login Form
$(function () {
    var button = $('#loginButton');
    var box = $('#loginBox');
    var form = $('#loginForm');
    button.removeAttr('href');
    button.mouseup(function (login) {
        box.toggle();
        button.toggleClass('active');
    });
    form.mouseup(function () {
        return false;
    });
    $(this).mouseup(function (login) {
        if (!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
        }
    });
});


// const loginBtn = document.getElementById("loginBtn");
// document.getElementById('loginBtn').onclick = function () {
// // function loginBtn(){
// // loginBtn.addEventListener("click", function() {
//     window.location.href = "index.html";
//     var input_username = document.getElementById("modlgn_username").value;
//     var input_password = document.getElementById("modlgn_password").value;
//
//     // ajax提交用户名+密码到后台程序
//     var xhr = new XMLHttpRequest();
//     xhr.open("GET", "http://localhost:8080/login?username=" + input_username + "&password=" + input_password);
//     xhr.setRequestHeader("Content-type", "url");
//     xhr.send("login?" + "username=" + input_username + "&password=" + input_password);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//             var res = xhr.responseText;
//             // 后台响应成功则跳转到登录页
//
//             var res_json=JSON.parse(res)
//
//             if (res_json["status"] === 1) {
//                 // alert("注册成功");
//                 $.removeCookie('userid', {path: '/'})
//                 $.cookie('userid', res.userid, {expires: 7, path: '/'});
//                 window.location.href = "index.html";
//             } else {
//                 // print(res.type)
//                 // print(res['status'])
//                 // alert(typeof(res_str) +"  "+typeof (res_json)+"  "+typeof (res)+"@"+res+"@"+res_json["status"]+"@"+res_json2['status']+"@"+input_username+input_password);
//                 alert("wrong user name or password");
//             }
//         }
//     }
// }


// 监听表单提交事件
// function submitform() {
//     // window.location.href = "index.html";
//     // alert("click")
//     // 获取表单数据
//     var input_username = document.getElementById('modlgn_username').value;
//     var input_password = document.getElementById('modlgn_password').value;
//     // alert(input_username+input_password)
//     var domain = window.location.host;
//     // alert(domain+" "+date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds())
//     // 发送表单数据到服务器
//     // 这里使用了假设的服务器URL和假设的POST请求
//     var xhr = new XMLHttpRequest();
//     xhr.open("GET", "http://"+domain+"/login?username=" + input_username + "&password=" + input_password);
//     xhr.setRequestHeader("Content-type", "");
//     xhr.send("login?" + "username=" + input_username + "&password=" + input_password);
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4 && xhr.status === 200) {
//             var res = xhr.responseText;
//             // 后台响应成功则跳转到登录页
//             var res_json=JSON.parse(res)
//             if (res_json["status"] === 1) {
//                 // alert(res)
//                 // // alert("注册成功");
//                 // $.removeCookie('userid', {path: '/'})
//                 // $.cookie('userid', res.userid, {expires: 7, path: '/'});
//                 window.location.href = "index.html";
//             } else {
//                 // print(res.type)
//                 // print(res['status'])
//                 // alert(typeof(res_str) +"  "+typeof (res_json)+"  "+typeof (res)+"@"+res+"@"+res_json["status"]+"@"+res_json2['status']+"@"+input_username+input_password);
//                 alert("wrong user name or password");
//             }
//         }
//     }
//     return false;
// }

$("#submit_button").click(function () {
    var domain = window.location.host;
    // alert("yes")
    document.getElementById("login-form").action = "http://"+domain+"/login"
    $("#myiframe").one("load", function () {
        var text = $(this).contents().find("body").text(); //获取到的是json的字符串
        // var j = $.parseJSON(text);  //json字符串转换成json对象

        var res_json=JSON.parse(text)
        // alert(res_json["status"])
        if (res_json["status"] === 1) {
            // alert(res)
            // // alert("注册成功");
            // $.removeCookie('userid', {path: '/'})

            $.cookie('username', res_json["username"], {expires: 7});
            $.cookie('birthday', res_json["birthday"], {expires: 7});
            $.cookie('userid', res_json["userid"], {expires: 7});
            $.cookie('phone', res_json["phone"], {expires: 7});
            $.cookie('email', res_json["email"], {expires: 7});
            $.cookie('nickname', res_json["nickname"], {expires: 7});
            window.location.href = "admin_modify.html";

        } else {
            // print(res.type)
            // print(res['status'])
            // alert(typeof(res_str) +"  "+typeof (res_json)+"  "+typeof (res)+"@"+res+"@"+res_json["status"]+"@"+res_json2['status']+"@"+input_username+input_password);
            alert("wrong user name or password");
        }
    })
});






