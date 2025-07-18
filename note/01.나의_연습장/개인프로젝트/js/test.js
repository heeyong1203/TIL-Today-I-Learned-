function click(){
    wrapper.addEventListener("mousedown", (e)=>{
        stop();
    });
}

function unclick(){
    wrapper.addEventListener("mouseup", (e)=>{
        reStart();
    });
}