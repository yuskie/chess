var messageCount = 0;
$(document).ready(function() {
	var socket = new SockJS("ws-connect");
	var connection = Stomp.over(socket);

	var errorCallback = function(error) {
		// display the error's message header:
		console.log(error);
	};

	var successCallback = function(frame) {
		console.log("Connected: " + frame);
		connection.subscribe('/topic/chessUpdate', function(data) {
			var dataObject = JSON.parse(data.body);
			updateBoard(dataObject);
			messageCount+=1;
			console.log(messageCount);
		});
	};

	connection.connect({}, successCallback, errorCallback);

	$("td").mousedown(function() {
		console.log($(this).attr("class"));
		var spot = $(this).attr("data");
		$("#startLoc").val(spot);
	});

	$("td").mouseup(function() {
		if ($("#startLoc").val() != "") {
			var secondSpot = $(this).attr("data");
			var message = {
				color: $("#player").text(),
				startLoc : $("#startLoc").val(),
				endLoc : secondSpot
			};
			connection.send("/app/chessUpdate", {}, JSON.stringify(message));
			$("#startLoc").val("");
		}
	})

});

function updateBoard(dataObject) {
	if (dataObject.color == null && $("#player").text() == "") {
		alert("Too many players, just spectating");
	}
	if(messageCount == 0){
		$("#player").text(dataObject.color);
	}
	if(dataObject.checkMate && dataObject.color == $("#player").text()){
		alert("You've been checkmated");
	}
	var chessModel = dataObject.chessModel;
	for ( var loc in chessModel) {
		if (chessModel[loc] != null) {
			var classes = $("td[data='" + loc + "']").attr("class");
			console.log(classes);
			$("td[data='" + loc + "']").addClass(chessModel[loc]);
		}
	}
}