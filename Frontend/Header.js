function genSearchSection() {
    var topDiv = document.createElement("div");
    var titleDiv = document.createElement("div");
    var searchDiv = document.createElement("div");
    var buttonDiv = document.createElement("div");

    topDiv.className = "topBar keyline";
    titleDiv.innerHTML = "UPOD";
    titleDiv.className = "searchTitle";
    searchDiv.className = "barSearch";
    buttonDiv.id = "buttonBar";
    buttonDiv.align = "right";

    topDiv.appendChild(titleDiv);
    topDiv.appendChild(searchDiv);
    topDiv.appendChild(buttonDiv);

    var searchBar = document.createElement("input");
    var goButton = document.createElement("button");

    searchBar.type = "text";
    searchBar.name = "searchBar";
    searchBar.className = "searchBar";
    goButton.type = "button";
    goButton.className = "searchButton";
    goButton.innerHTML = "&#10148;";

    searchDiv.appendChild(searchBar);
    searchDiv.appendChild(goButton);

    document.getElementsByTagName("body")[0].appendChild(topDiv);

}