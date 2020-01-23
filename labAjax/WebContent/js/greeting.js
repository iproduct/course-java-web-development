function greet(){
	var serviceUrl = "HelloAjax";
	var name = document.getElementById("name").value;
	
	//Create XHR
	if (typeof XMLHttpRequest !== "undefined") {
        xmlhttp = new XMLHttpRequest();
    } else {
        try {
            xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) { }
        if (xmlhttp == null) {
            try {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) { }
        }
        if (xmlhttp == null) {
            return;
        }
    }
	
	//Attach callback function
	xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState==4){
            if(xmlhttp.status >= 200 && xmlhttp.status < 400){
            	document.getElementById("greeting").innerHTML = 
            		xmlhttp.responseText;
            } else {
                errorCallback(xmlhttp);
            }
        }
    };
	
	//Call Ajax Servlet
    xmlhttp.open("GET", serviceUrl + "?name=" + name ,true);
    xmlhttp.setRequestHeader("Pragma", "no-cache");
    xmlhttp.setRequestHeader("Cache-Control", "no-cache");
    xmlhttp.send();
	
}