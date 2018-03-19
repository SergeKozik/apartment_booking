function decrease_guests(guest_id) {
    var element = document.getElementById(guest_id);
    var old = parseInt(element.value,10);
    if (old>0) {
        element.value = old-1;
    }
}
