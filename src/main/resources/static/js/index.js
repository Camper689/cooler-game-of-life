const configurationResource = Vue.resource("/configuration");
const newGameResource = Vue.resource('/new-game');
const evolveResource = Vue.resource('/evolve');
const gridResource = Vue.resource('/grid');

Vue.component('navbar', {
    props: [
        'toggleConfigurationMethod', 'newGameMethod', 'evolveMethod',
        'timerToggleMethod', 'timerStarted', 'zoomInMethod',
        'zoomOutMethod'
    ],
    data: function() {
        return {
            playing: this.timerStarted
        }
    },
    watch: {
        timerStarted: function(newValue, oldValue) {
            this.playing = newValue;
        }
    },
    template: `
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Game of life</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <button class="btn btn-outline-success" type="button" @click="newGameMethod()">New game</button>
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-outline-success" type="button" @click="toggleConfigurationMethod()">Configuration...</button>
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-outline-success" type="button" @click="evolveMethod()">
                                Evolve
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-circle" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
                                </svg>
                            </button>
                        </li>
                        <li class="nav-item">
                            <button class="btn btn-success" @click="timerToggleMethod()">
                                <svg :class="playing ? 'd-none' : ''" class="bi bi-play-fill" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path d="m11.596 8.697-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 0 1 0 1.393z"/>
                                </svg>
                                <svg :class="!playing ? 'd-none' : ''" class="bi bi-pause-fill" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path d="M5.5 3.5A1.5 1.5 0 0 1 7 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5zm5 0A1.5 1.5 0 0 1 12 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5z"/>
                                </svg>
                            </button>
                        </li>
                        <li class="nav-item">
                            <div class="btn-group">
                                <button class="btn btn-outline-info" type="button" @click="zoomInMethod()">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-zoom-in" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                                        <path d="M10.344 11.742c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1 6.538 6.538 0 0 1-1.398 1.4z"/>
                                        <path fill-rule="evenodd" d="M6.5 3a.5.5 0 0 1 .5.5V6h2.5a.5.5 0 0 1 0 1H7v2.5a.5.5 0 0 1-1 0V7H3.5a.5.5 0 0 1 0-1H6V3.5a.5.5 0 0 1 .5-.5z"/>
                                    </svg>
                                </button>
                                <button class="btn btn-outline-info" type="button" @click="zoomOutMethod()">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-zoom-out" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                                        <path d="M10.344 11.742c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1 6.538 6.538 0 0 1-1.398 1.4z"/>
                                        <path fill-rule="evenodd" d="M3 6.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z"/>
                                    </svg>
                                </button>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    `
});

Vue.component('canvas-grid', {
    props: ['styles', 'scale', 'grid', 'offset', 'size', 'getCanvasContextMethod'],
    data: function() {
        return {
            context: undefined,
            grid0: this.grid,
            styles0: this.styles,
            offset0: this.offset,
            size0: this.size
        }
    },
    mounted: function() {
        this.refreshContext();
    },
    methods: {
        refreshContext: function() {
            this.context = this.getCanvasContextMethod('grid');
            this.draw();
        },
        getStyleFromTag: function(tag) {
            return this.styles0[tag];
        },
        draw: function() {
            if(!this.grid0) return;

            var prevStyle = '';
            var topOffset = this.offset0;
            var leftOffset = 0;

            for(var rowIndex = 0; rowIndex < this.grid0.length; rowIndex++) {
                const row = this.grid0[rowIndex];
                leftOffset = 0 + this.offset0;
                for(var elemIndex = 0; elemIndex < row.length; elemIndex++) {
                    const elem = row[elemIndex];

                    var style = this.getStyleFromTag(elem);
                    if(prevStyle != style) {
                        prevStyle = style;
                        this.context.fillStyle = style;
                    }

                    this.context.fillRect(leftOffset, topOffset, this.size0, this.size0);
                    leftOffset += this.size0 + this.offset0;
                }
                topOffset += this.size0 + this.offset0;
            }
        }
    },
    watch: {
        scale: function(newValue, oldValue) {
            this.refreshContext();
        },
        grid: function(newValue, oldValue) {
            this.grid0 = newValue;
            this.draw();
        },
        styles: function(newValue, oldValue) {
            this.styles0 = styles;
            this.draw();
        },
        offset: function(newValue, oldValue) {
            this.offset0 = newValue;
            this.draw();
        },
        size: function(newValue, oldValue) {
            this.size0 = newValue;
            this.draw();
        }
    },
    template: `
        <canvas id="grid"></canvas>
    `
});

