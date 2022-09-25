
const yInput = document.getElementById("yInput")

const yElementWarning = document.getElementById("y-warning")

const submitButton = document.getElementById("form-submit")

function warning(elem, txt) {
    elem.innerHTML = txt;
    submitButton.disabled = true

}
function hideWarning(elem, txt) {
    elem.innerHTML = txt
    submitButton.disabled = false



}
function validateX(event) {
    const reg = new RegExp("^[+-]?([0-9]*[.,])?[0-9]+$")


    let x = parseFloat(yInput.value);
    if(Number.isNaN(x) || x <= -3 || x >= 3 || !reg.test(yInput.value)) {
        warning(yElementWarning, "X must be from -3 to 3")
    } else {
        hideWarning(yElementWarning, "")
    }
}
yInput.addEventListener('input', validateX)
yInput.dispatchEvent(new Event('input'))