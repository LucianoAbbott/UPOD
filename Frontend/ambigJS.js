function addLink(title, content){
  var linkCon = document.createElement("div");
  var link = document.createElement("a");
  var linkTitle = document.createElement("div");
  var text = document.createElement("p");

  linkCon.className ="linkContainer";
  link.href = "google.ca";
  linkTitle.className = "linkTop";
  text.innerHTML = title;

  var preview = document.createElement("div");
  var previewT = document.createElement("p");
  preview.className = "linkPreview";
  previewT.innerHTML = content;
  linkCon.appendChild(link);
  link.appendChild(linkTitle);
  linkTitle.appendChild(text);
  linkCon.appendChild(preview);
  preview.appendChild(previewT)

  document.getElementsByTagName("body")[0].appendChild(linkCon);
}

function addLinks(numLinks){
  var xmlh = new XMLHttpRequest();
  xmlh.onreadystatechange = function() {
    if (xmlh.readyState == 4) {
        var raw = xmlh.responseText
        var data = JSON.parse(raw);
        console.log(raw);
        for(var i = 0; i < numLinks; i++){
          addLink(data.sections[0].title, data.sections[0].text);
        }
    }
  }

  xmlh.open('GET', "http://174.117.188.203:8080/page/get/1", true);
  xmlh.send(null);  

 
}
