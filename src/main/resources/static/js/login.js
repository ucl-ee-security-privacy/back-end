// Login Form
$(function () {
    var button = $('#loginButton');
    loginBox
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
document.getElementById('loginBtn').onclick = function () {
// function loginBtn(){
// loginBtn.addEventListener("click", function() {
//     window.location.href = "index.html";
    var input_username = document.getElementById("modlgn_username").value;
    var input_password = document.getElementById("modlgn_password").value;

    // ajax提交用户名+密码到后台程序
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/login?username=" + input_username + "&password=" + input_password);
    xhr.setRequestHeader("Content-type", "url");
    xhr.send("login?" + "username=\"" + input_username + "\"&password=" + input_password);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var res = xhr.responseText;
            // 后台响应成功则跳转到登录页面

            if (JSON.parse(res).status === 1) {
                // alert("注册成功");
                $.removeCookie('userid', {path: '/'})
                $.cookie('userid', res.userid, {expires: 7, path: '/'});
                window.location.href = "index.html";
            } else {
                alert(res);

            }
        }
    }
}







