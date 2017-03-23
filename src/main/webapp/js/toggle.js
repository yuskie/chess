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
		        if (dataObject.mapData === undefined) {
		            updateCell(dataObject.x, dataObject.y, dataObject.color);
		        } else {
		            updateTable(dataObject);
		        }
			});
		};
	
	connection.connect({}, successCallback, errorCallback);

    $(".rounded-box").click(function () {
        $(".selected").removeClass("selected");
        $(this).addClass("selected");
    });

    $("td").click(function () {

        var rowIndex = $(this).attr("data-row");
        var colIndex = $(this).attr("data-col");
        var colorValue = $(".selected").css("background-color");

        var message = {
            x: rowIndex,
            y: colIndex,
            color: colorValue
        };
        connection.send("/app/mapUpdate", {}, JSON.stringify(message));
    });

});


function updateCell(row, column, colorValue) {
    var cell = $(`td[data-row='${row}']`).filter(`td[data-col='${column}']`);
    cell.css("background-color", colorValue);
    cell.removeClass("default");
}

function updateTable(data) {
    for (var rowIndex = 0; rowIndex < data.mapData.length; rowIndex++) {
        for (var colIndex = 0; colIndex < data.mapData[rowIndex].length; colIndex++) {
                        
            var colorValue = data.mapData[rowIndex][colIndex];
            if(colorValue !== null){
                updateCell(rowIndex, colIndex, colorValue);
            }
            
        }
    }
}