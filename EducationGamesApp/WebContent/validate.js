function validate(theForm){
        if(theForm.user.value.length==0){
                alert("User name can't be blank");
                theForm.user.focus();
               return false;
        }else if(theForm.request.value.length==0){
                alert("Request cannot be blank");
                theForm.request.focus();
                return false;
        }else if(theForm.request.value.length>100){
                alert("Value cannot be more than 100 characters");
                theForm.request.focus();
                return false;
        }
}