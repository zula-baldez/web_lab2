
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
function validateY(event) {
    const reg = new RegExp("^[+-]?([0-9]*[.,])?[0-9]+$")


    let y = parseFloat(yInput.value);
    if(Number.isNaN(y) || y <= -3 || y >= 3 || !reg.test(yInput.value)) {
        warning(yElementWarning, "Y must be from -3 to 3")
    } else {
        hideWarning(yElementWarning, "")
    }
}
yInput.addEventListener('input', validateY)
yInput.dispatchEvent(new Event('input'))