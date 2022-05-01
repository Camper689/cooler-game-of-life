const URL_GRID = "/grid";
const URL_NEW_GAME = "/new-game";
const URL_EVOLVE = "/evolve";

var WIDTH = 100;
var HEIGHT = 100;

var PLAYING = false;
var SCALE = 1.5;

var SIZE = 10;
var OFFSET = 0;

var DATA;

var HOVER_ROW = -1;
var HOVER_INDEX = -1;

var MOUSE_CLICKED = false;

setInterval(evolveIfPlaying, 500);

function zoomIn() {
    if(SCALE < 0.5) return;

    SCALE -= 0.1;
    draw();
}

function zoomOut() {
    if(SCALE > 3) return;

    SCALE += 0.1;
    draw();
}

function setMouseClickedState() {
    var flags = event.buttons !== undefined ? event.buttons : event.which;
    MOUSE_CLICKED = (flags & 1) === 1;
}

function putCell() {
    if(!DATA || HOVER_INDEX == -1)
        return;

    var x = HOVER_INDEX;
    var y = HOVER_ROW;

    var current = DATA[y][x];

    if(current == 0) {
        httpPut("/add/" + x + "/" + y, null);
        update();
    }
}

function onGridHover() {
    if(!DATA) return;

    const rect = event.target.getBoundingClientRect();
    const mouseX = event.clientX - rect.left;
    const mouseY = event.clientY - rect.top;

    const previousHoverRow = HOVER_ROW;
    const previousHoverIndex = HOVER_INDEX;

    const rectSize = (SIZE + OFFSET) / SCALE;
    var topOffset;

    for(var rowIndex = 0; rowIndex < DATA.length; rowIndex++) {
        topOffset = rowIndex * rectSize;

        if(mouseY >= topOffset && mouseY <= topOffset + rectSize) {
            HOVER_ROW = rowIndex;

            const row = DATA[rowIndex];
            for(var index = 0; index < row.length; index++) {
                var leftOffset = index * rectSize;
                if(mouseX >= leftOffset && mouseX <= leftOffset + rectSize) {
                    HOVER_INDEX = index;
                    break;
                }
            }

            break;
        }
    }

    if(HOVER_INDEX != -1 && (
        previousHoverRow != HOVER_ROW || previousHoverIndex != HOVER_INDEX
    )) drawHover();

    if(MOUSE_CLICKED)
        putCell();
}

function getAndResizeCanvasContext(id) {
    const canvas = document.getElementById(id);
    const ctx = canvas.getContext('2d');

    canvas.width = canvas.offsetWidth * SCALE;
    canvas.height = canvas.offsetHeight * SCALE;

    return ctx;
}

function drawHover() {
    if(!DATA) return;

    const ctx = getAndResizeCanvasContext('hover');

    const left = HOVER_INDEX * (SIZE + OFFSET) + OFFSET;
    const top = HOVER_ROW * (SIZE + OFFSET) + OFFSET;

    ctx.fillStyle = 'yellow';
    ctx.fillRect(left, top, SIZE, SIZE);
}

function draw() {
    if(!DATA) return;

    const ctx = getAndResizeCanvasContext('grid');

    var prevStyle = '';
    var topOffset = OFFSET;
    var leftOffset = 0;

    for(var rowIndex = 0; rowIndex < DATA.length; rowIndex++) {
        const row = DATA[rowIndex];
        leftOffset = 0 + OFFSET;
        for(var elemIndex = 0; elemIndex < row.length; elemIndex++) {
            const elem = row[elemIndex];

            var style = getStyleFromTag(elem);
            if(prevStyle != style) {
                prevStyle = style;
                ctx.fillStyle = style;
            }

            ctx.fillRect(leftOffset, topOffset, SIZE, SIZE);
            leftOffset += SIZE + OFFSET;
        }
        topOffset += SIZE + OFFSET;
    }
}

function evolveIfPlaying() {
    if(PLAYING)
        evolve();
}

function evolve() {
    httpPost(URL_EVOLVE, null);
    update();
}

function update() {
    DATA = JSON.parse(httpGet(URL_GRID));
    draw();
}

function getStyleFromTag(tag) {
    switch(tag) {
        case 1:
            return 'peachpuff';
        default:
            return 'rgb(128, 128, 128)';
    }
}

function newGame() {
    httpPost(URL_NEW_GAME, {width: 300, height: 150});
    update();
}

function togglePause() {
    PLAYING = !PLAYING;

    var playButton = document.getElementById('play-button');
    playButton.classList.add(PLAYING ? "playing" : "paused");
    playButton.classList.remove(!PLAYING ? "playing" : "paused");
}

function httpRequest(method, url, data) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open(method, url, false);
    xmlHttp.setRequestHeader("content-type", "application/json");
    xmlHttp.send(data == null ? null : JSON.stringify(data));

    return xmlHttp.responseText;
}

function httpGet(url) {
    return httpRequest("GET", url, null);
}

function httpPost(url, data) {
    return httpRequest("POST", url, data);
}

function httpPut(url, data) {
    return httpRequest("PUT", url, data);
}