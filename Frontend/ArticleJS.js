

function makeArticleSection(sectionJSON, i){
    var container = document.createElement("div");
    var header = document.createElement("h2");
    var content = document.createElement("p");
    var imageWrap = document.createElement("p");
    var image = document.createElement("img");
    container.classname="sectionEdit";
    header.classname="sectionHeader";
    image.style="margin-left: 40px";
    header.innerHTML=sectionJSON.sections[i].title;
    content.innerHTML=sectionJSON.sections[i].text;
    image.href=sectionJSON.sections[i].graphic;
    container.appendChild(header);
    container.appendChild(content);
    imageWrap.appendChild(image);
    container.append(imageWrap);
}

function genArticleSection(){
  var numSections=Object.keys.(sessionStorage.sectionJSON).length;
  for(var i=0;i<numSection;i++){
    makeArticleSection(sessionStorage.sectionJSON,i);
  }
}