Vue.component('canvas-hover', {
    props: ['size', 'offset', 'scale', 'grid', 'getCanvasContextMethod', 'putCellMethod'],
    data: function() {
        return {
            hoverRow: -1,
            hoverIndex: -1,
            grid0: this.grid,
            context: undefined,
            mouseClicked: false
        }
    },
    watch: {
        grid: function(newValue, oldValue) {
            this.grid0 = newValue;
        },
        scale: function(newValue, oldValue) {
            this.refreshContext();
        }
    },
    mounted: function() {
        this.refreshContext();
    },
    methods: {
        refreshContext: function() {
            this.context = this.getCanvasContextMethod('hover');
            this.draw();
        },
        onHover: function() {
            if(!this.grid) return;

            const rect = event.target.getBoundingClientRect();
            const mouseX = event.clientX - rect.left;
            const mouseY = event.clientY - rect.top;

            const previousHoverRow = this.hoverRow;
            const previousHoverIndex = this.hoverIndex;

            const rectSize = (this.size + this.offset) / this.scale;
            var topOffset;

            for(var rowIndex = 0; rowIndex < this.grid0.length; rowIndex++) {
                topOffset = rowIndex * rectSize;

                if(mouseY >= topOffset && mouseY <= topOffset + rectSize) {
                    this.hoverRow = rowIndex;

                    const row = this.grid0[rowIndex];
                    for(var index = 0; index < row.length; index++) {
                        var leftOffset = index * rectSize;
                        if(mouseX >= leftOffset && mouseX <= leftOffset + rectSize) {
                            this.hoverIndex = index;
                            break;
                        }
                    }

                    break;
                }
            }

            if(this.hoverIndex != -1 && (
                previousHoverRow != this.hoverRow || previousHoverIndex != this.hoverIndex
            )) this.draw();

            if(this.mouseClicked)
                this.putCellMethod(this.hoverIndex, this.hoverRow);
        },
        draw: function() {
            const left = this.hoverIndex * (this.size + this.offset) + this.offset;
            const top = this.hoverRow * (this.size + this.offset) + this.offset;

            this.context.clearRect(0, 0, this.context.canvas.width, this.context.canvas.height);
            this.context.fillStyle = 'yellow';
            this.context.fillRect(left, top, this.size, this.size);
        },
        updateMouseClickedState: function() {
            var flags = event.buttons !== undefined ? event.buttons : event.which;
            this.mouseClicked = (flags & 1) === 1;
        }
    },
    template: `
        <canvas id="hover"
            @mousedown="updateMouseClickedState()" @mouseup="updateMouseClickedState()"
            @mousemove="onHover()" @click="putCellMethod(hoverIndex, hoverRow)"
        ></canvas>
    `
});

Vue.component('canvas-parent', {
    props: ['styles', 'scale', 'size', 'grid', 'visible', 'putCellMethod'],
    data: function() {
        return {
            isVisible: this.visible
        }
    },
    methods: {
        getCanvasContext: function(elemId) {
            const canvas = document.getElementById(elemId);
            const ctx = canvas.getContext('2d');

            canvas.width = canvas.offsetWidth * this.scale;
            canvas.height = canvas.offsetHeight * this.scale;

            return ctx;
        }
    },
    watch: {
        visible: function(newValue, oldValue) {
            this.isVisible = newValue;
        }
    },
    template: `
        <div class="h-100 w-100" :class="isVisible ? '' : 'd-none'" style="position: relative">
            <canvas-hover :scale="scale" :size="size" :offset="0" :grid="grid" :getCanvasContextMethod="getCanvasContext" :putCellMethod="putCellMethod" />
            <canvas-grid
                :styles="styles" :scale="scale" :size="size"
                :offset="0" :grid="grid" :getCanvasContextMethod="getCanvasContext"
            />
        </div>
    `
});

