const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const uri = 'http://localhost:8080/get-buyer/' + urlParams.get('id');

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
    .then( (buyer) =>{
		var item = '<h1>' + buyer.firstName + ' ' + buyer.lastName + '</h1>'+
					'<hr />'+
					'<table>'+
						'<tr>'+
							'<th>Buyer ID:</th>'+
							'<td>' + buyer.buyer_id + '</td>'+
						'</tr>'+
						'<tr>'+
							'<th>Buyer Name:</th>'+
							'<td>' + buyer.firstName + ' ' + buyer.lastName + '</td>'+
						'</tr>'+
						'<tr>'+
							'<th>NIC:</th>'+
							'<td>' + buyer.nic + '</td>'+
						'</tr>'+
						'<tr>'+
							'<th>Contact No:</th>'+
							'<td>' + buyer.contactNo + '</td>'+
						'</tr>'+
					'</table>';
				
		$("#buyer").append(item);
    })
    .catch( (err) =>{
        console.log('ERROR:', err.message);
    });