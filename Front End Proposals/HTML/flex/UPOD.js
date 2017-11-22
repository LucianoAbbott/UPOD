function addSection(input){

    	var div = document.createElement("div");
	div.style.width = "100px";
	div.style.height = "100px";
	div.style.background = "red";
	div.style.color = "white";
	div.innerHTML = "Hello";

	document.getElementById("main").appendChild(div);

}