Vue.component('cell-state-transition-config', {
    props: ['transition'],
    template: `
        <div class="card mb-2">
            <div class="card-body">
                {{transition}}
            </div>
        </div>
    `
});

Vue.component('cell-state-config', {
    props: ['state'],
    template: `
        <div class="card mb-2">
            <div class="card-body">
                <h5 class="card-title">{{ state.tag }}</h5>
                <span class="lead">Transitions:</span>
                <cell-state-transition-config v-for="transition in state.transitions" :transition="transition" />
            </div>
        </div>
    `
})

Vue.component('configuration', {
    props: ['config', 'visible'],
    data: function() {
        return {
            isVisible: this.visible,
            states: undefined
        }
    },
    watch: {
        visible: function(newValue, oldValue) {
            this.isVisible = newValue;
        },
        config: function(newValue, oldValue) {
            this.states = newValue.states;
        }
    },
    template: `
        <div class="h-100 w-100 bg-white p-2 rounded container-fluid" :class="visible ? '' : 'd-none'">
            <cell-state-config v-for="state in states" :state="state" />
        </div>
    `
});

Vue.component('game-of-life-app', {
    data: function() {
        return {
            styles: ['rgb(128, 128, 128)', 'peachpuff'],
            config: undefined,
            grid: undefined,
            replaces: undefined,
            configurationHidden: false,
            timerStarted: false,
            scale: 1.2,
            size: 10
        }
    },
    created: function() {
        configurationResource.get()
                .then(response => response.json().then(
                    config => {
                        this.config = config;
                    }
                ));

        this.update();

        setInterval(this.timerTick, 500);
    },
    methods: {
        toggleConfiguration: function() {
            this.configurationHidden = !this.configurationHidden;
        },
        timerToggle: function() {
            this.timerStarted = !this.timerStarted;
        },
        newGame: function() {
            newGameResource
                .save({width: 300, height: 150})
                .then(res => this.update());
        },
        loadGridFromResponse: function(gridData) {
            this.grid = gridData.grid;
            this.replaces = gridData.replaces;
        },
        update: function() {
            gridResource.get()
                .then(res => res.json().then(
                    gridData => this.loadGridFromResponse(gridData)
                ));
        },
        evolve: function() {
            evolveResource.save({})
                .then(res => res.json().then(
                    gridData => this.loadGridFromResponse(gridData)
                ));
        },
        putCell: function(x, y) {
            if(x == -1) return;
            const current = this.grid[y][x];
            if(current == 0) {
                Vue.resource("/add/" + x + "/" + y).update({}).then(res => this.update());
            }
        },
        timerTick: function() {
            if(this.timerStarted)
                this.evolve();
        },
        zoomIn: function() {
            if(this.scale < 0.5) return;
            this.scale -= 0.1;
        },
        zoomOut: function() {
            if(this.scale > 3) return;
            this.scale += 0.1;
        }
    },
    template: `
        <div class="container-fluid h-100 mt-2">
            <navbar
                :toggleConfigurationMethod="toggleConfiguration"
                :newGameMethod="newGame"
                :evolveMethod="evolve"
                :timerToggleMethod="timerToggle"
                :timerStarted="timerStarted"
                :zoomInMethod="zoomIn"
                :zoomOutMethod="zoomOut"
            />
            <configuration :config="config" :visible="!configurationHidden" />
            <canvas-parent
                :styles="styles"
                :scale="scale"
                :size="size"
                :grid="grid"
                :visible="configurationHidden"
                :putCellMethod="putCell"
            />
        </div>
    `
});

var app = new Vue({
    el: '#app',
    template: '<game-of-life-app />',
});