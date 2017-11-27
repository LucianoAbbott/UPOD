var i = 0;

function addSection() {
    /*var original = document.getElementById('duplicater' + i);
    var clone = original.cloneNode(true); // "deep" clone
   clone.id = "duplicater" + ++i; // there can only be one element with an ID
    clone.onclick = duplicate; // event handlers are not cloned
    original.parentNode.appendChild(clone);*/
    i++;
    var secParent = document.getElementsByClassName("sectionContainer")[0];
    var table = document.getElementsByClassName("sidebarSections")[0];
    var sectionTC = document.createElement("a");
    sectionTC.href = "#section" + i;
    sectionTC.className = "sideBarLink a";
    sectionTC.innerHTML = "new \<br>";
    sectionTC.width = "100%";
    table.insertBefore(sectionTC, table.childNodes[table.childNodes.length - 2]);


    var newSection = document.createElement("div");
    newSection.className = "sectionForm";

    newSection.id = "section" + i;
    /*newSection.innerHTML = "WE MADE IT";*/

    var rowA = document.createElement("div");
    rowA.className = "rowAlignLeft";

    var form = document.createElement("form");
    var upButton = document.createElement("button");
    var downButton = document.createElement("button");
    form.className = "sideBySide";
    upButton.innerHTML = "&#65514";
    downButton.innerHTML = "&#65516";
    upButton.className = "solidButton";
    downButton.className = "solidButton";
    upButton.id = "upButton"+i;
    downButton.id = "downButton"+i;
    var input = i;
    upButton.addEventListener("click",function(){moveUpSection(input);})
    downButton.addEventListener("click",function(){moveDownSection(input);})
    upButton.type = "button";
    downButton.type = "button";


    form.appendChild(upButton);
    form.appendChild(downButton);
    rowA.appendChild(form);

    /*titleform.appendChild(addButton);
    titleform.appendChild(removeButton);
    rowA.appendChild(titleform);*/
    newSection.appendChild(rowA);
    //HERE
    var infoInput = document.createElement("div");
    var titleform = document.createElement("form");
    var sectionTitleText = document.createElement("input");
    var sectionDeleteButton = document.createElement("button");
    sectionTitleText.type = "text";
    sectionTitleText.name = "sectionTitle";
    sectionTitleText.id = "sectionTitle" + i;
    sectionTitleText.className = "articleTitleBox";
    sectionTitleText.placeholder = "Section Title";
    sectionDeleteButton.className = "ghostButtonRed";
    sectionDeleteButton.innerHTML = "Delete Section";
    sectionDeleteButton.onclick = "getData();"

    sectionDeleteButton.type = "button";
    sectionDeleteButton.onclick = function() {
        table.removeChild(sectionTC);
        secParent.removeChild(newSection);
        console.log(i);
    };

    titleform.appendChild(sectionTitleText);
    titleform.appendChild(sectionDeleteButton);
    infoInput.appendChild(titleform);
    //newSection.appendChild(infoInput);

    sectionTitleText.addEventListener("keyup", function(event) {
        sectionTC.innerHTML = sectionTitleText.value + "\<br>";
    });


    var sectionWrapDiv = document.createElement("div");
    var sectionSideDiv = document.createElement("div");
    var sectionAreaText = document.createElement("textarea");
    var sectionLatexText = document.createElement("textarea");
    var diaForm = document.createElement("form");
    var diagramUrl = document.createElement("input");
    sectionWrapDiv.className = "sectionWrap";
    sectionSideDiv.className = "sectionSide";

    sectionWrapDiv.width = "100%";

    sectionLatexText.rows = "5";
    sectionLatexText.cols = "20";
    sectionAreaText.className = "tArea";

    sectionLatexText.rows = "5";
    sectionLatexText.cols = "20";
    sectionLatexText.className = "tArea";

    sectionAreaText.placeholder = "Content";
    sectionLatexText.placeholder = "Latex";
    diagramUrl.className = "articleTitleBox";
    diagramUrl.type = "text";
    diagramUrl.placeholder = "Diagram URL";
    diagramUrl.name = "diagram URL " + i;


    sectionSideDiv.appendChild(sectionAreaText);
    sectionSideDiv.appendChild(sectionLatexText);
    diaForm.appendChild(diagramUrl);
    sectionSideDiv.appendChild(diaForm);
    sectionWrapDiv.appendChild(sectionSideDiv);

    var sectionImage = document.createElement("div");
    var imageDiv = document.createElement("div");
    var imageForm = document.createElement("form");
    var fileIn = document.createElement("input");
    var getImageButton = document.createElement("button");
    sectionImage.className = "sectionImage";
    imageDiv.className = "image";
    imageDiv.id = "disp";
    fileIn.id = "fileInput";
    fileIn.type = "file";
    fileIn.style.display = "none";
    getImageButton.type = "file";
    getImageButton.className = "solidButton";
    getImageButton.onclick = "document.getElementById('fileInput').click();";
    getImageButton.innerHTML = "Add Image";

    sectionImage.appendChild(imageDiv);
    imageForm.appendChild(fileIn);
    imageForm.appendChild(getImageButton);
    sectionImage.appendChild(imageForm);
    //sectionWrapDiv.appendChild(infoInput);
    sectionWrapDiv.appendChild(sectionImage);
    infoInput.appendChild(sectionWrapDiv);
    newSection.appendChild(infoInput);


    secParent.appendChild(newSection);

}

