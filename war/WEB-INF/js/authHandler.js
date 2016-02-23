      var clientId = 'client-ID';
      //var apiKey = 'api-key';
      // To enter one or more authentication scopes, refer to the documentation for the API.
      var scopes = 'https://www.googleapis.com/auth/plus.me';
	  var auth = false;
      function handleClientLoad() {
        window.setTimeout(checkAuth,1);
      }
	  
      function checkAuth() {
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
			window.location = "redirection page URL";}
        } else {
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
        gapi.client.load('plus', 'v1', function() {
          var request = gapi.client.plus.people.get({
            'userId': 'me'
          });
          request.execute(function(resp) {
            var email = resp.emails.value;
			if(email.substring(email.indexOf("@"),email.length-1) != "mnit.ac.in")
              return false;
            else
              return true;			
          });
        });
      }