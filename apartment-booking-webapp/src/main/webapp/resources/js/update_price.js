function update_price(url, apartment_id, guests_div_id, check_in_div_id, check_out_div_id, price_div_id) {
    var guests_num = parseInt(document.getElementById(guests_div_id).value,10);
    var check_in_str = document.getElementById(check_in_div_id).value;
    var check_out_str = document.getElementById(check_out_div_id).value;
    ask_price(url,apartment_id,guests_num,check_in_str,check_out_str,price_div_id);
}
