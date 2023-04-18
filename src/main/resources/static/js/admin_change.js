var totalnum = 0;

window.onload = function (ev) {
    // the function will be called at first while page loads
    initNum();
};

initNum = function () {
    // var total = document.getElementById('total_num');
    // var html_text = ''
    // // get commentlist
    // $.ajax({
    //     url: "comment/comment_list",
    //     type: 'GET',
    //     dataType: 'json',
    //     success: function (data) {
    //         html_text = '<span>' + data + '<span/>'
    //         total.innerHTML = html_text;
    //     }
    // });
    showNum()
};

showNum = function () {
    var total = document.getElementById('total_num');
    var html_text = ''
    $.ajax({
        url: "admin_modify/0",
        type: 'GET',
        success: function (data) {
            var num = data["productnum"]
            html_text = '<span>' + "Remain:  "+num + '<span/>'
            // alert(html_text);
            total.innerHTML = html_text;
        },
        error: function (){
            alert("error")
        }
    });


}


document.getElementById('change').onclick = function () {
// function loginBtn(){
// loginBtn.addEventListener("click", function() {
//     window.location.href = "admin_modify.html";
    var num = document.getElementById("change_num").valueAsNumber;

    // ajax提交用户名+密码到后台程序
    var xhr = new XMLHttpRequest();
    xhr.open("get", "admin_modify/0/"+num);
    xhr.setRequestHeader("Content-type", "url");
    xhr.send("admin_modify/0/"+num);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var res = xhr.responseText;
            var res_json = JSON.parse(res)
            if (res_json["status"] === 1){
                alert("Change success");
                showNum()
            }
            else{
                alert("change failed")
            }
        }
    }
}