var oTxt = document.getElementById("txt");
var oBox = document.getElementById("box");

function handle() {
  oBox.innerHTML = oTxt.value;
  var a = oBox.offsetWidth;
  if (a >40) {
    oTxt.style.width = oBox.offsetWidth + 10 + "px";
  } else {
    oTxt.style.width = "50px";
  }
}

if (/msie/i.test(navigator.userAgent)) {
  oTxt.onpropertychange = handle;
} else {
  oTxt.addEventListener("input", handle, false);
}