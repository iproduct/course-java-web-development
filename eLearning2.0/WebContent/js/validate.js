function validateEmail() {
	var email = document.getElementById("email").value;
	var errorsElement = document.getElementById("emailErrors");
	errorsElement.style.display = "none";
	loadJSONObject("GET", "http://localhost:8080/eLearning2.0/AjaxValidatorServlet", {"email": email}, showEmailValidationResults);
}

function showEmailValidationResults(data){
	if(data == "valid") return;
	var errorsElement = document.getElementById("emailErrors");
	errorsElement.style.display = "block";
	errorsElement.innerHTML = data;
}

function loadJSONObject(method, url, params, successCallback, errorCallback, contentType)
{
    contentType = contentType || "application/x-www-form-urlencoded";
    params = params || {};
    errorCallback = errorCallback || function(){};
    var xmlhttp;
    var paramStr = "", p;
    var i = 0;
    var delimiter = "&";
    for(p in params){
        paramStr += ((i>0)?delimiter:"") + encodeURIComponent(p) + "="+encodeURIComponent(params[p]);
        i++;
    }
    url = url + "?t=" + Math.random();
    if(method !== "POST" && method !== "PUT" && paramStr.length > 0){
        url += "&" + paramStr;
    }

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
    xmlhttp.onreadystatechange = function(){
        if (xmlhttp.readyState==4){
            if(xmlhttp.status >= 200 && xmlhttp.status < 300){
            	var data = xmlhttp.responseText;
                successCallback(data);
            } else {
                errorCallback(xmlhttp);
            }
        }
    }
    xmlhttp.open(method, url ,true);
    xmlhttp.setRequestHeader("Pragma", "no-cache")
    xmlhttp.setRequestHeader("Cache-Control", "no-cache")
    if((method === "POST" || method === "PUT") && paramStr.length > 0){
        xmlhttp.setRequestHeader("Content-type",contentType);
        xmlhttp.setRequestHeader("Accept","application/json");
       xmlhttp.send(paramStr);
    } else {
        xmlhttp.send();
    }
}
