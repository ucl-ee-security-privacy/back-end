var totalnum = 0;

window.onload = function (ev) {
    // the function will be called at first while page loads
    initNum();
};

initNum = function () {
    var total = document.getElementById('total_num');
    var html_text = ''
    // get commentlist
    $.ajax({
        url: "comment/comment_list",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            html_text = '<span>' + data + '<span/>'
            total.innerHTML = html_text;
        }
    });
};

showNum = function (num) {
    var total = document.getElementById('total_num');
    var html_text = ''
    html_text = '<span>' + num + '<span/>'
    total.innerHTML = html_text;
}


document.getElementById('change').onclick = function () {
// function loginBtn(){
// loginBtn.addEventListener("click", function() {
    window.location.href = "admin_modify.html";
    var num = document.getElementById("change_num").valueAsNumber;


    // ajax提交用户名+密码到后台程序
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "registerRecv.php");
    xhr.setRequestHeader("Content-type", "url");
    xhr.send("admin_modify?productid=0&changed_num=" + num);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var res = xhr.responseText;
            showNum(JSON.parse(res))
        }
    }
}