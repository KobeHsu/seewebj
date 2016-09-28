// Initialize the Modeling
function initModeling(modelType) {
	if (modelType == 8) {
		// addSwinglane(150, '外部互動界線');
		// addSwinglane(300, '可視界線');
		// addSwinglane(450, '內部互動界線');
        addConnector("SwingLane", '外部互動界線', 150);
        addConnector("SwingLane", '可視界線', 300);
        addConnector("SwingLane", '內部互動界線', 450);
        clearSelected("group_0001_");
        clearSelected("group_0002_");
        clearSelected("group_0003_");
	}
}
// Add Swinglane
function addSwinglane(posY, swinglaneName) {
    var grp = getGroupPrefix(gSerialNo);
    var swinglaneId = grp + "swinglane";
    
    var newSwinglane = gSvg.line(0, posY, $('#drawArea').width(), posY);
    newSwinglane.addClass("ModelingSwinglane");
    newSwinglane.attr("id", swinglaneId);
    
    var textId = grp + "text";
    var text = gSvg.text(0, posY-15, swinglaneName);
    text.attr("id", textId);
    text.addClass("myLabel");    
		
    gSerialNo++;		
}