const uri = 'http://localhost:8080/get-buyers';

let req = new Request(uri, {
    method: 'GET',
    mode: 'cors'
});

fetch(req)
    .then( (response)=>{
        if(response.ok){
            return response.json();
        }else{
            throw new Error('BAD HTTP!');
        }
    })
    .then( (jsonData) =>{
		jsonData.forEach(buyer => {
			var item = '<div class="card">'+
							'<div class="row">'+
								'<div class="col-md-1">'+ buyer.buyer_id +'</div>'+
								'<div class="col-md-4">'+ buyer.firstName + ' ' + buyer.lastName +'</div>'+
								'<div class="col-md-2">'+ buyer.nic + '</div>'+
								'<div class="col-md-2">'+ buyer.contactNo + '</div>'+
								'<div class="col-md-1">'+
									'<a href="/buyer.html/?id=' + buyer.buyer_id + '">View</a>'+
								'</div>'+
								'<div class="col-md-1">'+
									'<a href="/buyerUpdate.html/?id=' + buyer.buyer_id + '">Edit</a>'+
								'</div>'+
								'<div class="col-md-1">'+
									'<input type="submit" id="delete" value="Delete" onclick="myFunc('+buyer.buyer_id+')"/>'+
								'</div>'+
							'</div>'+
						'</div>';
					
			$("#buyers").append(item);
		});
    })
    .catch( (err) =>{
        console.log('ERROR:', err.message);
    });
    
function myFunc(buyerId){
	const deleteUri = 'http://localhost:8080/delete-buyer/'+ buyerId;
	
	var result = confirm("Do you want to delete?");
	
	if (result) {
		fetch(deleteUri, {method: 'DELETE'})
		    .then(() =>{
				window.location.replace("http://localhost:8080/buyers.html");
		    })
		    .catch( (err) =>{
		        console.log('ERROR:', err.message);
		    });
	}

}