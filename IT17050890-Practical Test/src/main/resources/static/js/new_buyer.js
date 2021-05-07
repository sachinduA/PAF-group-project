const uri = 'http://localhost:8080/new-buyer';

$(document).ready(function () {
 	$("form").submit(function (event) {
		var formData = {
	      	firstName: $("#firstName").val(),
	      	lastName: $("#lastName").val(),
	      	contactNo: $("#contactNo").val(),
	      	nic: $("#nic").val()
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
    .then( (buyer) =>{
		window.location.replace("http://localhost:8080/buyers.html");
    })
    .catch( (err) =>{
        console.log('ERROR:', err.message);
    });

    event.preventDefault();
  });
});