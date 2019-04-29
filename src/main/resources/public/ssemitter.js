if('serviceWorker' in navigator) {
  navigator.serviceWorker
           .register('/serviceWorker.js')
           .then(function(){
                return navigator.serviceWorker.ready;
           })
           .then(function(registration) {
                console.log(registration);
                console.log("Service Worker Registrado no projeto com sucesso!!");
           });
} else {
    console.log("Sem service worker.");
}

if(typeof(EventSource) !== "undefined") {
    var source = new EventSource("/sseTest");
    source.onmessage = function(event) {
        var obj = JSON.parse(event.data);
        var idTag = obj.idTag;
        var valorTag = obj.valorTag;
        document.getElementById(idTag).innerHTML = valorTag;
    };
} else {
    document.getElementById("sseDiv").innerHTML =
                     "Your browser does not support server-sent events.";
}