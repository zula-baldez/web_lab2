const canvas = document.getElementById('graph')
const ctx = canvas.getContext('2d')



const width = canvas.width
const height = canvas.height
const markWidth = 5
const arrowSize = 5
const rSize = 60
const figureColor = '#39f'


const baseUrl = "http://localhost:8080/WILDFLY_LAB2-1.0-SNAPSHOT"
function drawGraph() {

    drawFigure()

    drawPane()

}
function drawPane() {


    ctx.beginPath()
    //draw coordinate plate
    ctx.moveTo(width/2, height/2)
    ctx.lineTo(width, height/2)

    ctx.moveTo(width/2, height/2)
    ctx.lineTo(0, height/2)

    ctx.moveTo(width/2, height/2)
    ctx.lineTo(width/2, height)

    ctx.moveTo(width/2, height/2)
    ctx.lineTo(width/2, 0)
    //draw marks
    ctx.moveTo(width/2 + rSize, height/2 + markWidth)
    ctx.lineTo(width/2 + rSize, height/2 - markWidth)

    ctx.moveTo(width/2 + rSize*2, height/2 + markWidth)
    ctx.lineTo(width/2 + rSize*2, height/2 - markWidth)

    ctx.moveTo(width/2 - rSize, height/2 + markWidth)
    ctx.lineTo(width/2 - rSize, height/2 - markWidth)

    ctx.moveTo(width/2 - rSize*2, height/2 + markWidth)
    ctx.lineTo(width/2 - rSize*2, height/2 - markWidth)

    ctx.moveTo(width/2 + markWidth, height/2 + rSize)
    ctx.lineTo(width/2 - markWidth, height/2 + rSize)

    ctx.moveTo(width/2 + markWidth, height/2 + rSize*2)
    ctx.lineTo(width/2 - markWidth, height/2 + rSize*2)

    ctx.moveTo(width/2 + markWidth, height/2 - rSize)
    ctx.lineTo(width/2 - markWidth, height/2 - rSize)

    ctx.moveTo(width/2 + markWidth, height/2 - rSize*2)
    ctx.lineTo(width/2 - markWidth, height/2 - rSize*2)
    //draw arrows
    ctx.moveTo(width/2, 0)
    ctx.lineTo(width/2 + arrowSize, arrowSize)
    ctx.moveTo(width/2, 0)
    ctx.lineTo(width/2 - arrowSize, arrowSize)

    ctx.moveTo(width, height/2)
    ctx.lineTo(width - arrowSize, height/2 + arrowSize)
    ctx.moveTo(width, height/2)
    ctx.lineTo(width - arrowSize, height/2 - arrowSize)
    //draw text
    ctx.moveTo(width/2, height/2)
    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
    ctx.textAlign  = 'center';

    ctx.fillText('-R/2', width/2 - rSize, height*8/17)
    ctx.fillText('-R', width/2 - rSize*2, height*8/17)
    ctx.fillText('R/2', width/2 + rSize, height*8/17)
    ctx.fillText('R', width/2 + rSize*2, height*8/17)
    ctx.textAlign = 'left'
    ctx.textBaseline = 'middle'
    ctx.fillText('-R/2', width * 9 / 17, width/2 + rSize)
    ctx.fillText('-R', width * 9 / 17, width/2 + rSize*2)
    ctx.fillText('R/2', width * 9 / 17, width/2 - rSize)
    ctx.fillText('R', width * 9 / 17, width/2 - rSize*2)

    ctx.font = '15px monospace'

    ctx.fillText('y', width * 9 / 17, arrowSize * 2)
    ctx.textAlign = 'center'
    ctx.textBaseline = 'bottom'

    ctx.fillText('x', width - arrowSize, height*8/17)

    ctx.stroke()
    ctx.textAlign  = 'center';

    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
}
function drawFigure() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    ctx.font = '20px monospace'
    ctx.fillStyle = '#000'
    ctx.textAlign = 'center'
    ctx.textBaseline = 'middle'

    ctx.beginPath()
    ctx.fillStyle = figureColor
    ctx.moveTo(width/2, height/2);
    ctx.lineTo(width/2 + rSize, height/2);
    ctx.lineTo(width/2, height/2 - rSize * 2)
    ctx.lineTo(width / 2 - rSize, height / 2 - rSize * 2)
    ctx.lineTo(width / 2 - rSize, height / 2)
    ctx.moveTo(width / 2, height / 2 + rSize)

    ctx.lineTo(width / 2, height / 2)

    ctx.fill()


    ctx.moveTo(width / 2 , height / 2 + rSize)
    ctx.lineTo(width / 2, height / 2)
    ctx.lineTo(width / 2 - rSize, height / 2)
    ctx.arc(width / 2, height / 2, rSize,  Math.PI / 2, Math.PI)
    ctx.fill()



}


canvas.onmousedown = (e) => {

    let form = document.getElementById('form')
    let formData = new FormData(form)
    if(!formData.has('r') || formData.get('r') === 0) {
        alert('First enter R')
        return
    } else {
        var r = formData.get('r')
    }
    let x = (e.offsetX / width) * (3 * r ) - (3 / 2) * r ;
    let y =  ((3 * r / 2 - (e.offsetY / height * (3 * r ))) * 10) / 10;

    let dr = x - Math.floor(x);
    if(x>=0) {
        if(dr >= 0.75) x = Math.floor(x)+1;
        else if(dr < 0.75 && dr >= 0.25) x = Math.floor(x) + 0.5
        else x = Math.floor(x);
    } else {
        if(dr <= -0.75) y = Math.floor(x)-1;
        else if(dr > -0.75 && dr <= -0.25) x = Math.floor(x) - 0.5
        else x = Math.floor(x);
    }




    form['x'].value = x;

    form['y'].value = y;

    if(x > 2  || x <-2) {
        alert("X must be from -2 to 2")
        return
    }
    form.submit()
}

drawGraph()