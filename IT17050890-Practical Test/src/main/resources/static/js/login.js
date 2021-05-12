const uri = "http://localhost:8080/login";

$(document).ready(function () {
 	$("form").submit(function (event) {
		var formData = {
	      	email: $("#email").val(),
	      	password: $("#password").val()
    	};
	
	var headers = new Headers();
 	headers.set('Content-type', 'application/json');
	
	let req = new Request(uri, {
	    method: 'POST',
	    mode: 'cors',
	    headers,
	    body: JSON.stringify(formData)
	});
	
    fetch(req)
	    .then((resp) =>{
		    if(resp.ok){
		    	return resp.json();
		    } else {
		    	throw new Error('BAD HTTP!');
		    }
	    })
	    .then((data)=>{
	    	if(data["result"] === "TRUE"){
	    		window.location.replace("http://localhost:8080/buyers.html");
	    	} else if(data["result"] === "FALSE"){
	    		$("#error").html(data["error"]);
	    	}
	    })
	    .catch( (err) =>{
	        console.log('ERROR:', err.message);
	    });

    event.preventDefault();
  });
});