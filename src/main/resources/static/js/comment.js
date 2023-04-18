var commentSpace = document.getElementById('comment_space');
var textareaLen = commentSpace.value.length;
var htmlText = '';
var commentList = [];
var commentTree = {
    commentid: '',
    userid: '',
    posttime: '',
    content: '',
};

window.onload = function (ev) {
    // the function will be called at first while page loads
    initTreeComment();
};

getCurrentTime = function () {
    var date = new Date();
    var year = date.getFullYear()
    var month = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1)
    var day = date.getDate()
    var hour = date.getHours()
    var min = date.getMinutes()
    var sec = date.getSeconds()

    return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec
};

initTreeComment = function () {
    var commentView = document.getElementById('comment_view');
    // get commentlist
    $.ajax({
        url: "comment/comment_list",
        type: 'GET',
        success: function (data) {
            if (data.length === 0) {
                // alert("no");
                noComment(commentView)
            } else {
                // var d = toString(data)
                // alert("yes"+typeof(d)+" "+d);
                // var data_json = JSON.parse(data)
                // alert(data[0]["id"])
                haveComment(commentView, data)
            }
        },
        error: function (){
            alert("error")
        }

    });
};

noComment = function (commentView) {
    commentView.style.textAlign = 'center';
    commentView.innerText = 'Please login first'
};

haveComment = function (commentView, arr) {

    commentView.style.textAlign = 'left';

    var htmlText = '';
    arr.forEach(function (value) {
        // alert(value["commentid"])
        htmlText +=
            '<ul id="' + value["commentid"] + '" class="comment_ulist" style="">' +
            '  <li class="comment_line_box" id="' + value["commentid"] + '">' +
            '    <div class="right_box">' +
            '      <a class="commentator">' + value["userid"] + '</a>';

        htmlText += '<span class="time">' + value["posttime"] + '</span>';

        // if (value["content"].length > 10) {
        htmlText += ' <span style="display: block; margin-top: 8px" class="comment">' + value["content"] + '</span>';
        // } else {
        //     htmlText += ' <span class="comment">' + value["content"].substring(0, 10) + '</span>';
        // }
        htmlText +=
            '    </div>'+
            '  </li>' +
            '</ul>'
    });
    commentView.innerHTML = htmlText;
};


// submitTree = function (textareaEle) {
//     var commentView = document.getElementById('comment_view');
//     var text = textareaEle.value;
//     if (text.length > 0) {
//         commentTree = {
//             posttime: getCurrentTime(),
//             content: text,
//         };
//         alert(commentTree)
//         var xhr = new XMLHttpRequest();
//         xhr.open("POST", "comment/submit");
//         xhr.setRequestHeader("Content-type", "url");
//         xhr.send(commentTree);
//         xhr.onreadystatechange = function () {
//             if (xhr.readyState === 4 && xhr.status === 200) {
//                 var res = xhr.responseText;
//                 var res_json=JSON.parse(res)
//                 haveComment(commentView, res_json)
//             }
//         }
//     }
// };

// document.getElementById('submit_comment').onclick = function (textarea) {
//     // window.location.href = "index.html";
//     var commentSpace = document.getElementById('comment_space');
//     // alert(commentSpace.value);
//     submitTree(commentSpace);
// };


$("#submit_comment").click(function () {
    var domain = window.location.host;
    document.getElementById("comment_form").action = "comment/submit"
    var commentView = document.getElementById('comment_view');
    $("#myiframe").one("load", function () {
        var commentSpace = document.getElementById('comment_space');
        // alert(commentSpace.value);
        // submitTree(commentSpace);
        var text = $(this).contents().find("body").text();
        if(text === "1"){
            $.ajax({
                url: "comment/comment_list",
                type: 'GET',
                success: function (data) {
                    if (data.length === 0) {
                        // alert("no");
                        noComment(commentView)
                    } else {
                        // var d = toString(data)
                        // alert("yes"+typeof(d)+" "+d);
                        // var data_json = JSON.parse(data)
                        // alert(data[0]["id"])
                        // alert("goin")
                        haveComment(commentView, data)
                    }
                },
                error: function (){
                    alert("error")
                }
            });
        }
    })
});