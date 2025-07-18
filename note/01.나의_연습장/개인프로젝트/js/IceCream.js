class IceCream extends GameObject{
    constructor(container, x, y, width, height, config){
        super(container, x, y, width, height, {velX: config.velX, velY: config.velY, bg: config.imageUrl});
        this.originalVelX=config.velX; // 초기 속도 저장 (일시 정지 후 다시 움직일 때 사용)
        this.stopped = false;
    }


    // 부모의 tick override 
    // 아이스크림 멈춤, 이동에 대한 판단
    tick(){
        if (this.stopped){
            this.velX=0;
        } else {
            this.velX=this.originalVelX;
            this.x+=this.velX;
        }
    }
    
    // 부모의 render 상속
    render(){
        super.render();
    }
}