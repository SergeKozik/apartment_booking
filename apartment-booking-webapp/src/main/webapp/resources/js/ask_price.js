function ask_price(url,apartment_id,guests_num,check_in_str,check_out_str,price_div_id) {
    var request = new XMLHttpRequest();
    var params = "";
    params += "guests="+guests_num+"&";
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
                draw_price(JSON.parse(request.responseText),price_div_id);
        }
    };
    request.send(params);
}
