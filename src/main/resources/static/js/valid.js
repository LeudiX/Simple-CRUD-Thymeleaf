
/** 
* !Animated timed notifications
*/
function showName(name) {
    const message = document.getElementById('showN');
    // showing message with styles
    message.classList.add("timednotif");
    message.innerHTML = 'Welcome back again ' + name;
    message.style.visibility = 'visible';
    
    setTimeout(() => {
        message.classList.remove("timednotif");
        message.style.visibility = 'hidden';
    }, 8000);

}