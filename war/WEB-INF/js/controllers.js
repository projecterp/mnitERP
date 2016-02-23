'use strict';

var mnitErp = mnitErp || {};
/**
 * @ngdoc module
 * @name ERPControllers
 * @description
 * Angular module for controllers.
 */

mnitErp.controllers = angular.module('ERPControllers', ['ui.bootstrap']);

/**
 * @ngdoc controller
 * @name MyProfileCtrl
 * @description
 * User Profile page controller
 */
mnitErp.controllers.controller('MyProfileCtrl',
    function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
        $scope.submitted = false;
        $scope.loading = false;
        /**
         * The initial profile retrieved from the server - null
         * @type {{}}
         */
        $scope.initialProfile = {};

        /**
         * Enum scopes for various fields
         * @type {string[]}
         */
        
		$scope.Marital[
	       'MARRIED',
	     'UNMARRIED',
	       'WIDOWED'
      	];
	
	    $scope.BGroup[
		'A+',
		'A-',
		'B+',
		'B-',
		'AB+',
		'AB-',
		'O+',
		'O-',
		'UNSPECIFIED'
	    ];
	
	   $scope.Hosteller[
	   'Hosteller',
	   'Day_Scholar'		
	   ];
	
	   $scope.Admission[
		'CSAB',
		'JOSAA',
		'DASA',
		'GATE',
		'CAT'
	   ];
	
	   $scope.Category[
		'GENERAL',
		'SC',
		'ST',
		'OBC'
	   ];
	
	   $scope.Branch[
		'CSE',
		'ME',
		'EE',
		'ECE',
		'ARCH',
		'META',
		'CHEM',
		'CE',
		'HUMANITIES',
		'CHEMISTRY',
		'PHYSICS',
		'MATHEMATICS'
	  ];
	
	$scope.Degree[
		'B.Tech',
		'M.Tech',
		'Ph.D',
		'MBA',
		'M.Sc.'
	];
	
	$scope.Status[
		'ACTIVE',
		'DORMANT'
	];
	
	$scope.Gender[
		'MALE',
		'FEMALE'
	];
	
	$scope.ftpt[
		'PT',
		'FT'
	];
	
	$scope.duration[
		'Two Years',
		'Three_Years',
		'Four_Years',
		'Five_Years'
	];

    //Initializes the User profile page
        $scope.init = function () {
            var retrieveProfileCallback = function () {
                $scope.facultyProfile = {};  //empty StudentProfile
				$scope.studentProfile = {};  //empty facultyProfile
                $scope.loading = true;
				$scope.prof = false;
                gapi.client.mniterp.checkUser().execute(function (resp) {
				/** check user and then load which API function has to be called next
				to retrieve faculty/student profile from datastore**/  
                        $scope.$apply(function () {
                            $scope.loading = false;
                            if (resp.error) {
                                // Failed to check a user/ Authenticate the user.
                            } else {
                                // Succeeded to check user
                                $scope.prof = resp.result;  
                            }
                        });
                    }
                );
				if($scope.prof==true){
				//loads facultyProfile object
				  gapi.client.mniterp.loadFacultyProfile().execute(function (resp){
				        $scope.$apply(function () {
                            $scope.loading = false;
                            if (resp.error) {
                                // Failed to retrieve facultyProfile.
                            } else {
                                // Succeeded to retrieve facultyProfile, now copying the attributes in scope.
                                $scope.facultyProfile.name = resp.result.name;
                               	$scope.facultyProfile.instiEmail = resp.result.instiEmail;
                               	$scope.facultyProfile.email	= resp.result.email;
 	                     		$scope.facultyProfile.profID = resp.result.profID;
								$scope.facultyProfile.officeNo = resp.result.officeNo;
								$scope.facultyProfile.mobileNo = resp.result.mobileNo;
								$scope.facultyProfile.mobVisible = resp.result.mobVisible;
								$scope.facultyProfile.dept = resp.result.dept;
								$scope.facultyProfile.quals = resp.result.quals;
								$scope.facultyProfile.rsrch = resp.result.rsrch;
								$scope.facultyProfile.cProjects = resp.result.cProjects;
                            }
                        });
				  });
				}
				else{
				//loads facultyProfile object
				  gapi.client.mniterp.loadStudentProfile().execute();
				}
            };
            if (!oauth2Provider.signedIn) {
                var modalInstance = oauth2Provider.showLoginModal();
                modalInstance.result.then(retrieveProfileCallback);
            } else {
                retrieveProfileCallback();
            }
        };

        /**
         * Invokes the conference.saveProfile API.
         */
        $scope.saveProfile = function () {
            $scope.submitted = true;
            $scope.loading = true;
            gapi.client.mniterp.saveProfile($scope.profile).
                execute(function (resp) {
                    $scope.$apply(function () {
                        $scope.loading = false;
                        if (resp.error) {
                            // The request has failed.
                            var errorMessage = resp.error.message || '';
                            $scope.messages = 'Failed to update a profile : ' + errorMessage;
                            $scope.alertStatus = 'warning';
                            $log.error($scope.messages + 'Profile : ' + JSON.stringify($scope.profile));

                            if (resp.code && resp.code == HTTP_ERRORS.UNAUTHORIZED) {
                                oauth2Provider.showLoginModal();
                                return;
                            }
                        } else {
                            // The request has succeeded.
                            $scope.messages = 'The profile has been updated';
                            $scope.alertStatus = 'success';
                            $scope.submitted = false;
                            $scope.initialProfile = {
                                displayName: $scope.profile.displayName,
                                teeShirtSize: $scope.profile.teeShirtSize
                            };

                            $log.info($scope.messages + JSON.stringify(resp.result));
                        }
                    });
                });
        };
    })
;