function refreshPositions() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("agvs");

    request.onload = function() {
        vBoard.innerHTML = this.responseText;
        vBoard.style.color="black";
        setTimeout(refreshPositions, 2000);
    };

    request.ontimeout = function() {
        vBoard.innerHTML = "Server timeout, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPositions, 100);
    };

    request.onerror = function() {
        vBoard.innerHTML = "No server reply, still trying ...";
        vBoard.style.color="red";
        setTimeout(refreshPositions, 5000);
    };

    request.open("GET", "/agvs", true);
    request.timeout = 5000;
    request.send();
}