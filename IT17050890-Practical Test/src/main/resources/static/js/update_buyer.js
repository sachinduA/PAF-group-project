const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const uri = 'http://localhost:8080/update-buyer/'+ urlParams.get('id');
const getBuyerUri = 'http://localhost:8080/get-buyer/' + urlParams.get('id');


let GetReq = new Request(getBuyerUri, {
    method: 'GET',
    mode: 'cors'
});

fetch(GetReq)
    .then( (response)=>{
        if(response.ok){
            return response.json();
        }else{
            throw new Error('BAD HTTP!');
        }
    })
    .then( (buyer) =>{
		$("#firstName").val(buyer.firstName);
		$("#lastName").val(buyer.lastName);
		$("#contactNo").val(buyer.contactNo);
		$("#nic").val(buyer.nic);
    })
    .catch( (err) =>{
        console.log('ERROR:', err.message);
    });


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
	    method: 'PUT',
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