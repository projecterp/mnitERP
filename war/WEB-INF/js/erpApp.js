'use strict';

/**
 * @ngdoc object --> ;) Documentation :-0
 * @name mnitErp
 * @requires $routeProvider
 * @requires mnitErpControllers
 * @requires ui.bootstrap
 * @description
 * Root application, routes and specifies controller depending on the URL requested.
 */
 
var app = angular.module('mnitErp',
	    ['ERPControllers', 'ngRoute', 'ui.bootstrap']).
	    config(['$routeProvider',
	        function ($routeProvider) {
	            $routeProvider.
	                when('/conference', {
	                    templateUrl: '/partials/show_conferences.html',
	                    controller: 'ShowConferenceCtrl'
	                }).
	                when('/conference/create', {
	                    templateUrl: '/partials/create_conferences.html',
	                    controller: 'CreateConferenceCtrl'
	                }).
	                when('/conference/detail/:websafeConferenceKey', {
	                    templateUrl: '/partials/conference_detail.html',
	                    controller: 'ConferenceDetailCtrl'
	                }).
	                when('/profile', {
	                    templateUrl: '/partials/profile.html',
	                    controller: 'MyProfileCtrl'
	                }).
	                when('/', {
	                    templateUrl: '/partials/home.html'
	                }).
	                otherwise({
	                    redirectTo: '/'
	                });
	        }]);
 
 /**
 * @ngdoc constant
 * @name HTTP_ERRORS
 * @description
 * HTTP error codes.  :P Only one defined so far, 'cause we don't have room for any sort of errors :D
 */
app.constant('HTTP_ERRORS', {
    'UNAUTHORIZED': 401
});


/**
 * @ngdoc service
 * @name oauth2Provider
 * @description
 * Service for OAuth2 information shared across all the pages.
 */
 
app.factory('oauth2Provider', function ($modal) {
    var oauth2Provider = {
        CLIENT_ID: 'client-id',               //to be put later on
        SCOPES: 'https://www.googleapis.com/auth/userinfo.email profile',
        signedIn: false
    };

    //Calls the OAuth2 authentication method
	
    oauth2Provider.signIn = function (callback) {
        gapi.auth.signIn({
            'clientid': oauth2Provider.CLIENT_ID,
            'cookiepolicy': 'single_host_origin',
            'accesstype': 'online',
            'approveprompt': 'auto',
            'scope': oauth2Provider.SCOPES,
            'callback': callback
        });
    };

    // Logs out the user.

    oauth2Provider.signOut = function () {
        gapi.auth.signOut();
        // Explicitly set the invalid access token in order to make the API calls fail.
        gapi.auth.setToken({access_token: ''});
        oauth2Provider.signedIn = false;
    };


    /**
     * Shows the modal with Google+ sign in button.
     *
     * @returns {*|Window}
     */
    oauth2Provider.showLoginModal = function() {
        var modalInstance = $modal.open({
            templateUrl: '/partials/login.modal.html',
            controller: 'OAuth2LoginModalCtrl'
        });
        return modalInstance;
    };

    return oauth2Provider;
});
