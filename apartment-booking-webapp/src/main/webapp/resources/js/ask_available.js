function ask_available(apartment_id,check_in_id,check_out_id, yes_div_id, no_div_id) {
    var check_in_str = document.getElementById(check_in_id).value;
    var check_out_str = document.getElementById(check_out_id).value;
    var request = new XMLHttpRequest();
    var params = "";
    params += "check_in="+check_in_str+"&";
    params += "check_out="+check_out_str+"&";
    var paramId = encodeURIComponent(apartment_id);
    var new_url = url+'/'+paramId;
    request.open("POST", new_url);
    request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 201) {
            var type = request.getResponseHeader("Content-Type");
            if (type === "application/json;charset=UTF-8")
                draw_time_available(JSON.parse(request.responseText), yes_div_id, no_div_id);
        }
    };
    request.send(params);
}
