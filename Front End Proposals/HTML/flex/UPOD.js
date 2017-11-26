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

    /*var titleForm = document.createElement("form");*/
    var f = document.createElement("form");
    var addButton = document.createElement("button");
    var removeButton = document.createElement("button");
    f.className = "sideBySide";
    addButton.innerHTML = "&#65514";
    removeButton.innerHTML = "&#65516";
    addButton.className = "solidButton";
    removeButton.className = "solidButton";



    f.appendChild(addButton);
    f.appendChild(removeButton);
    rowA.appendChild(f);

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