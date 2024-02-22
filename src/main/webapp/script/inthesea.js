import {GlowParticle} from './Glowparticle.js';
// import {Wave} from './Wave.js';

    const COLORS = [
        {r: 45, g:74, b: 277}, //blue
        {r: 217, g:219, b: 238}, //yellow
        {r: 55, g:104, b: 248}, //pupple
        {r: 44, g:209, b: 252}, //skyblue
        {r: 179, g:183, b: 216}, //green
];

class App {
    constructor(){
        this.canvas = document.createElement("canvas");
        document.body.insertBefore(this.canvas, document.body.firstChild);
        this.ctx = this.canvas.getContext('2d');

        // this.wave = new Wave();

        this.pixelRatio = (window.devicePixelRatio >1)? 2:1;

        this.totalParticles =35;
        this.particles = [];
        this.maxRadius = 500;
        this.minRadius = 400;

        window.addEventListener('resize', this.resize.bind(this),false);
        this.resize();

        window.requestAnimationFrame(this.animate.bind(this));
    }

    resize(){
        this.stageWidth = document.body.clientWidth;
        this.stageHeight = document.body.clientHeight;

        this.canvas.width = this.stageWidth * this.pixelRatio;
        this.canvas.height = this.stageHeight * this.pixelRatio;
        this.ctx.scale(this.pixelRatio, this.pixelRatio);

        this.ctx.globalCompositeOperation = 'darken';

        this.createParticles();

        // this.wave.resize(this.stageWidth, this.stageHeight);
    }

    createParticles(){
        let curColor = 0;
        this.particles = [];

        for(let i = 0; i < this.totalParticles; i++){
            const item = new GlowParticle(
                Math.random() * this.stageWidth,
                Math.random() * this.stageHeight,
                Math.random() *
                (this.maxRadius - this.minRadius) + this.minRadius,
                COLORS[curColor]
            );

            if(++curColor >= COLORS.length){
                curColor = 0;
            }

            this.particles[i] = item;
        }
    }

    animate(){
        window.requestAnimationFrame(this.animate.bind(this));

        this.ctx.clearRect(0, 0, this.stageWidth, this.stageHeight);

        // this.wave.draw(this.ctx);
        for(let i = 0; i < this.totalParticles; i++){
            const item = this.particles[i];
            item.animate(this.ctx, this.stageWidth, this.stageHeight);
        }



    }

}

window.onload = () => {

    new App();

}




