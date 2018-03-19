function draw_time_available(flag, yes_div_id, no_div_id) {
    var yes_div=document.getElementById(yes_div_id);
    var no_div=document.getElementById(no_div_id);
    if (flag==='true') {
        yes_div.style.display='inline-block';
        no_div.style.display='none';
    } else {
        yes_div.style.display='none';
        no_div.style.display='inline-block';
    }
}
