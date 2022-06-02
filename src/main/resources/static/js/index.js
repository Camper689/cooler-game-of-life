const configurationResource = Vue.resource("/configuration");
const newGameResource = Vue.resource('/new-game');
const evolveResource = Vue.resource('/evolve');
const gridResource = Vue.resource('/grid');

Vue.component('navbar', {
    props: [
        'configurationHidden', 'selectedCellName',
        'toggleConfigurationMethod', 'newGameMethod', 'evolveMethod',
        'timerToggleMethod', 'timerStarted', 'zoomInMethod',
        'zoomOutMethod'
    ],
    data: function() {
        return {
            playing: this.timerStarted,
            configurationHidden0: this.configurationHidden
        }
    },
    watch: {
        timerStarted: function(newValue, oldValue) {
            this.playing = newValue;
        },
        configurationHidden: function(newValue) {
            this.configurationHidden0 = newValue;
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
                        <li class="nav-item" v-if="configurationHidden">
                            <button class="btn btn-outline-success" type="button" @click="newGameMethod()">New game</button>
                        </li>
                        <li class="nav-item" v-if="configurationHidden">
                            <button class="btn btn-outline-success" type="button" @click="evolveMethod()">
                                Evolve
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-circle" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
                                </svg>
                            </button>
                        </li>
                        <li class="nav-item" v-if="configurationHidden">
                            <button class="btn btn-success" @click="timerToggleMethod()">
                                <svg :class="playing ? 'd-none' : ''" class="bi bi-play-fill" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path d="m11.596 8.697-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 0 1 0 1.393z"/>
                                </svg>
                                <svg :class="!playing ? 'd-none' : ''" class="bi bi-pause-fill" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                    <path d="M5.5 3.5A1.5 1.5 0 0 1 7 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5zm5 0A1.5 1.5 0 0 1 12 5v6a1.5 1.5 0 0 1-3 0V5a1.5 1.5 0 0 1 1.5-1.5z"/>
                                </svg>
                            </button>
                        </li>
                        <li class="nav-item" v-if="configurationHidden">
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
                        <li class="nav-item" v-if="!configurationHidden">
                            <button class="btn btn-success" type="button" @click="toggleConfigurationMethod()">Hide configuration</button>
                        </li>
                        <li class="nav-item" v-if="configurationHidden">
                            <button class="btn btn-success" type="button" @click="toggleConfigurationMethod()">ShowConfiguration</button>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
    `
});

Vue.component('canvas-grid', {
    props: ['colors', 'scale', 'grid', 'offset', 'size', 'getCanvasContextMethod'],
    data: function() {
        return {
            context: undefined,
            grid0: this.grid,
            colors0: this.colors,
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
        getColorFromIndex: function(index) {
            return this.colors0[index];
        },
        draw: function() {
            if(!this.grid0) return;

            var prevColor = '';
            var topOffset = this.offset0;
            var leftOffset = 0;

            for(var rowIndex = 0; rowIndex < this.grid0.length; rowIndex++) {
                const row = this.grid0[rowIndex];
                leftOffset = 0 + this.offset0;

                for(var elemIndex = 0; elemIndex < row.length; elemIndex++) {
                    const elem = row[elemIndex];

                    const color = this.getColorFromIndex(elem);
                    if(prevColor != color) {
                        prevColor = color;
                        this.context.fillStyle = color;
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
        colors: function(newValue, oldValue) {
            this.colors0 = newValue;
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
    props: ['colors', 'scale', 'size', 'grid', 'visible', 'putCellMethod'],
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
        /*
        _config: {
            handler(newValue) {
                this.config = newValue;
            },
            deep: true
        }
        */
    },
    template: `
        <div class="h-100 w-100" :class="isVisible ? '' : 'd-none'" style="position: relative">
            <canvas-hover :scale="scale" :size="size" :offset="0" :grid="grid" :getCanvasContextMethod="getCanvasContext" :putCellMethod="putCellMethod" />
            <canvas-grid
                :colors="colors" :scale="scale" :size="size"
                :offset="0" :grid="grid" :getCanvasContextMethod="getCanvasContext"
            />
        </div>
    `
});

Vue.component('configuration', {
    props: ['config', 'visible', 'onChangeMethod', 'colors', 'updateColorsMethod', 'resetColorsMethod'],
    data: function() {
        return {
            isVisible: this.visible,
            yaml: this.readConfigToYaml(this.config),
            colors0: [],
            states: undefined,
            thereWasChanges: false,
            errorMessage: '',
            colorsHidden: true
        }
    },
    created: function() {
        this.states = this.config.states;
        for(var color of this.colors) {
            this.colors0.push(color);
        }
    },
    methods: {
        readYamlToObject(yaml) {
            const stack = [];
            const lines = yaml.split("\n").reverse();

            for(var line of lines) {
                if(!line.trim().length) continue;

                const match = new RegExp("[\t ]+").exec(line);
                const symbolCountArray = match ? match[0].split('').map(symbol => symbol == '\t' ? 4 : 1) : [];
                var nesting = 0;
                for(var count of symbolCountArray) {
                    nesting += count;
                }

                line = line.trim()
                const key = line.split(":")[0];

                var value = line.substring(line.indexOf(":") + 1).trim();
                if(value == "") {
                    value = {};

                    while(stack.length && stack[0].nesting > nesting) {
                        const firstValue = stack.shift();
                        value[firstValue.key] = firstValue.value;
                    }
                }

                stack.unshift({nesting, key, value});
            }

            var result = {};
            for(var stackObj of stack) {
                result[stackObj.key] = stackObj.value;
            }

            return result;
        },
        readConfigToYaml(config) {
            var yaml = '';
            var stateIndex = 1;
            for(var state of config.states) {
                const stateName = config.defaultStateName == state.name ? 'default-state' : ('state-' + stateIndex++);
                yaml += stateName + ":\n\t";
                yaml += "name: " + state.name + "\n\t";
                yaml += "transitions:" + "\n\t\t";
                var transitionIndex = 1;
                for(var transition of state.transitions) {
                    yaml += ("transition-" + transitionIndex++) + ":\n\t\t\t";
                    yaml += "condition:\n\t\t\t\t";
                    yaml += "number-of-neighbours: " + transition.condition.neighboursStateName + "\n\t\t\t\t";
                    yaml += "is: " + transition.condition.symbol + "\n\t\t\t\t";
                    yaml += "number: " + transition.condition.number + "\n\t\t\t";
                    yaml += "become: " + transition.newStateName + "\n\t\t";
                }

                yaml += "\n";
            }

            return yaml;
        },
        onYamlChange: function() {
            this.thereWasChanges = true;
        },
        reset: function() {
            this.yaml = this.readConfigToYaml(this.config);
            this.thereWasChanges = false;
        },
        error(message) {
            this.errorMessage = message;
            this.thereWasChanges = false;
        },
        save: function() {
            this.error('');

            const yamlAsJSON = this.readYamlToObject(this.yaml);
            if(!yamlAsJSON["default-state"] || !yamlAsJSON['default-state'].name) {
                return this.error("At least one of cell state sections must be named 'default-state'");
            }

            var states = [];
            for(var key of Object.keys(yamlAsJSON)) {
                const state = yamlAsJSON[key];

                const name = state.name;
                if(!name) {
                    return this.error(state.key + " has no name");
                }

                if(states.filter(s => s.name == name).length) {
                    return this.error("There is two states with the same name " + name);
                }

                const transitions = [];
                if(state.transitions) {
                    for(var transitionKey of Object.keys(state.transitions)) {
                        const transition = state.transitions[transitionKey];
                        const condition = transition.condition;
                        if(!condition) {
                            return this.error("There is no condition in transition " + transitionKey);
                        }

                        const neighbours = condition["number-of-neighbours"];
                        if(!neighbours) {
                            return this.error("There is no neighbour type in transition " + transitionKey);
                        }

                        if(!condition.number) {
                            return this.error("There is no number in transition " + transitionKey);
                        }

                        const number = Number.parseInt(condition.number);
                        if(isNaN(number)) {
                            return this.error("Number value in transition " + transitionKey + " is not a number");
                        }

                        const symbol = condition.is;
                        const symbolCorrect = symbol.length && (symbol == '=' || symbol == '>' || symbol == '<' || symbol == '>=' || symbol == '<=');
                        if(!symbolCorrect) {
                            return this.error("There is no symbol or symbol is incorrect in transition " + transitionKey);
                        }

                        const become = transition.become;
                        if(!become) {
                            return this.error("There is no 'become' property in transition " + transitionKey);
                        }

                        transitions.push({
                            "condition": {
                                "number": number,
                                "neighboursStateName": neighbours,
                                "symbol": symbol
                            },
                            "newStateName": become
                        });
                    }
                }

                states.push({name, transitions});
            }

            var newConfig = {
                defaultStateName: yamlAsJSON['default-state'].name,
                states: states
            };

            this.onChangeMethod(newConfig);
        },
        setColor(index) {
            console.log(index);
        }
    },
    watch: {
        visible: function(newValue, oldValue) {
            this.isVisible = newValue;
        },
        config: {
            handler(newValue) {
                this.yaml = this.readConfigToYaml(newValue);
                this.states = newValue.states;

                while(this.colors0.length < this.states.length) {
                    this.colors0.push('#0000FF');
                }
            },
            deep: true
        },
        colors: function(newValue) {
            this.colors0 = newValue;
        }
    },
    template: `
        <div class="h-100 w-100 bg-white p-3 rounded container-fluid" :class="visible ? '' : 'd-none'">
            <div class="h-100" :class="!colorsHidden ? 'd-none' : ''">
                <div class="row bg-light p-3 rounded">
                    <div class="col">
                        <h4 class="card-title">&nbsp;Enter your configuration here: </h4>
                        <h5 class="text-danger">{{errorMessage}}</h5>
                    </div>
                    <div class="col-auto ml-auto">
                        <button class="btn btn-primary" @click="colorsHidden = false">Change colors</button>
                        <button class="btn btn-danger" @click="reset">Reset</button>
                        <button class="btn btn-success" @click="save" v-if="thereWasChanges">Save</button>
                    </div>
                </div>

                <textarea v-model="yaml" class="form-control height-fill" @input="onYamlChange"></textarea>
            </div>

            <div class="h-100" :class="colorsHidden ? 'd-none' : ''">
                <div class="row bg-light p-3 rounded">
                    <div class="col">
                        <h4 class="card-title">&nbsp;Edit cell colors:</h4>
                    </div>
                    <div class="col-auto ml-auto">
                        <button class="btn btn-primary" @click="colorsHidden = true">Change configuration</button>
                        <button class="btn btn-danger" @click="resetColorsMethod">Reset to default</button>
                    </div>
                </div>

                <div class="card-deck mt-2">
                    <div class="card mt-2" v-for="(state, index) of states">
                        <div class="card-body">
                            <div class="card-text p-2">
                                <span class="lead">{{state.name}}: </span>
                                <input type="color" v-model="colors0[index]" @change="updateColorsMethod(colors0)" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `
});

Vue.component('game-of-life-app', {
    data: function() {
        return {
            colors: this.readFromLocalStorage("colors", '#808080,#FFDAB9,#8A2BE2,#D2691E,#00FF00,#C87064').split(','),
            selectedCellName: undefined,
            config: undefined,
            grid: undefined,
            replaces: undefined,
            configurationHidden: true,
            timerStarted: false,
            scale: this.readFromLocalStorage("scale", 1.2),
            size: 10
        }
    },
    created: function() {
        configurationResource.get()
                .then(response => response.json().then(
                    config => {
                        this.config = config;
                        this.selectCellName(this.config);
                    }
                ));

        this.update();

        setInterval(this.timerTick, 500);
    },
    methods: {
        readFromLocalStorage: function(name, defaultValue) {
            return localStorage.getItem(name) || defaultValue;
        },
        saveInLocalStorage: function(name, value) {
            localStorage.setItem(name, value);
        },
        selectCellName: function(config) {
            if(config.states.length > 1) {
                this.selectedCellName = config.states.filter(state => state.name != config.defaultStateName)[0].name;
            } else {
                this.selectedCellName = config.defaultStateName;
            }
        },
        toggleConfiguration: function() {
            this.configurationHidden = !this.configurationHidden;
        },
        timerToggle: function() {
            this.timerStarted = !this.timerStarted;
        },
        newGame: function() {
            newGameResource
                .save({grid: {width: 100, height: 100}, configuration: this.config})
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
                Vue.resource("/add/" + x + "/" + y + "/" + this.selectedCellName).update({}).then(res => this.update());
            }
        },
        timerTick: function() {
            if(this.timerStarted)
                this.evolve();
        },
        zoomIn: function() {
            if(this.scale < 0.5) return;
            this.scale -= 0.1;
            this.saveInLocalStorage("scale", this.scale);
        },
        zoomOut: function() {
            if(this.scale > 3) return;
            this.scale += 0.1;
            this.saveInLocalStorage("scale", this.scale);
        },
        updateConfig: function(newConfig) {
            this.config = newConfig;
            this.selectCellName(this.config);

            this.newGame();
        },
        updateColors: function(newColors) {
            this.colors = newColors;
            this.saveInLocalStorage("colors", this.colors.join(','));
        },
        resetColors: function() {
            this.updateColors(['#808080', '#FFDAB9', '#8A2BE2', '#D2691E', '#00FF00', '#C87064']);
        }
    },
    template: `
        <div class="container-fluid h-100 mt-2">
            <navbar
                :configurationHidden="configurationHidden"
                :selectedCellName="selectedCellName"
                :toggleConfigurationMethod="toggleConfiguration"
                :newGameMethod="newGame"
                :evolveMethod="evolve"
                :timerToggleMethod="timerToggle"
                :timerStarted="timerStarted"
                :zoomInMethod="zoomIn"
                :zoomOutMethod="zoomOut"
            />
            <configuration
                v-if="config"
                :config="config"
                :colors="colors"
                :visible="!configurationHidden"
                :onChangeMethod="updateConfig"
                :updateColorsMethod="updateColors"
                :resetColorsMethod="resetColors"
            />
            <canvas-parent
                :colors="colors"
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