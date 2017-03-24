$(document).ready(function () {

	var socket = new SockJS("ws-connect");
	var connection = Stomp.over(socket);

	var errorCallback = function(error) {
	      // display the error's message header:
	      console.log(error);
	 };
	 
	 var successCallback = function(frame) {
			console.log("Connected: "+ frame);
			connection.subscribe('/topic/mapUpdate', function(data) {
				var dataObject = JSON.parse(data.body);
				if(dataObject.boardState == undefined){
					
				}else{
					updateBoard(dataObject);
				}
			});
		};
	
	connection.connect({}, successCallback, errorCallback);


    $("td").click(function () {

        var spot = $(this).attr("data");
        
        $("td").click(function () {
        	var secondSpot = $(this).attr("data");
        	var message = {
        			startLoc: spot,
        			endLoc: secondSpot
        	};
        	connection.send("/app/mapUpdate", {}, JSON.stringify(message));
        	
        })
    });

});

function updateBoard(dataObject){
	var boardState = dataObject.boardState;
	for(var loc in boardState){
		if(boardState[loc] != null){
			$("td[data='"+loc+"']").addClass(boardState[loc].pieceString)
		}
	}
}