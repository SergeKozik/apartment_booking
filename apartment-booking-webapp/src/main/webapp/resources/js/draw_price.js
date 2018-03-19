function draw_price(money_value,price_div_id) {
    var priceDiv=document.getElementById(price_div_id);
    var html_content = money_value[0]+" "+money_value[1];
    priceDiv.innerHTML=html_content;
}
