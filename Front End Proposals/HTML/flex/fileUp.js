function readUrl(input){
    if(input.files && input.files[0]){
        var reader = new FileReader();

        reader.onload = function(e){
            $('#disp').attr('src', e.target.result)
                      .width(150)
                      .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }

}