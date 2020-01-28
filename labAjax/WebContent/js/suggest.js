var countries=[];

function autoSuggestCountry(){
	var country = document.getElementById("country").value;
	if(country.length > 0)
		loadJSONObject("POST", "http://localhost:8088/labAjax/SuggestCountry", {"country": country}, showResults);
}

function autocompleteCountry() {
	var suggestions = document.getElementById("suggestions");
	var index = suggestions.selectedIndex;
	document.getElementById("country").value = countries[index].country;
	document.getElementById("capital").value = countries[index].capital;
	suggestions.style.display = "none";
}

function showResults(data){
//	var results = "";
	countries = data;
	var suggestions = document.getElementById("suggestions");
	if(countries.length == 0 ) {
		suggestions.style.display = "none";
	}
	suggestions.innerHTML = "";
	for(var i = 0; i < countries.length; i++){
		var op = document.createElement("option");
		op.setAttribute("value", i);
		var txt = document.createTextNode(countries[i].country);
		op.appendChild(txt);
		suggestions.appendChild(op);
//		results += "<option value='" + countries[i].country + "'>" 
//			+ countries[i].country + "</option>";
//		suggestions.innerHTML = results;
		suggestions.style.display = "block";
	}
//	alert(results);
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
            	var data = JSON.parse(xmlhttp.responseText);
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

