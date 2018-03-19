function increase_guests(guest_id,max_id) {
    var maxValue = parseInt(document.getElementById(max_id).value,10);
    var element = document.getElementById(guest_id);
    var old = parseInt(element.value,10);
    if (old<maxValue){
        element.value = old+1;
    }
}
