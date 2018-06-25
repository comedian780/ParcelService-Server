angular.
  module('parcelConfig').
  component('parcelSize', {
	  transclude: true,
	  template:
	'<div class="w3-panel w3-card-2">'+
		'<div class="w3-container w3-teal" >'+
		    '<h2>Paketgröße</h2>'+
		'</div>'+
		'<div>'+
			'<form class="">'+
			'<p>Länge:<br/><input id="length" class="w3-input" type="text" style="width:100%" ng-model="$ctrl.parcel.size.length">'+
			'<p>Breite:<br/><input id="width" class="w3-input" type="text" style="width:100%" ng-model="$ctrl.parcel.size.width">'+
			'<p>Tiefe:<br/><input id="depth" class="w3-input" type="text" style="width:100%" ng-model="$ctrl.parcel.size.depth">'+
			'<div class="w3-button w3-teal" id="cfg-size-btn" ng-click="$ctrl.calcSize($ctrl.parcel)" style="cursor: pointer;">'+
          'Paketgröße berechnen</div> <p id="parcelRes">Paketgröße: {{$ctrl.parcel.size.cat}}</p>'+
			'</form>'+
		'</div>'+
	'</div>' ,


    controller: function ParcelPriceController($rootScope, $http) {


    	this.parcel = {
    		size:{length: '0',width:'0',depth:'0',cat:'S'}
    	};
    	$rootScope.parcelsize = this.parcel;
    	this.calcSize = function($parcel)
    	{
    		var parameter = JSON.stringify($parcel.size);
    		var url = "www.allgaeu-parcel-service.de:8443/parcel/size";
    		$http.post(url, parameter).then(function(data, status, headers, config)
    		{
	          ret_data = angular.fromJson(data);
	          $parcel.size.cat=ret_data['data']['cat'];
    		});
    	}


    }
  });