function moveUpSection(sectionNum){
	var secParent = document.getElementsByClassName("sectionContainer")[0].children;
	if(sectionNum-1>0){
		var newTopSec = document.getElementById('section'+sectionNum);
		var bottom = sectionNum -1;
		var newBottomSec = document.getElementById('section'+bottom);
		newTopSec.id = "section"+bottom;
		newBottomSec.id = "section"+sectionNum;

		var clone = newTopSec.cloneNode(true);
		var clone1 = newBottomSec.cloneNode(true);
		var secParent = document.getElementsByClassName("sectionContainer")[0];
		secParent.insertBefore(clone,newBottomSec);
		secParent.insertBefore(clone1,newTopSec);

		secParent.removeChild(newTopSec);
		secParent.removeChild(newBottomSec);

		newTopSec = document.getElementById('section'+sectionNum);
		newBottomSec.id = "section"+sectionNum;

		var newBottomButton =document.getElementById("upButton"+bottom);
		var newTopButton =document.getElementById("upButton"+sectionNum);
		newBottomButton.addEventListener("click",function(){moveUpSection(sectionNum);});
		newTopButton.addEventListener("click",function(){moveUpSection(sectionNum-1);});
		newBottomButton.id = "upButton"+sectionNum;
		bottom = sectionNum -1;
		newTopButton.id = "upButton"+bottom;

		newBottomButton =document.getElementById("downButton"+bottom);
		newTopButton =document.getElementById("downButton"+sectionNum);
		newBottomButton.addEventListener("click",function(){moveDownSection(sectionNum);});
		newTopButton.addEventListener("click",function(){moveDownSection(sectionNum-1);});
		newBottomButton.id = "downButton"+sectionNum;
		bottom = sectionNum -1;
		newTopButton.id = "downButton"+bottom;

		
	}
	else{
		alert("This section is already at the top of the page.");
	}
}

function moveDownSection(sectionNum){
	var secParent = document.getElementsByClassName("sectionContainer")[0].children;
	if(sectionNum !=i){
		var bottom = sectionNum +1;
		var newTopSec = document.getElementById('section'+bottom);
		var newBottomSec = document.getElementById('section'+sectionNum);
		newTopSec.id = "section"+sectionNum;
		newBottomSec.id = "section"+bottom;

		var clone = newTopSec.cloneNode(true);
		var clone1 = newBottomSec.cloneNode(true);
		var secParent = document.getElementsByClassName("sectionContainer")[0];
		secParent.insertBefore(clone,newBottomSec);
		secParent.insertBefore(clone1,newTopSec);

		secParent.removeChild(newTopSec);
		secParent.removeChild(newBottomSec);

		newTopSec = document.getElementById('section'+sectionNum);
		newBottomSec.id = "section"+sectionNum;

		var newBottomButton =document.getElementById("upButton"+bottom);
		var newTopButton =document.getElementById("upButton"+sectionNum);
		newBottomButton.addEventListener("click",function(){moveUpSection(sectionNum);});
		newTopButton.addEventListener("click",function(){moveUpSection(sectionNum+1);});
		newBottomButton.id = "upButton"+sectionNum;
		bottom = sectionNum +1;
		newTopButton.id = "upButton"+bottom;

		newBottomButton =document.getElementById("downButton"+bottom);
		newTopButton =document.getElementById("downButton"+sectionNum);
		newBottomButton.addEventListener("click",function(){moveDownSection(sectionNum);});
		newTopButton.addEventListener("click",function(){moveDownSection(sectionNum+1);});
		newBottomButton.id = "downButton"+sectionNum;
		bottom = sectionNum +1;
		newTopButton.id = "downButton"+bottom;
	}
	else{
		alert("This section is already at the bottom of the page.");
	}
}


function genJson() {
    var submitText = "";
    var sections = document.getElementsByClassName("sectionForm");

    var title = document.getElementById("pageTitle").value;
    submitText = "{\n";
    submitText += "\t\"Page\":{ \n";
    submitText += "\t\t\"title\":\"" + title + "\",\n";
    submitText += "\t\t\"sections\":{ \n"

    // go through the sections and get their data
    for (var secNum = 0; secNum < sections.length; secNum++) {

        submitText += "\t\t\t\"section" + secNum + "\":{\n";
        submitText += "\t\t\t\"secTitle\":\"" + document.getElementById("sectionTitle" + (secNum + 1)).value + "\",\n";
        submitText += "\t\t\t\"content\":{ \n"
    }
    submitText += "\t\t}\n";
    submitText += "\t}\n";
    submitText += "}\n";
    console.log(submitText);
}

function helloWorld(){
	alert("Hello World");
}