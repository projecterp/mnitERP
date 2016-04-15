      var scopes = 'https://www.googleapis.com/auth/userinfo.email';
      var clientId = '495391269113-k3oghl07mv84mnqnep564trn1joiij5q.apps.googleusercontent.com';    
     // var scopes = 'https://www.googleapis.com/auth/plus.me';
	  var auth = false;
      function handleClientLoad() {
    	  console.log("FUckingggg");
        window.setTimeout(checkAuth,1);
      }
	  
      function checkAuth() {
    	  console.log("Fuck me baby!!!");
        gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
      }
	  
      function handleAuthResult(authResult) {
        var authorizeButton = document.getElementById('authorize-button');
        if (authResult && !authResult.error) {
          authorizeButton.style.visibility = 'hidden';
         
          var res = makeApiCall();
		  if(res==false)
		    handleClientLoad();
		  else{
		    auth = true;
		    console.log("FINGER IN YOUR A@@");} //just for testing
        } else {
        	console.log("FINGER IN YOUR A**");
           handleClientLoad();
        }
      }
	/*  
      function handleAuthClick(event) {
        gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
        return false;
      }
	  */
      // Load the API and make an API call
      function makeApiCall() {
     /*   gapi.client.load('plus', 'v1', function() {
          var request = gapi.client.plus.people.get({
            'userId': 'me'
          });
          request.execute(function(resp) {
            var email = resp.emails.value;
			if(email.substring(email.indexOf("@"),email.length-1) != "mnit.ac.in")
              return false;ss
            else
              return true;			
          }); 
        }); */
    	  console.log("Fucked");
      }
        