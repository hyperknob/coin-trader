function commitApiKey() {
    var keyName = $("#keyName").val() || "";
    var key = $("#key").val() || "";
    var secret = $("#secret").val() || "";
    var data = {
        keyName: keyName,
        key: key,
        secret: secret
    };
    $.ajax({
        timeout : 20000,
        type : "POST",
        dataType : "JSON",
        url : "/ajax/addApiKey",
        data : data,
        cache: false,
        success : function(data){
            if(data && data.code === 200) {
                window.location.href = '/market';
            } else {
                alert(data.message);
            }
        }
    });
}